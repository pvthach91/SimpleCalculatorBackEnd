package com.pvthach.calculator.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by THACH-PC
 */

@Table(name = "HISTORY")
@Entity(name = "History")
public class History implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "CREATED_BY")
    private User createdBy;

    public History() {
    }

    public History(String content, User createdBy) {
        this.content = content;
        this.date = new Date();
        this.createdBy = createdBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
