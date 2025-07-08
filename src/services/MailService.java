package services;

public class MailService {

    public static void sendEmail(String isbn, String email, String fileType) {
        System.out.println("Quantum book store: Sending " + fileType +
                " book with ISBN " + isbn + " to email: " + email);
    }


}