package org.sjtu.backend.repository;


import org.sjtu.backend.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer>{
    List<Book> findAll();

    List<Book> getByOrderByInventory();

    List<Book> findByAuthor(String author);

    List<Book> findByName(String name);

    Book findById(int id);

    List<Book> getByOrderBySales();

    @Query(value = "from Book where isbn = :isbn and inventory >= -1")
    List<Book> selectByISBN(int isbn);

    @Query(value = "from Book where inventory not in (-1)")
    List<Book> getBooks();
}
