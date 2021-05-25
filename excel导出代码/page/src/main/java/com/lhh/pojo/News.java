package com.lhh.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "news")
public class News
{
	@Id
	private String id;
	private Date createTime;
	private String source;
	private Date tTime;
	private String title;
	private String url;
}
