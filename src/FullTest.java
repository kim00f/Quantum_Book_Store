public class FullTest {
    public static void main(String[] args) {
        QuantumBookstore store = new QuantumBookstore();

        System.out.println("=== Testing Quantum Bookstore ===\n");

        // Test 1: Adding books
        System.out.println("Test 1: Adding Books");
        System.out.println("--------------------");
        store.addPaperBook("ISBN001", "The Great Gatsby", "F. Scott Fitzgerald", 1925, 15.99, 50);
        store.addEBook("ISBN002", "1984", "George Orwell", 1949, 9.99, "PDF");
        store.addShowcaseBook("ISBN003", "Demo Book", "Demo Author", 2020);
        store.addPaperBook("ISBN004", "To Kill a Mockingbird", "Harper Lee", 1960, 12.99, 30);
        store.addEBook("ISBN005", "Digital Fortress", "Dan Brown", 1998, 8.99, "EPUB");
        store.addPaperBook("ISBN006", "Ancient History", "Old Writer", 1950, 5.99, 10);

        // Display inventory after adding
        System.out.println("\nInventory after adding books:");
        store.displayInventory();

        // Test 2: Buying books
        System.out.println("\n\nTest 2: Buying Books");
        System.out.println("--------------------");

        try {
            // Buy paper book
            double amount1 = store.buyBook("ISBN001", 2, "customer@email.com", "123 Main St");
            System.out.println("Transaction successful: Paid $" + amount1);

            // Buy e-book
            double amount2 = store.buyBook("ISBN002", 1, "ebook@email.com", "Not needed");
            System.out.println("Transaction successful: Paid $" + amount2);

            // Try to buy showcase book (should fail)
            System.out.println("\nTrying to buy showcase book:");
            store.buyBook("ISBN003", 1, "test@email.com", "Test Address");
        } catch (RuntimeException e) {
            System.out.println("Transaction failed as expected");
        }

        // Test 3: Insufficient stock
        System.out.println("\n\nTest 3: Testing Insufficient Stock");
        System.out.println("----------------------------------");
        try {
            store.buyBook("ISBN001", 100, "bulk@email.com", "Bulk Address");
        } catch (RuntimeException e) {
            System.out.println("Transaction failed as expected");
        }

        // Test 4: Non-existent book
        System.out.println("\n\nTest 4: Buying Non-existent Book");
        System.out.println("--------------------------------");
        try {
            store.buyBook("ISBN999", 1, "nobody@email.com", "Nowhere");
        } catch (RuntimeException e) {
            System.out.println("Transaction failed as expected");
        }

        // Test 5: Removing outdated books
        System.out.println("\n\nTest 5: Removing Outdated Books (older than 70 years)");
        System.out.println("-----------------------------------------------------");
        store.removeOutdatedBooks(70);

        // Display final inventory
        System.out.println("\n\nFinal Inventory:");
        System.out.println("----------------");
        store.displayInventory();

        // Test 6: Buy more books after removal
        System.out.println("\n\nTest 6: Additional Purchases");
        System.out.println("----------------------------");
        try {
            double amount3 = store.buyBook("ISBN004", 5, "reader@email.com", "456 Oak Ave");
            System.out.println("Transaction successful: Paid $" + amount3);

            double amount4 = store.buyBook("ISBN005", 1, "digital@email.com", "Not needed");
            System.out.println("Transaction successful: Paid $" + amount4);
        } catch (RuntimeException e) {
            System.out.println("Transaction failed");
        }

        // Show final state
        System.out.println("\n\nFinal Inventory State:");
        System.out.println("----------------------");
        store.displayInventory();
    }
}