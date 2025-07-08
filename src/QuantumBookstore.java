import models.*;
import services.*;
import java.util.*;
import java.time.Year;

public class QuantumBookstore {
    private Map<String, Book> inventory;

    public QuantumBookstore() {
        this.inventory = new HashMap<>();
    }

    // Add books methods
    public void addPaperBook(String isbn, String title, String authorName,
                             int publishedYear, double price, int stock) {
        PaperBook book = new PaperBook(isbn, title, authorName, publishedYear, price, stock);
        inventory.put(isbn, book);
        System.out.println("Quantum book store: Added paper book - " + title);
    }

    public void addEBook(String isbn, String title, String authorName,
                         int publishedYear, double price, String fileType) {
        EBook book = new EBook(isbn, title, authorName, publishedYear, price, fileType);
        inventory.put(isbn, book);
        System.out.println("Quantum book store: Added e-book - " + title);
    }

    public void addShowcaseBook(String isbn, String title, String authorName, int publishedYear) {
        ShowcaseBook book = new ShowcaseBook(isbn, title, authorName, publishedYear);
        inventory.put(isbn, book);
        System.out.println("Quantum book store: Added showcase book - " + title);
    }

    // Remove outdated books
    public List<Book> removeOutdatedBooks(int yearsOld) {
        List<Book> removedBooks = new ArrayList<>();
        int currentYear = Year.now().getValue();

        // Create a list of ISBNs to remove (to avoid concurrent modification)
        List<String> toRemove = new ArrayList<>();

        for (Book book : inventory.values()) {
            if (currentYear - book.getPublishedYear() > yearsOld) {
                toRemove.add(book.getIsbn());
                removedBooks.add(book);
            }
        }

        // Remove the books
        for (String isbn : toRemove) {
            inventory.remove(isbn);
            Book book = removedBooks.stream()
                    .filter(b -> b.getIsbn().equals(isbn))
                    .findFirst().orElse(null);
            if (book != null) {
                System.out.println("Quantum book store: Removed outdated book - " +
                        book.getTitle() + " (published " + book.getPublishedYear() + ")");
            }
        }

        return removedBooks;
    }

    // Buy book method
    public double buyBook(String isbn, int quantity, String email, String address) {
        Book book = inventory.get(isbn);

        if (book == null) {
            System.out.println("Quantum book store: Error - Book with ISBN " + isbn + " not found");
            throw new RuntimeException("Book not found");
        }

        if (!book.canBeSold()) {
            System.out.println("Quantum book store: Error - " + book.getTitle() + " is not for sale");
            throw new RuntimeException("Book not for sale");
        }

        double totalAmount = book.getPrice() * quantity;

        if (book instanceof PaperBook) {
            PaperBook paperBook = (PaperBook) book;
            if (!paperBook.hasStock(quantity)) {
                System.out.println("Quantum book store: Error - Insufficient stock. Available: " +
                        paperBook.getStock() + ", Requested: " + quantity);
                throw new RuntimeException("Insufficient stock");
            }
            paperBook.reduceStock(quantity);
            ShippingService.ship(isbn, address, quantity);
            System.out.println("Quantum book store: Sold " + quantity + " paper book(s) - " +
                    book.getTitle() + " for $" + totalAmount);
        } else if (book instanceof EBook) {
            EBook eBook = (EBook) book;
            MailService.sendEmail(isbn, email, eBook.getFileType());
            System.out.println("Quantum book store: Sold e-book - " + book.getTitle() +
                    " for $" + totalAmount);
        }

        return totalAmount;
    }

    // Helper method to display inventory
    public void displayInventory() {
        System.out.println("Quantum book store: Current Inventory:");
        for (Book book : inventory.values()) {
            String stockInfo = "";
            if (book instanceof PaperBook) {
                stockInfo = ", Stock: " + ((PaperBook) book).getStock();
            }
            System.out.println("  - " + book.getTitle() + " by " + book.getAuthorName() +
                    " (" + book.getType() + ", ISBN: " + book.getIsbn() +
                    ", Year: " + book.getPublishedYear() +
                    ", Price: $" + book.getPrice() + stockInfo + ")");
        }
    }

    // Helper method to check if book exists
    public boolean hasBook(String isbn) {
        return inventory.containsKey(isbn);
    }
}