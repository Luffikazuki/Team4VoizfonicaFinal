package com.example.blog.Model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
@Document
public class Create {
    @Id
    private String id;

   private Date date=new Date();
    private String name;
    private String title;
    private String summary;
    private String message;
}
