package com.backend.payload.response;

public class PhotoResponse {
    private String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String type;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PhotoResponse() {

    }

    public PhotoResponse(String id, String type, String url) {
        this.id = id;
        this.type = type;
        this.url = url;
    }
}
