package org.example;

import org.example.notes.Note;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        MySQLDBConnection connection = new MySQLDBConnection();
        connection.createDataBase();
        connection.useDataBase();
        connection.createTable();
        //connection.addNewNote("справка", "получить справку");
        List<Note> notes = connection.getAllNotes();
        for(Note note :notes) {
            System.out.println(note);
        }
    }
}