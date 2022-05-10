package org.sjtu.backend.daoimpl;

import org.sjtu.backend.entity.Book;
import org.sjtu.backend.dao.BookDao;
import org.sjtu.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao{
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findByName(String name){
        return bookRepository.findByName(name);
    }

    @Override
    public Book findById(int id){
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll(){

        return bookRepository.getBooks();
    }

    @Override
    public Book save(Book newBook){
        return bookRepository.save(newBook);
    }
}
