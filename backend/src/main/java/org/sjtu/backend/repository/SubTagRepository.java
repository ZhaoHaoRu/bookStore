package org.sjtu.backend.repository;

import org.sjtu.backend.entity.SubTag;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubTagRepository extends Neo4jRepository<SubTag, Long> {
//    @Query("MATCH(p:SubTag{name:{name}}) return p")
    SubTag findByName(String name);
}
