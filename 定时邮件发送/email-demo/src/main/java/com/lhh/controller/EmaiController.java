package com.lhh.controller;


import com.lhh.constant.MailConst;
import com.lhh.entity.EmailEntity;
import com.lhh.util.MailSenderUtil;
import com.lhh.util.Result;
import com.lhh.util.StatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("email")
@CrossOrigin
public class EmaiController {

    @RequestMapping
    public Result tests(){
        return new Result(true, StatusCode.OK,"成功！");
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result test(@RequestBody Map<String,String> map){
        try{
            EmailEntity emailEntity = new EmailEntity();
            ArrayList<String> emailArray = new ArrayList<>();
            //测试，收取邮件的邮箱，可以填写自己的发送邮件的邮箱
            emailArray.add(map.get("email"));
            emailEntity.setToList(emailArray);
            emailEntity.setSubject(map.get("title"));
            emailEntity.setContext(map.get("content"));
            emailEntity.setUserName(MailConst.USER_NAME);
            emailEntity.setAuth(MailConst.MAIL_SMTP_AUTH);
            emailEntity.setPassword(MailConst.PWD_CODE);
            emailEntity.setHost(MailConst.MAIL_HOST);
            MailSenderUtil.sendMailToUserArray(emailEntity);
            return new Result(true, StatusCode.OK,"发送成功！");
        }catch (Exception e){
            return new Result(false,StatusCode.ERROR,"发送失败！");
        }


    }
}
