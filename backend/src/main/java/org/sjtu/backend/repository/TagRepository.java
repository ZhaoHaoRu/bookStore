package org.sjtu.backend.repository;

import java.util.List;
import java.util.Set;

import org.sjtu.backend.entity.Tag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends Neo4jRepository<Tag, Long> {
    Tag findByName(String name);
}
