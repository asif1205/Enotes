/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enotes.dto;

/**
 *
 * @author acer
 */
public class EnotesAddingNotedto {

        public void setPassword(String password) {
        this.password=password;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public EnotesAddingNotedto() {
    }

    public EnotesAddingNotedto(String title,String name, String subject, String content,String password) {
        this.name=name;
        this.title = title;
        this.subject = subject;
        this.content = content;
        this.password=password;
    }

    public String getName() {
        return name;
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
    
    public String getPassword() {
        return password;
    }
    private String name;
    private String title;
    private String subject;
    private String content;
    private String password;
    
}
