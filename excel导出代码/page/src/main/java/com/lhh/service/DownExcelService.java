package com.lhh.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lhh.dao.NewsDao;
import com.lhh.pojo.News;
import com.lhh.util.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



@Service
public class DownExcelService {
    @Autowired
    private NewsDao newsDao;

    public void downExcel(HttpServletRequest request, HttpServletResponse response, String ids) {
        ServletOutputStream outputStream = null;
        try {
            List<News> lists = null;
            if (StringUtils.isEmpty(ids)) {
                lists = newsDao.findAll();
            } else {
                List<String> asList = Arrays.asList(ids.split("\\-"));

                lists = newsDao.findByIds(asList);
            }


            HSSFWorkbook writeListWeekExcel = writeListWeekExcel("测试案列", lists, 6);

            //响应到客户端
            this.setResponseHeader(request, response, "测试案列.xls");

            outputStream = response.getOutputStream();

            writeListWeekExcel.write(outputStream);

            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    private void setResponseHeader(HttpServletRequest request, HttpServletResponse response, String fileName) {
        try {
            String userAgent = request.getHeader("User-Agent");
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            } else {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static HSSFWorkbook writeListWeekExcel(String titleName, List<News> dataList,
                                                   int cloumnCount) {
        HSSFWorkbook workbook = null;
        try {
            // 获取总列数
            int columnNumCount = cloumnCount;
            // 创建工作薄
            workbook = new HSSFWorkbook();

            // 创建标题格式
            HSSFCellStyle titleStyle = workbook.createCellStyle();
            // 创建一个居中格式
            titleStyle.setAlignment(HorizontalAlignment.CENTER);
            // 字体
            HSSFFont titleFont = workbook.createFont();
            titleFont.setFontName("宋体");
            // 设置字体大小
            titleFont.setFontHeightInPoints((short) 14);
            titleFont.setBold(true);// 粗体显示
            titleStyle.setFont(titleFont);

            // 创建表头格式
            HSSFCellStyle headStyle = workbook.createCellStyle();
            // 创建一个居中格式
            headStyle.setAlignment(HorizontalAlignment.CENTER);
            // 字体
            HSSFFont headFont = workbook.createFont();
            headFont.setFontName("宋体");
            // 设置字体大小
            headFont.setFontHeightInPoints((short) 12);
            headFont.setBold(true);// 粗体显示
            headStyle.setFont(headFont);
            // 设置边框
            headStyle.setBorderBottom(BorderStyle.THIN); // 下边框
            headStyle.setBorderLeft(BorderStyle.THIN);// 左边框
            headStyle.setBorderTop(BorderStyle.THIN);// 上边框
            headStyle.setBorderRight(BorderStyle.THIN);// 右边框

            // 创建单元格格式，并设置值表头 设置表头居中
            HSSFCellStyle style = workbook.createCellStyle();
            // 创建一个居中格式
            style.setAlignment(HorizontalAlignment.CENTER);
            // 自动换行
            style.setWrapText(true);
            HSSFFont font = workbook.createFont();
            font.setFontName("宋体");
            // 设置字体大小
            font.setFontHeightInPoints((short) 12);
            style.setFont(font);
            // 设置边框
            style.setBorderBottom(BorderStyle.THIN); // 下边框
            style.setBorderLeft(BorderStyle.THIN);// 左边框
            style.setBorderTop(BorderStyle.THIN);// 上边框
            style.setBorderRight(BorderStyle.THIN);// 右边框

            // 创建工作表
            HSSFSheet sheet = workbook.createSheet(titleName);
            // 设置列宽

            sheet.setColumnWidth(0, 2000);

            /**
             * 往Excel中写新数据
             */
            // 标题
            Row titleRow = sheet.createRow(0);

            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellStyle(titleStyle);
            titleCell.setCellValue(titleName);

            Row row0 = sheet.createRow(0);
            Cell cell0 = row0.createCell(0);
            cell0.setCellValue("id");
            //cell0.setCellStyle(style);

            Cell cell1 = row0.createCell(1);
            cell1.setCellValue("create_time");
            //cell1.setCellStyle(style);

            Cell cell2 = row0.createCell(2);
            cell2.setCellValue("source");
            //cell2.setCellStyle(style);

            Cell cell3 = row0.createCell(3);
            cell3.setCellValue("t_time");
            //cell3.setCellStyle(style);

            Cell cell4 = row0.createCell(4);
            cell4.setCellValue("url");
            //cell4.setCellStyle(style);

            Cell cell5 = row0.createCell(5);
            cell5.setCellValue("title");
            //cell5.setCellStyle(style);

            // 得到要插入的每一条记录
            for (int i = 0; i < dataList.size(); i++) {
                // 创建一行：从第二行开始，跳过属性列
                Row row = sheet.createRow(i + 1);

                String id = dataList.get(i).getId() + "";
                String createTime = DateUtil.format(dataList.get(i).getCreateTime(), "yyyy-MM-dd HH:mm:ss");
                String source = dataList.get(i).getSource();
                String ttime = DateUtil.format(dataList.get(i).getTTime(), "yyyy-MM-dd HH:mm:ss");
                String url = dataList.get(i).getUrl();
                String title = dataList.get(i).getTitle();
                for (int k = 0; k <= columnNumCount; k++) {
                    // 在一行内循环
                    Cell first = row.createCell(0);
                    first.setCellValue(id);
                    //first.setCellStyle(style);

                    Cell second = row.createCell(1);
                    second.setCellValue(createTime);
                    //second.setCellStyle(style);

                    Cell third = row.createCell(2);
                    third.setCellValue(source);
                    //third.setCellStyle(style);

                    Cell fourth = row.createCell(3);
                    fourth.setCellValue(ttime);
                    //fourth.setCellStyle(style);

                    Cell fiveth = row.createCell(4);
                    fiveth.setCellValue(url);
                    //fiveth.setCellStyle(style);

                    Cell sixth = row.createCell(5);
                    sixth.setCellValue(title);
                    //sixth.setCellStyle(style);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }
}
