package com.lhh.controller;


import com.lhh.pojo.Node;
import com.lhh.service.NodeService;
import com.lhh.util.Result;
import com.lhh.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/page")
public class PageController {

    @Autowired
    NodeService nodeService;

    @RequestMapping(value = "test")
    public Result test(){
        return  new Result(true, StatusCode.OK,"测试");
    }

    @RequestMapping(value = "/newlist/{page}/{size}",method= RequestMethod.GET)
    public Result test(@PathVariable int page, @PathVariable int size){
        try{
            Page<Node> all = nodeService.findAll(page, size);
            return  new Result(true, StatusCode.OK,"查询成功",all);
        }catch (Exception e){
            return  new Result(false, StatusCode.ERROR,"程序或网络错误");
        }
    }

}
