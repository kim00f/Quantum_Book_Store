package models;

public class PaperBook extends Book {
    private int stock;

    public PaperBook(String isbn, String title, String authorName, int publishedYear,
                     double price, int stock) {
        super(isbn, title, authorName, publishedYear, price);
        this.stock = stock;
    }

    @Override
    public String getType() {
        return "Paper Book";
    }

    @Override
    public boolean canBeSold() {
        return stock > 0;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void reduceStock(int quantity) {
        if (this.stock >= quantity) {
            this.stock -= quantity;
        }
    }

    public boolean hasStock(int quantity) {
        return this.stock >= quantity;
    }
}