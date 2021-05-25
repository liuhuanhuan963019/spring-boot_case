package com.lhh.service;

import com.lhh.dao.NodeDao;
import com.lhh.pojo.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NodeService {

    @Autowired
    NodeDao nodeDao;

    public Page<Node> findAll(int page, int size){
        Pageable pageable = PageRequest.of(page-1,size);
        return nodeDao.newlist(pageable);
    }

}
