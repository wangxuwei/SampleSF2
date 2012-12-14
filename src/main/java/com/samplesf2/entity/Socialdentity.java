package com.samplesf2.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "socialdentity")
@javax.persistence.SequenceGenerator(name = "SEQ_STORE", allocationSize = 1, sequenceName = "socialdentity_id_seq")
public class Socialdentity extends BaseEntity {
    private Long   user_id;
    private String token;
    @Column(name="token_date")
    private Date tokenDate;
    private String service;
    
    
    public Long getUser_id() {
        return user_id;
    }
    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Date getTokenDate() {
        return tokenDate;
    }
    public void setTokenDate(Date tokenDate) {
        this.tokenDate = tokenDate;
    }
    public String getService() {
        return service;
    }
    public void setService(String service) {
        this.service = service;
    }
    
    
}
