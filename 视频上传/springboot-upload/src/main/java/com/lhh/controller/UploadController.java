package com.lhh.controller;


import com.lhh.domain.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 上传文件
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
public class UploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);


    @PostMapping("/upload")
    @ResponseBody
    public ResponseBean upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String filePath = request.getSession().getServletContext().getRealPath("/uploadFile/");
        File dir = new File(filePath);
        if (!dir.isDirectory()) {//文件目录不存在，就创建一个
            dir.mkdirs();
        }

        if (file.isEmpty()) {
            return ResponseBean.error("上传失败，请选择文件",null);
        }
        String fileName = file.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = System.currentTimeMillis() + ext;
        File dest = new File(filePath + File.separator + newFileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            String url =  request.getScheme() +"://" + request.getServerName()
                    + ":" +request.getServerPort() + "/uploadFile/";

            return ResponseBean.success("上传成功", url  + newFileName);
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return ResponseBean.error("上传失败",null);
    }
}
