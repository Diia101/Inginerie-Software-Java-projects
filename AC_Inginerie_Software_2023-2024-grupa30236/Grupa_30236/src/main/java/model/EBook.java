package model;

import java.util.Date;
public class EBook {
    private Long id;
    private String author;
    private String title;
    private String format;
    private Date publishedDate;

    public EBook(Long id, String author, String title, String format, Date publishedDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.format = format;
        this.publishedDate = publishedDate;
    }

    public EBook() {

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

    public void setFormat(String format) {
        this.format = format;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
}