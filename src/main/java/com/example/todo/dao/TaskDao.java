package com.example.todo.dao;

import com.example.todo.config.TaskConfiguration;
import com.example.todo.models.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TaskDao {

    private final SessionFactory sessionFactory;

    public TaskDao(TaskConfiguration config) {
        // Initialize Hibernate configuration
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", config.getDbUrl());
        configuration.setProperty("hibernate.connection.username", config.getDbUsername());
        configuration.setProperty("hibernate.connection.password", config.getDbPassword());
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");

        // Register annotated classes
        configuration.addAnnotatedClass(Task.class);

        this.sessionFactory = configuration.buildSessionFactory();
    }

    // Fetch all tasks
    public List<Task> getAllTasks() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Task", Task.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Fetch task by ID
    public Task getTaskById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Task.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Create a new task
    public void createTask(Task task) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Update an existing task
    public Task updateTask(long id, Task updatedTask) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Task task = session.get(Task.class, id);
            if (task != null) {
                task.setDescription(updatedTask.getDescription());
                task.setStatus(updatedTask.getStatus());
                task.setStartDate(updatedTask.getStartDate());
                task.setTargetDate(updatedTask.getTargetDate());
                session.update(task);
                transaction.commit();
                return task;
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return null;
    }

    // Delete a task
    public boolean deleteTask(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Task task = session.get(Task.class, id);
            if (task != null) {
                session.delete(task);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }

    // Close the SessionFactory when done
    public void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
