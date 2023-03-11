package org.sjtu.backend.dao;

import org.sjtu.backend.entity.Book;

import java.util.List;

public interface BookDao {
    List<Book> findByName(String name);

    Book findById(int id);

    List<Book> findAll();

    Book save(Book newBook);

//    List<Book> findByISBN(int ISBN);

}
