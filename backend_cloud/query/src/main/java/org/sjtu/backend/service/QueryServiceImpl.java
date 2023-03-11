package org.sjtu.backend.service;

import org.sjtu.backend.dao.BookDao;
import org.sjtu.backend.entity.Book;
import org.sjtu.backend.serviceimpl.QueryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QueryServiceImpl implements QueryService {
    @Resource
    BookDao bookDao;

    @Override
    public String queryAuthor(String book_name) {
        List<Book> books = bookDao.findByName(book_name);

        if(books.isEmpty()) {
            return "";
        }
        for(Book book : books) {
            if(book.getInventory() > 0) {
                return book.getAuthor();
            }
        }
        return "";
    }
}
