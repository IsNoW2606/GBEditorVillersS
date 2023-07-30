package org.helmo.gbeditor.model;

public class Book {
    private String isbn;
    private String title;
    private String summary;
    private boolean isPublish;

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
        this.summary = "Ce livre ne contient pas de résumé.";
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public boolean isPublish() {
        return isPublish;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setPublish(boolean publish) {
        isPublish = publish;
    }
}
