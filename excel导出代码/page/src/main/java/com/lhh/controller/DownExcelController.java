package com.lhh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhh.service.DownExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/excel")
@CrossOrigin
public class DownExcelController
{
	@Autowired
	private DownExcelService downExcelService;

	@RequestMapping(value="/download",method = RequestMethod.GET)
	public void downExcel(HttpServletRequest request,HttpServletResponse response,String ids)
	{
		downExcelService.downExcel(request,response, ids);
	}
}
