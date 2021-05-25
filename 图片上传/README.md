# Spring Boot + Vue前后端分离实现图片上传功能

前端页面

![img](https://mmbiz.qpic.cn/mmbiz_png/wSDTzzfKr2o9EUP8KyYTTe8k0CYIxw4rwatjicmvhAsSzib7nL5Keib2SoRUnTHCz5wVX6ibelx0ctmG069aU0mvSg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

上传第一张图片

![img](https://mmbiz.qpic.cn/mmbiz_png/wSDTzzfKr2o9EUP8KyYTTe8k0CYIxw4r03fbJ1JQ5uy5H6MtjQhcWSQVlglMwIibePHfUeXy9CCUR1DJSsZfUbA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

上传第二张图片

![img](https://mmbiz.qpic.cn/mmbiz_png/wSDTzzfKr2o9EUP8KyYTTe8k0CYIxw4r03hIoHkoXhhm7Np5mmE6nFINPnN0C8BD2Rhok1fbzR8mg5SWVaODFw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

提示：图片直接上传保存到本地。



**环境：**

**前端：vue**

**后端：springboot**



pox.xml

```java
<!-- https://mvnrepository.com/artifact/org.slf4j/slf4j-api -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.25</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>1.7.25</version>
            <scope>compile</scope>
        </dependency>
```

ImageController.java

```java

package com.example.demo.controller;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSON;

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
```

main.js

```java

import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);
import $ from 'jquery'

axios.defaults.baseURL = 'http://127.0.0.1:8080';
Vue.prototype.HOST='/email'

Vue.config.productionTip = false
Vue.prototype.$axios = axios

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
```

Helloworld.vue

```vue

<template>
   <div>
     <el-upload
       class="upload-demo"
       action="http://127.0.0.1:8003/fileUpload/uploadImage"
       :on-preview="handlePreview"
       :on-remove="handleRemove"
       :file-list="fileList"
       list-type="picture">
       <el-button type="primary" round>点击上传</el-button>
       <div slot="tip" class="el-upload__tip" style="color:#fff;">只能上传jpg/png文件，且不超过500kb</div>
     </el-upload>
   </div>
</template>

<script>
import {
  quillEditor
} from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
export default {
  data() {
    return {
      fileList: []
    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    uploadFile(){
      console.log(this.fileList);
    }
  }
}
</script>
```



