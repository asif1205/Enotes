/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enotes.dto;

import java.util.Date;

/**
 *
 * @author acer
 */
public class EnotesShowAllNotesdto {

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setselfId(int selfId){
        this.selfId=selfId;
    }
    public void setD(Date d) {
        this.d = d;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public Date getD() {
        return d;
    }
    public int getselfId() {
        return selfId;
    }

    public EnotesShowAllNotesdto(String title, String subject, String content, Date d,int selfId) {
        this.title = title;
        this.subject = subject;
        this.content = content;
        this.d = d;
        this.selfId=selfId;
    }

    public EnotesShowAllNotesdto() {
    }
    private String title;
    private String subject;
    private String content;
    private Date d;
    int selfId;
}
