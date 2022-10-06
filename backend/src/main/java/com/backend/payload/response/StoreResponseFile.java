package com.backend.payload.response;

public class StoreResponseFile {
    private String writer_username;
    public String getWriter_username() {
        return writer_username;
    }

    public void setWriter_username(String writer_username) {
        this.writer_username = writer_username;
    }

    private String writer_name;
    public String getWriter_name() {
        return writer_name;
    }

    public void setWriter_name(String writer_name) {
        this.writer_name = writer_name;
    }

    private String title;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String contents;
    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    private String url;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public StoreResponseFile(String writer_username, String writer_name,
                        String title, String contents, String url) {
        this.writer_username = writer_username;
        this.writer_name = writer_name;
        this.title = title;
        this.contents = contents;
        this.url = url;
    }
    
    


    
}
