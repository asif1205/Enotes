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
public class EnotesRegistrationdto {

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public EnotesRegistrationdto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public EnotesRegistrationdto() {
    }
    private String name;
    private String email;
    private String password;
}
