package com.nixalevel.servlets.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {
    private String ip;
    private String header;
    private Date date;

    public User(String ip, String header, Date date) {
        this.ip = ip;
        this.header = header;
        this.date = date;
    }
}
