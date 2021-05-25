package com.lhh.service;




import com.lhh.common.Pager;
import com.lhh.entity.Test;

import java.util.Map;

public interface TestService {

    public Pager<Test> findAll(Map<String,String> map, int size) throws Exception;
}
