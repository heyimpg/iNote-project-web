package com.amela.Models;

import com.amela.Controllers.CustomeAnnotation.UniqueAccount;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name= "login")
public class Login_Clone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    private String email;

    @Size(min = 6, message = "Mat khau phai tren 6 ky tu")
    private String password;

    @NotEmpty(message = "Khong duoc de trong truong nay")
    @Pattern(regexp = "([^0-9])+", message = "Ban chi duoc nhap chu")
    @Size(min = 1, max = 10, message = "Ten cho phep chua toi da 10 ky tu")
    private String name;

    public Login_Clone() {
    }

    public Login_Clone(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Login{" +
                "ID=" + ID +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
