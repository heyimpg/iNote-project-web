package com.amela.Models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "noteType")
public class NoteType {
    @Id
    @Column(name="noteType_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Size(min = 1, max = 50, message = "Danh mục không được để trống và tối đa 50 ký tự")
    private String name;

    @Size(max = 50, message = "Trường này cho phép tối đa 50 ký tự")
    private String description;

    @OneToMany(targetEntity = Note.class)
    private List<Note> notes;

    public NoteType() {
    }

    public NoteType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "NoteType{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", description='" + description +
                '}';
    }
}
