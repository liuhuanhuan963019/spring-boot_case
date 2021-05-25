package com.lhh.serviceImpl;


import com.lhh.common.Pager;
import com.lhh.dao.TestDao;
import com.lhh.entity.Test;
import com.lhh.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestDao testDao;


    @Override
    public Pager<Test> findAll(Map<String,String> map, int size) throws Exception{
        List<Test> list = testDao.findAll();
        Pager<Test> pager = new Pager<Test>(list,1,size);
        if(map.get("page") != null ) {
            pager = new Pager<Test>(list,Integer.valueOf(map.get("page")+""),size);
        }
        return pager;
    }
}
