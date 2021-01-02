package org.acme.getting.started.config;

import io.quarkus.arc.config.ConfigProperties;

@ConfigProperties(prefix = "couchbase")
public class CouchbaseConfiguration {
    private String urls;
    private String bucket;
    private String username;
    private String password;

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
