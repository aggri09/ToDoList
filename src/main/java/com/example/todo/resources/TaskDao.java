package com.example.todo.resources;

import com.example.todo.TaskConfiguration;
import com.example.todo.core.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {

    private final String dbUrl;
    private final String dbUsername;
    private final String dbPassword;

    public TaskDao(TaskConfiguration config) {
        this.dbUrl = config.getDbUrl();
        this.dbUsername = config.getDbUsername();
        this.dbPassword = config.getDbPassword();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }

    // Fetch all tasks
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                tasks.add(new Task(
                        rs.getLong("id"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getDate("start_date"),
                        rs.getDate("target_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    // Fetch task by ID
    public Task getTaskById(long id) {
        String query = "SELECT * FROM tasks WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Task(
                        rs.getLong("id"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getDate("start_date"),
                        rs.getDate("target_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Create a new task
    public void createTask(Task task) {
        String query = "INSERT INTO tasks (description, status, start_date, target_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, task.getDescription());
            stmt.setString(2, task.getStatus());
            stmt.setDate(3, new java.sql.Date(task.getStartDate().getTime()));
            stmt.setDate(4, new java.sql.Date(task.getTargetDate().getTime()));
            stmt.executeUpdate();

            // Retrieve generated ID
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                task.setId(generatedKeys.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an existing task
    public Task updateTask(long id, Task updatedTask) {
        String query = "UPDATE tasks SET description = ?, status = ?, start_date = ?, target_date = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, updatedTask.getDescription());
            stmt.setString(2, updatedTask.getStatus());
            stmt.setDate(3, new java.sql.Date(updatedTask.getStartDate().getTime()));
            stmt.setDate(4, new java.sql.Date(updatedTask.getTargetDate().getTime()));
            stmt.setLong(5, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                updatedTask.setId(id);
                return updatedTask;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Delete a task
    public boolean deleteTask(long id) {
        String query = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setLong(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
