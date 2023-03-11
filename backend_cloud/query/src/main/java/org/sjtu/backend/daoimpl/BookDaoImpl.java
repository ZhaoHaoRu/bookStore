package org.sjtu.backend.daoimpl;

import org.sjtu.backend.dao.BookDao;
import org.sjtu.backend.entity.Book;
import org.sjtu.backend.repository.BookRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao{
    @Resource
    private BookRepository bookRepository;



    @Override
    public List<Book> findByName(String name){
        return bookRepository.findByName(name);
    }

    @Override
    public Book findById(int id){
        Book new_book = bookRepository.findById(id);
        return new_book;
    }

    @Override
    public List<Book> findAll(){
        List<Book> result = bookRepository.getBooks();
        return result;
    }

    @Override
    public Book save(Book newBook){
        // 要在数据库中的书籍都要进行更新
        Book savedBook = bookRepository.save(newBook);
        return savedBook;
    }
}
