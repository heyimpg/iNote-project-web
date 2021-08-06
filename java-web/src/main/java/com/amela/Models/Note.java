package com.amela.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "note")
public class Note {
    @Id
    @Column(name="note_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Size(min = 1, max = 50, message = "Tiêu đề không được để trống và tối đa 50 ký tự")
    private String title;

    @Size(min=1, message = "Không được để trống trường này")
    @Pattern(regexp = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))", message = "Bạn phải nhập đúng định dạng YYYY-MM-DD")
    private String time;

    private String content;

    @ManyToOne
    @JoinColumn(name = "noteType_id")
    private NoteType type;

    public Note() {
    }

    public Note(String time, String title, String content) {
        this.time = time;
        this.title = title;
        this.content = content;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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
