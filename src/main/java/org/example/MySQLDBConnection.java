package org.example;
import org.example.notes.Note;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySQLDBConnection implements DBConnection {
    private String url = "jdbc:mysql://localhost:3306/";
    private String userName = "root";
    private String password = "qwer";

    java.sql.Connection connection = DriverManager.getConnection(url, userName, password);

    public MySQLDBConnection() throws SQLException {
    }

    @Override
    public void connect() {
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            System.out.println("Не удалось подключиться к БД");
        }

    }

    public void createDataBase() {

        String createDB = "CREATE DATABASE IF NOT EXISTS notesDB";
        try (PreparedStatement statement = connection.prepareStatement(createDB)) {
                statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void useDataBase() {
        String useDB = "USE notesDB";
        try (PreparedStatement statement = connection.prepareStatement(useDB)) {
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Не удалось использовать БД");
        }
    }

    public void createTable() {
        Date creationDate = new Date();
        String createTable = "CREATE TABLE IF NOT EXISTS notes (id INT AUTO_INCREMENT PRIMARY KEY, " +
                "title VARCHAR(100), body VARCHAR(255), creationDate DATETIME);";
        try (PreparedStatement statement = connection.prepareStatement(createTable)) {
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Не удалось открыть таблицу");
        }
    }

    public void addNewNote(String title, String body) {
        Date date = new Date();
        String newNote = "INSERT INTO notes (title, body, creationDate) VALUES (?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(newNote)) {
            statement.setString(1, title);
            statement.setString(2, body);
            statement.setObject(3, LocalDateTime.now());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Не удалось добавить заметку");
            e.printStackTrace();
        }
    }

    public List<Note> getAllNotes() {
        List<Note> allNotes = new ArrayList<>();
        String findNote = "SELECT * FROM notes";
        try (PreparedStatement statement = connection.prepareStatement(findNote)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String body = rs.getString("body");
                Timestamp dt = rs.getTimestamp(4);
                allNotes.add(new Note(id, title, body, dt));
            }

        } catch (SQLException e) {
            System.out.println("Не удалось прочесть все заметки из БД");
            e.printStackTrace();
        }
        return allNotes;
    }
}
