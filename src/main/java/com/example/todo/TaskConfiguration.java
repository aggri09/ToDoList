package com.example.todo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import javax.validation.constraints.NotEmpty;

public class TaskConfiguration extends Configuration {

    @NotEmpty
    private String dbUrl;

    @NotEmpty
    private String dbUsername;

    @NotEmpty
    private String dbPassword;

    @JsonProperty
    public String getDbUrl() {
        return dbUrl;
    }

    @JsonProperty
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    @JsonProperty
    public String getDbUsername() {
        return dbUsername;
    }

    @JsonProperty
    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    @JsonProperty
    public String getDbPassword() {
        return dbPassword;
    }

    @JsonProperty
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
}
