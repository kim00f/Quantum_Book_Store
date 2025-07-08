package services;

public class ShippingService {

    public static void ship(String isbn, String address, int quantity) {
        System.out.println("Quantum book store: Shipping " + quantity +
                " book(s) with ISBN " + isbn + " to address: " + address);
    }

}