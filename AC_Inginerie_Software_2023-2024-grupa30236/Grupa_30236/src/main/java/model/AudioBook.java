package model;


import java.util.Date;

public class AudioBook {
    private Long id;
    private String author;
    private String title;
    private int runTime; // durata in minute
    private Date publishedDate;

    public AudioBook(Long id, String author, String title, int runTime, Date publishedDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.runTime = runTime;
        this.publishedDate = publishedDate;
    }


    public AudioBook() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
}