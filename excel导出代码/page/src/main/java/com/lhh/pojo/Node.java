package com.lhh.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "news")
public class Node {
    @Id
    private String id;
    private String source;
    private String title;
    private Date tTime;
    private String url;
}
