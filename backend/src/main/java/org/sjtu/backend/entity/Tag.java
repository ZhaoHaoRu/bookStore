package org.sjtu.backend.entity;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


@NodeEntity("Tag")
@Getter
@Setter
public class Tag {
    @Id
    @GeneratedValue
    private Long tagId;

    private String name;

    private Tag() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public Tag(String name) {
        this.name = name;
    }

    /**
     * Neo4j doesn't REALLY have bi-directional relationships. It just means when querying
     * to ignore the direction of the relationship.
     * https://dzone.com/articles/modelling-data-neo4j
     */
    @Relationship(type = "CHILDREN")
    public Set<SubTag> children;

    public void isParentOf(SubTag tag) {
        if (this.children == null) {
            this.children = new HashSet<>();
        }
        this.children.add(tag);
    }
}
