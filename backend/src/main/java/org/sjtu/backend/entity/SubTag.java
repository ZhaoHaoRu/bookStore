package org.sjtu.backend.entity;


import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@NodeEntity("SubTag")
@Getter
@Setter
public class SubTag {
    @Id
    @GeneratedValue
    private Long subTagId;

    private String name;

    private SubTag() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public SubTag(String name) {
        this.name = name;
    }

    @Relationship(type = "PARENT")
    public Tag parent;

}
