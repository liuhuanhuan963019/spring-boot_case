package com.lhh.dao;

import com.lhh.pojo.Node;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NodeDao extends JpaRepository<Node, String> {

    @Query(value = "SELECT * FROM news  ORDER BY create_time DESC", nativeQuery = true)
    public Page<Node> newlist(Pageable pageable);
}
