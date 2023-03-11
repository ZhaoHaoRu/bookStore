package org.sjtu.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.sjtu.backend.entity.BookImg;

@Repository
public interface BookImgRepository extends MongoRepository<BookImg, Integer> {
    BookImg findByBookId(Integer bookId);

    BookImg save(BookImg goodsPic);
}
