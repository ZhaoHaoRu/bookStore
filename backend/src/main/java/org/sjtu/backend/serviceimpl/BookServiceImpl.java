package org.sjtu.backend.serviceimpl;

import org.sjtu.backend.entity.Book;
import org.sjtu.backend.dao.BookDao;
import org.sjtu.backend.entity.CartItem;
import org.sjtu.backend.entity.OrderItem;
import org.sjtu.backend.entity.User;
import org.sjtu.backend.repository.BookRepository;
import org.sjtu.backend.service.BookService;
import org.apache.commons.collections.CollectionUtils;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.sjtu.backend.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookServiceImpl implements BookService{
    @Resource
    private BookDao bookDao;

    @Resource
    private CartService cartService;

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Override
//    public List<Book> findByName(String name) {
//        List<Book> books = bookDao.findByName(name);
//        for (Book book: books) {
//            if(book.getInventory() == -1)
//                books.remove(book);
//        }
//        return books;
//    }

    @Override
    public Book findById(int id) {
       Book book = bookDao.findById(id);
       return book;
    }

    // delete之后的操作是否也是需要有所修改？
    @Override
    public Book deleteBook(int id) {
        Book toDelete = bookDao.findById(id);
        if(toDelete == null)
            return null;
        toDelete.setInventory(-1);
        return bookDao.save(toDelete);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Book addBook(int isbn, String name, String type, String author, int priceYuan, int priceJiao, String description, int inventory, String image) {
        Book newBook = new Book();
        newBook.setName(name);
        newBook.setAuthor(author);
        newBook.setType(type);
        newBook.setDescript(description);
        newBook.setPriceYuan(priceYuan);
        newBook.setPriceJiao(priceJiao);
        newBook.setIsbn(isbn);
        newBook.setInventory(inventory);
        newBook.setImage(image);
        return bookDao.save(newBook);
    }

    @Override
    public Book copyBook(Book toChange) {
        Book newBook = new Book();
        newBook.setIsbn(toChange.getIsbn());
        newBook.setName(toChange.getName());
        newBook.setType(toChange.getType());
        newBook.setAuthor(toChange.getAuthor());
        newBook.setPriceYuan(toChange.getPriceYuan());
        newBook.setPriceJiao(toChange.getPriceJiao());
        newBook.setDescript(toChange.getDescript());
        newBook.setInventory(toChange.getInventory());
        newBook.setImage(toChange.getImage());
        newBook.setSales(toChange.getSales());
        //这个操作相当于将原先的书删除
        toChange.setInventory(-1);
        bookDao.save(toChange);
        return newBook;
    }

    @Override
    public List<CartItem> updateAfterChange(Book oldBook, Book newBook) {
        if(newBook == null || oldBook == null)
            return null;
        return cartService.updateBookInCart(oldBook.getId(), newBook.getId());
    }

    //这里对于cart中的应该也要有相应的变化
    @Override
    public Book changeName(int id, String newName) {
        Book toChange = findById(id);
        if(toChange == null)
            return null;
        Book newBook = copyBook(toChange);
        newBook.setName(newName);
        Book news = bookDao.save(newBook);
        updateAfterChange(toChange, news);
        return news;
    }

    @Override
    public Book changeAuthor(int id, String newAuthor) {
        Book toChange = findById(id);
        if(toChange == null)
            return null;
        toChange.setAuthor(newAuthor);
        return bookDao.save(toChange);
    }

    @Override
    public Book changeImg(int id, String newImg) {
        Book toChange = findById(id);
        if(toChange == null)
            return null;
        Book newBook = copyBook(toChange);
        newBook.setImage(newImg);
        Book news = bookDao.save(newBook);
        updateAfterChange(toChange, news);
        return news;
    }

    @Override
    public Book changeIsbn(int id, int newIsbn) {
        Book toChange = findById(id);
        if(toChange == null)
            return null;
        toChange.setIsbn(newIsbn);
        return bookDao.save(toChange);
    }

    @Override
    public Book changeInventory(int id, int newInventory) {
        Book toChange = findById(id);
        if(toChange == null)
            return null;
        toChange.setInventory(newInventory);
        return bookDao.save(toChange);
    }

    @Override
    public List<Book> findByTag(String tag) {
        return bookDao.findByTag(tag);
    }
}
