package com.amela.Models;

import com.amela.Controllers.CustomeAnnotation.UniqueAccount;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name= "login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @NotEmpty(message = "Khong duoc de trong truong nay")
    @Email(message = "Ban phai nhap dung dinh dang email VD: abc@gmail.com")
    @Size(max = 30, message = "Do dai toi da la 30 ky tu")
    @Column(unique = true)
    @UniqueAccount(message = "Tai khoan da ton tai!")
    private String email;

//    @Pattern(regexp = "[A-Za-z\\d@$!%*#?&^_-]{6,20}$", message = "Mat khau khong ho tro co dau va dau cach")
    @Size(min = 6, message = "Mat khau phai tren 6 ky tu")
    private String password;

    @NotEmpty(message = "Khong duoc de trong truong nay")
    @Pattern(regexp = "([^0-9])+", message = "Ban chi duoc nhap chu")
    @Size(min = 1, max = 10, message = "Ten cho phep chua toi da 10 ky tu")
    private String name;

    public Login() {
    }

    public Login(String email, String password, String name) {
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
