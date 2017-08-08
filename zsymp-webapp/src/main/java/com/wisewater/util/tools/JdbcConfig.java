package com.wisewater.util.tools;

public class JdbcConfig {

	private String driverClass;
	
	private String jdbcUrl;
	
	private String user;
	//
	private String password;
	
	private String initialPoolSize;
	
	private int maxPoolSize;
	
	private String minPoolSize;
	
	private String maxIdleTime;
	
	private String maxStatements;
	
	private String acquireIncrement;
	
	private String schema;

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInitialPoolSize() {
        return initialPoolSize;
    }

    public void setInitialPoolSize(String initialPoolSize) {
        this.initialPoolSize = initialPoolSize;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public String getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(String minPoolSize) {
        this.minPoolSize = minPoolSize;
    }

    public String getMaxIdleTime() {
        return maxIdleTime;
    }

    public void setMaxIdleTime(String maxIdleTime) {
        this.maxIdleTime = maxIdleTime;
    }

    public String getMaxStatements() {
        return maxStatements;
    }

    public void setMaxStatements(String maxStatements) {
        this.maxStatements = maxStatements;
    }

    public String getAcquireIncrement() {
        return acquireIncrement;
    }

    public void setAcquireIncrement(String acquireIncrement) {
        this.acquireIncrement = acquireIncrement;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
	
	
}
