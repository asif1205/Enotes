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
public class EnotesCheckingPassworddto {
    public void setPassword() {
        
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public EnotesCheckingPassworddto(String password) {
        this.password = password;
    }
    private String password;
}
