package org.sjtu.backend.controllers;

import org.sjtu.backend.entity.Book;
import org.sjtu.backend.service.BookService;
import org.sjtu.backend.service.SolrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private SolrService solrService;


    @RequestMapping("/book/getBooks")
    public List<Book> getBooks() {
        List<Book> books = bookService.findAll();
        System.out.println(books);
        return books;
    }


    @RequestMapping("/book/getBook")
    public Book getBook(@RequestParam("id") Integer id) {
        Book book = bookService.findById(id);
        System.out.println("to find a book!");
        return book;
    }


    @RequestMapping("/book/delete")
    public Book deleteBook(@RequestParam("id") Integer id) {
        return bookService.deleteBook(id);
    }


    @RequestMapping("/book/addBook")
    public Book addBook(@RequestParam("ISBN") Integer ISBN, @RequestParam("name") String name,
                        @RequestParam("type") String type, @RequestParam("author") String author,
                        @RequestParam("priceYuan") int priceYuan, @RequestParam("priceJiao") int priceJiao,
                        @RequestParam("description") String description,
                        @RequestParam("inventory") Integer inventory, @RequestParam("inventory") String image)
    {
        return bookService.addBook(ISBN, name, type, author, priceYuan, priceJiao, description, inventory, image);
    }


    @RequestMapping("/book/changeAttribute")
    public Book changeAttribute(@RequestParam("id") Integer id, @RequestParam("attribute") String attribute, @RequestParam("newValue") String newValue){
        if(attribute.equals("name"))
            return bookService.changeName(id, newValue);
        if(attribute.equals("author"))
            return bookService.changeAuthor(id, newValue);
        if(attribute.equals("ISBN"))
            return bookService.changeIsbn(id, Integer.parseInt(newValue));
        if(attribute.equals("inventory"))
            return bookService.changeInventory(id, Integer.parseInt(newValue));
        else
            return bookService.changeImg(id, newValue);
    }

//    @RequestMapping("/book/changeName")
//    public Book changeName(@RequestParam("id") Integer id, @RequestParam("newName") String newName){
//        return bookService.changeName(id, newName);
//    }
//
//    @RequestMapping("/book/changeAuthor")
//    public Book changeImg(@RequestParam("id") Integer id, @RequestParam("newAuthor") String newAuthor){
//        return bookService.changeAuthor(id, newAuthor);
//    }
//
//    @RequestMapping("/book/changeIsbn")
//    public Book changeIsbn(@RequestParam("id") Integer id, @RequestParam("newIsbn") Integer newIsbn){
//        return bookService.changeIsbn(id, newIsbn);
//    }
//
//    @RequestMapping("/book/changeInventory")
//    public Book changeInventory(@RequestParam("id") Integer id, @RequestParam("newInventory") Integer newInventory){
//        return bookService.changeInventory(id, newInventory);
//    }

    @RequestMapping("/book/query")
    public List<Book> queryByCondition(@RequestParam("condition") String condition) {
        return solrService.queryByCondition(condition);
    }

}
