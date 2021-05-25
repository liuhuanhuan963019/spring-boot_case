package com.example.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.utils.DocExclPDFUtils;
import com.example.utils.Word2PdfJacobUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/common")
@RestController
@Api(description = "文档操作接口", tags = { "文档操作接口接口管理" })
public class CommonController {

	public static final String fileSavePath = "D:\\file\\";
	public static final String targetPath = "D:\\pdf\\";


	@ApiOperation(value = "word转pdf", notes = "word转pdf")
	@PostMapping("/wordToPdf")
	public Map wordExcute(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map map = new HashMap();
		try {
			String realPath = request.getSession().getServletContext().getRealPath("/");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String dateStr =  sdf.format(new Date());
			String filename = file.getOriginalFilename();
			String path = copyFileUsingFileStreams(file.getInputStream(), fileSavePath + dateStr, fileSavePath + dateStr +File.separator+filename);
//			String s1 = DocExclPDFUtils.wordToPdf2(path, realPath + "pdf" + File.separator + dateStr, realPath + "pdf" + File.separator + dateStr + File.separator + filename.substring(0, filename.lastIndexOf(".")) + ".pdf");
			Word2PdfJacobUtil.word2PDF(path, "E:\\test.pdf");
			map.put("status", "0000");
			map.put("msg", "成功");
			map.put("fileData1", "pdf" + File.separator + dateStr + File.separator + filename.substring(0, filename.lastIndexOf(".")) + ".pdf");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("status", "9999");
			map.put("msg", "失败");
		}

		return map;

	}

	private static String copyFileUsingFileStreams(InputStream input, String dest,String path) throws IOException {
		OutputStream output = null;
		try {
			File file = new File(dest);
			if(!file.exists()) {
				file.mkdirs();
			}
			output = new FileOutputStream(path);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
		} finally {
			if(output != null) {
				output.close();
			}
		}
		return path;
	}

}
