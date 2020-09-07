package com.connector.beta.dto;

import org.springframework.core.io.ByteArrayResource;

public class SearchImageDto {
    private String firstName;
    private String lastName;
    private String url;
    private String type;
    private String size;

    public SearchImageDto() {
    }

    public SearchImageDto(String firstName, String lastName, String url, String type, String size) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.url = url;
        this.type = type;
        this.size = size;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
