package com.example.todo.resources;

import com.example.todo.core.Task;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    private final TaskDao taskDao;

    public TaskResource(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    // Retrieve all tasks or a task by ID
    @GET
    public Response getTasks(@QueryParam("id") Long id) {
        if (id == null) {
            List<Task> tasks = taskDao.getAllTasks();
            return Response.ok(tasks).build();
        } else {
            Task task = taskDao.getTaskById(id);
            if (task == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Task with ID " + id + " not found.")
                        .build();
            }
            return Response.ok(task).build();
        }
    }

    // Create a new task
    @POST
    public Response createTask(Task task) {
        if (task.getDescription() == null || task.getStatus() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Task description and status are required.")
                    .build();
        }
        task.setStatus("TODO");
        taskDao.createTask(task);
        return Response.status(Response.Status.CREATED)
                .entity(task)
                .build();
    }

    // Update an existing task
    @PUT
    @Path("/{id}")
    public Response updateTask(@PathParam("id") long id, Task updatedTask) {
        Task existingTask = taskDao.getTaskById(id);
        if (existingTask == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Task with ID " + id + " not found.")
                    .build();
        }

        updatedTask.setId(id); // Ensure the updated task uses the correct ID
        Task updated = taskDao.updateTask(id, updatedTask);
        return Response.ok(updated).build();
    }

    // Delete a task
    @DELETE
    @Path("/{id}")
    public Response deleteTask(@PathParam("id") long id) {
        boolean isDeleted = taskDao.deleteTask(id);
        if (!isDeleted) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Task with ID " + id + " not found.")
                    .build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
