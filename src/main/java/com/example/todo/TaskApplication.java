package com.example.todo;

import com.example.todo.resources.TaskDao;
import com.example.todo.resources.TaskResource;
import io.dropwizard.Application;

import io.dropwizard.setup.Environment;

public class TaskApplication extends Application<TaskConfiguration> {

    public static void main(String[] args) throws Exception {
        new TaskApplication().run(args);
    }

    @Override
    public void run(TaskConfiguration configuration, Environment environment) {
        // Initialize DAO
        TaskDao taskDao = new TaskDao(configuration);

        // Register resources
        environment.jersey().register(new TaskResource(taskDao));
    }
}
