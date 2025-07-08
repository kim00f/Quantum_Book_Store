package models;

public class EBook extends Book {
    private String fileType;

    public EBook(String isbn, String title, String authorName, int publishedYear,
                 double price, String fileType) {
        super(isbn, title, authorName, publishedYear, price);
        this.fileType = fileType;
    }

    @Override
    public String getType() {
        return "E-Book";
    }

    //assume that ebooks are alawys available

    @Override
    public boolean canBeSold() {
        return true;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}