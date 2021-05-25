package com.lhh.controller;



import com.lhh.common.Pager;
import com.lhh.entity.Test;
import com.lhh.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("")
public class TestController {

    @Autowired
    TestService testServiceImpl;

    @RequestMapping(value="")
    public ModelAndView test(@RequestParam Map<String,String> map) throws Exception {
        ModelAndView mv = new ModelAndView("index");
        Pager<Test> pager = testServiceImpl.findAll(map,2);
        mv.addObject("listAll", pager.getPageContent());
        mv.addObject("pageTotal", pager.getPageTotal());
        mv.addObject("page", pager.getCurrentPage());
        mv.addObject("Result","查询成功！");
        return mv;
    }

}
