package org.sjtu.backend.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import com.alibaba.fastjson.JSON;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Getter
@Setter
@Document
public class BookImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer picId;

    @Indexed(unique = true)
    private Integer bookId;

    // the picture url
    private String picture;
}
