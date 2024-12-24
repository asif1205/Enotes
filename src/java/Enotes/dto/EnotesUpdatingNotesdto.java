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
public class EnotesUpdatingNotesdto {
    private String name;
    private String password;
    private String oldTitle;
    private String newTitle;
    private String oldSubject;
    private String newSubject;
    private String oldContent;
    private String newContent;

    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setOldTitle(String oldTitle) {
        this.oldTitle = oldTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public void setOldSubject(String oldSubject) {
        this.oldSubject = oldSubject;
    }

    public void setNewSubject(String newSubject) {
        this.newSubject = newSubject;
    }

    public void setOldContent(String oldContent) {
        this.oldContent = oldContent;
    }

    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    
    public String getOldTitle() {
        return oldTitle;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public String getOldSubject() {
        return oldSubject;
    }

    public String getNewSubject() {
        return newSubject;
    }

    public String getOldContent() {
        return oldContent;
    }

    public String getNewContent() {
        return newContent;
    }

    public EnotesUpdatingNotesdto(String oldTitle, String newTitle, String oldSubject, String newSubject, String oldContent, String newContent,String name,String password) {
        this.oldTitle = oldTitle;
        this.newTitle = newTitle;
        this.oldSubject = oldSubject;
        this.newSubject = newSubject;
        this.oldContent = oldContent;
        this.newContent = newContent;
        this.name=name;
        this.password=password;
    }

    public EnotesUpdatingNotesdto() {
    }
}
