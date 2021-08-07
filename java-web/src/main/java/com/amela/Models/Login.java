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

    @NotEmpty(message = "Không được để trống trường này")
    @Email(message = "Bạn phải nhập đúng định dạng email VD: abc@gmail.com")
    @Size(max = 30, message = "Độ dài tối đa là 30 ký tự")
    @Column(unique = true)
    @UniqueAccount(message = "Tài khoản đã tồn tại!")
    private String email;

    @Size(min = 6, max = 20, message = "Mật khẩu phải từ 6-20 ký tự")
    private String password;

    @NotEmpty(message = "Không được để trống trường này")
    @Pattern(regexp = "[a-zA-Z]+", message = "Bạn chỉ được nhập chữ")
    @Size(min = 1, max = 10, message = "Tên cho phép chứa tối đa 10 ký tự")
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
