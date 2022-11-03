package org.sjtu.backend.service;

import org.sjtu.backend.entity.Book;

import java.util.List;
import java.util.Map;

public interface SolrService {
    List<Book> queryByCondition(String de);

    Map<String, Object> insertAndUpdate(Book book);

    Map<String, Object> deleteBookById(int id);
}
