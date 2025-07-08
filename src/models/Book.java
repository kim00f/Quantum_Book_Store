package models;

public abstract class Book {
    protected String isbn;
    protected String title;
    protected String authorName;
    protected int publishedYear;
    protected double price;

    public Book(String isbn, String title, String authorName, int publishedYear, double price) {
        this.isbn = isbn;
        this.title = title;
        this.authorName = authorName;
        this.publishedYear = publishedYear;
        this.price = price;
    }

    public abstract String getType();
    public abstract boolean canBeSold();

    // Getters and setters
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public double getPrice() {
        return price;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}