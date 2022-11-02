package org.sjtu.backend.service;

import org.sjtu.backend.entity.Book;

import java.util.List;

public interface SolrService {
    List<Book> queryByCondition(String de);
}
