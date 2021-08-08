package com.amela.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "note")
public class Note {
    @Id
    @Column(name="note_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Size(min = 1, max = 50, message = "Tieu de khong duoc de trong va chi chua toi da 50 ky tu")
    private String title;

//    @Size(min=1, message = "Không được để trống trường này")
//    @Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))", message = "Bạn phải nhập đúng định dạng YYYY-MM-DD")
    private LocalDate time;

    private String content;

    @ManyToOne
    @JoinColumn(name = "noteType_id")
    private NoteType type;

    public Note() {
    }

    public Note(String title, LocalDate time, String content) {
        this.title = title;
        this.time = time;
        this.content = content;
    }

    public Note(int ID, String title, LocalDate time, String content) {
        this.ID = ID;
        this.title = title;
        this.time = time;
        this.content = content;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NoteType getType() {
        return type;
    }

    public void setType(NoteType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Note{" +
                "ID=" + ID +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type.getName() +
                '}';
    }
}
