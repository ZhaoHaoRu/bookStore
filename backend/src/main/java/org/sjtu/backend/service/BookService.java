package org.sjtu.backend.service;

import org.sjtu.backend.dao.BookDao;
import org.sjtu.backend.entity.Book;
import org.sjtu.backend.entity.CartItem;

import java.util.List;
public interface BookService {
    List<Book> findByName(String name);

    Book findById(int id);

    Book deleteBook(int id);

    List<Book> findAll();

    Book copyBook(Book toChange);

    List<CartItem> updateAfterChange(Book oldBook, Book newBook);

    Book addBook(int isbn, String name, String type, String author, double price, String description, int inventory, String image);

    Book changeName(int id, String newName);

    Book changeAuthor(int id, String newAuthor);

    Book changeImg(int id, String newImg);

    Book changeIsbn(int id, int newIsbn);

    Book changeInventory(int id, int newInventory);
}
