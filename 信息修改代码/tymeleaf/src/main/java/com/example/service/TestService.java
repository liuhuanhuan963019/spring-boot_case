package com.example.service;



import com.example.common.Pager;
import com.example.entity.Test;

import java.util.Map;

public interface TestService {

    public Pager<Test> findAll(Map<String,String> map, int size) throws Exception;

    public void save(Map<String,String> map) throws Exception;
}
