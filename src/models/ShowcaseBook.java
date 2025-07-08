package models;

public class ShowcaseBook extends Book {

    public ShowcaseBook(String isbn, String title, String authorName, int publishedYear) {
        super(isbn, title, authorName, publishedYear, 0.0);
    }

    @Override
    public String getType() {
        return "Showcase/Demo Book";
    }

    @Override
    public boolean canBeSold() {
        return false;
    }
}