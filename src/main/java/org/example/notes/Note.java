package org.example.notes;

import java.util.Date;

public class Note {
    private int id;
    private String title;
    private String body;
    private Date creationDate;

    public Note() {

    }

    public Note(String title, String body) {
        creationDate = new Date();
        this.title = title;
        this.body = body;
    }

    public Note(int id, String title, String body, Date creationDate) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreationDate() {
        return  creationDate;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
