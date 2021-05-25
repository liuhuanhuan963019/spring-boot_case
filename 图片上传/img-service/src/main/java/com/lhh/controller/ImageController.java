package com.lhh.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuhuanhuan
 * @version 1.0
 * @date 2021/5/25 18:47
 */
@RestController
@RequestMapping("/fileUpload")
public class ImageController {

    private static final String imagePath = "/root/images";

    @RequestMapping(value = "/uploadImage")
    public void uploadImage(@RequestParam MultipartFile file, HttpServletRequest request,
                            HttpServletResponse response) {
        Map result = new HashMap();
        try {
            String name = file.getName();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String format = sdf.format(new Date());

            String fileName = file.getOriginalFilename();
            String path = imagePath + File.separator + fileName;

            File storeImage = new File(path);
            if (!storeImage.exists()) {
                storeImage.mkdirs();
            }
            file.transferTo(storeImage);
            result.put("status", "0");
            result.put("msg", "上传成功");
            result.put("fileName", fileName);
            result.put("path", path);
        } catch (Exception e) {
            result.put("status", "1");
            result.put("msg", "上传失败");
        }
        String json = JSON.toJSONString(result);
        PrintWriter writer = null;
        try {
            response.setHeader("Content-type", "application/json;charset=UTF-8");
            writer = response.getWriter();
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }

}
