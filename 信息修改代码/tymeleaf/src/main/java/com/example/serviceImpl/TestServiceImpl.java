package com.example.serviceImpl;


import com.example.common.Pager;
import com.example.dao.TestDao;
import com.example.entity.Test;
import com.example.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Override
    public void save(Map<String,String> map) throws Exception {

        Test test = new Test();
        test.setId(Integer.parseInt(map.get("id")));
        test.setModifyTime(new Date());
        test.setName(map.get("name"));
        test.setRemark(map.get("name"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        test.setCreateTime(sdf.parse(map.get("createTime")));

        test.setDes(map.get("des"));
        testDao.save(test);
    }
}
