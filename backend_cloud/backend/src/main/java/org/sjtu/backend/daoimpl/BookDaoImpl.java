package org.sjtu.backend.daoimpl;

import com.alibaba.fastjson.JSONArray;
import org.sjtu.backend.dao.BookDao;
import org.sjtu.backend.entity.Book;
import org.sjtu.backend.repository.BookRepository;
import org.sjtu.backend.service.SolrService;
import org.sjtu.backend.utils.redisutils.RedisUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoImpl implements BookDao{
    @Resource
    private BookRepository bookRepository;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private SolrService solrDao;

    @Override
    public List<Book> findByName(String name){
        return bookRepository.findByName(name);
    }

    @Override
    public Book findById(int id){
        Object book = redisUtil.get("book_" + id);
        Book new_book;
        System.out.println("Searching book: " + id + " in Redis");
        if(book == null) {
            System.out.println("The book: " + id + " not in Redis");
            new_book = bookRepository.findById(id);
            System.out.println("Set the uncached book: " + id + " in Redis");
            redisUtil.set("book_" + id, JSONArray.toJSON(new_book));
        } else {
            System.out.println("The book: " + id + "can find in Redis");
            new_book = JSONArray.parseObject(book.toString(), Book.class);
        }

        return new_book;
    }

    @Override
    public List<Book> findAll(){
        List<Book> result = new ArrayList<>();
        Book new_book;
        // 从id=1的书开始遍历，找到所有的书
        for(int i = 1;; ++i) {
            // 先检查redis缓存
            Object book = redisUtil.get("book_" + i);
            System.out.println("Searching book: " + i + " in Redis");
            if(book == null) {
                System.out.println("The book: " + i + " not in Redis");
                new_book = bookRepository.findById(i);
                if(new_book == null) {
                    System.out.println("Finish search all books in Redis");
                    break;
                }
                System.out.println("Set the uncached book: " + i + " in Redis");
                redisUtil.set("book_" + i, JSONArray.toJSON(new_book));
            } else {
                System.out.println("The book: " + i + "can find in Redis");
                new_book = JSONArray.parseObject(book.toString(), Book.class);
            }
            // 判断书籍的合法性
            if(new_book.getInventory() > 0)
                result.add(new_book);
        }
        return result;
    }

    @Override
    public Book save(Book newBook){
        // 要在数据库中的书籍都要进行更新
        Book savedBook = bookRepository.save(newBook);
        Integer newId = savedBook.getId();
        // 更新redis缓存
        redisUtil.set("book_" + newId, JSONArray.toJSON(savedBook));
        System.out.println("Update the book: " + newId + " in Redis");

        // 更新solr中的索引文件
        Map<String, Object> result;
        if(savedBook.getInventory() > 0) {
             result = solrDao.insertAndUpdate(savedBook);
        } else {
            result = solrDao.deleteBookById(savedBook.getId());
        }
        System.out.println("solr update message: ");
        System.out.println(result.get("message"));
        return savedBook;
    }

//    @Override
//    public List<Book> findByISBN(int ISBN) { return bookRepository.selectByISBN(ISBN); }
}
