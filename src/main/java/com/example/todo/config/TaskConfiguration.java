package com.example.todo.config;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskConfiguration extends Configuration {

    private String dbUrl;
    private String dbUsername;
    private String dbPassword;

    // Hibernate configuration
    private String dialect;
    private String hbm2ddl;
    private boolean showSql;
    private boolean formatSql;

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

    // Hibernate configuration properties
    @JsonProperty
    public String getDialect() {
        return dialect;
    }

    @JsonProperty
    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    @JsonProperty
    public String getHbm2ddl() {
        return hbm2ddl;
    }

    @JsonProperty
    public void setHbm2ddl(String hbm2ddl) {
        this.hbm2ddl = hbm2ddl;
    }

    @JsonProperty
    public boolean isShowSql() {
        return showSql;
    }

    @JsonProperty
    public void setShowSql(boolean showSql) {
        this.showSql = showSql;
    }

    @JsonProperty
    public boolean isFormatSql() {
        return formatSql;
    }

    @JsonProperty
    public void setFormatSql(boolean formatSql) {
        this.formatSql = formatSql;
    }
}
