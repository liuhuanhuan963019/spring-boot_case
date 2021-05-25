package com.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Api(description = "文件接口", tags = {"文件接口管理"})
@RequestMapping("/file")
@RestController
public class FileController {

    @Value("${file.path}")
    private String PATH;

    @ApiOperation(value = "文档操作", notes = "文档操作")
    @PostMapping("/saveFile")
    public Map saveFile(@RequestParam("file") MultipartFile file) {
        Map map = new HashMap();
        String originalFilename = file.getOriginalFilename();
        File fileExist = new File(PATH);
        if (!fileExist.exists()) {
            fileExist.mkdirs();
        }
        try {
            file.transferTo(new File(PATH + originalFilename));
            map.put("code", "0000");
            map.put("msg", "成功");
        } catch (IOException e) {
            e.printStackTrace();
            map.put("code", "9999");
            map.put("msg", "失败");
        }
        return map;
    }
}
