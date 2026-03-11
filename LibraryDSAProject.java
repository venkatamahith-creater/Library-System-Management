import java.util.*;

class Book {
    int id;
    String title;
    boolean available;

    Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.available = true;
    }

    public String toString() {
        return id + " | " + title + " | " + (available ? "Available" : "Issued");
    }
}

public class LibraryDSAProject {

    static Scanner sc = new Scanner(System.in);

    // Linked List for storing books
    static LinkedList<Book> books = new LinkedList<>();

    // Hashing for quick search
    static HashMap<Integer, Book> bookMap = new HashMap<>();

    // Queue for book requests
    static Queue<String> requestQueue = new LinkedList<>();

    // Stack for return history
    static Stack<String> returnStack = new Stack<>();

    // Priority queue for reservations
    static PriorityQueue<Integer> reservationPQ = new PriorityQueue<>();

    // ADD BOOK
    static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();

        Book b = new Book(id, title);

        books.add(b);
        bookMap.put(id, b);

        System.out.println("Book added successfully.");
    }

    // DISPLAY BOOKS
    static void displayBooks() {

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\nBook List:");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    // SEARCH BOOK
    static void searchBook() {

        System.out.print("Enter Book ID to search: ");
        int id = sc.nextInt();

        // Hash search
        if (bookMap.containsKey(id)) {
            System.out.println("Book Found: " + bookMap.get(id));
        } else {
            System.out.println("Book not found.");
        }
    }

    // SORT BOOKS
    static void sortBooks() {

        ArrayList<Book> list = new ArrayList<>(books);

        // Bubble Sort
        for (int i = 0; i < list.size() - 1; i++) {

            for (int j = 0; j < list.size() - i - 1; j++) {

                if (list.get(j).id > list.get(j + 1).id) {

                    Book temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }

        System.out.println("\nSorted Books:");
        for (Book b : list) {
            System.out.println(b);
        }
    }

    // ISSUE BOOK
    static void issueBook() {

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Book b = bookMap.get(id);

        if (b == null) {
            System.out.println("Book not found.");
            return;
        }

        if (!b.available) {
            System.out.println("Book already issued.");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        requestQueue.add(name);
        b.available = false;

        System.out.println("Book issued to " + name);
    }

    // RETURN BOOK
    static void returnBook() {

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Book b = bookMap.get(id);

        if (b == null) {
            System.out.println("Book not found.");
            return;
        }

        if (b.available) {
            System.out.println("Book already in library.");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        b.available = true;
        returnStack.push(name);

        System.out.println("Book returned successfully.");
    }

    // VIEW REQUEST QUEUE
    static void viewQueue() {

        if (requestQueue.isEmpty()) {
            System.out.println("No requests.");
            return;
        }

        System.out.println("Request Queue:");
        for (String s : requestQueue) {
            System.out.println(s);
        }
    }

    // CHECK AVAILABILITY
    static void checkAvailability() {

        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();

        Book b = bookMap.get(id);

        if (b == null) {
            System.out.println("Book not found.");
        } else {
            System.out.println(b);
        }
    }

    // PRIORITY RESERVATION
    static void priorityReservation() {

        System.out.print("Enter Priority Number: ");
        int p = sc.nextInt();

        reservationPQ.add(p);

        System.out.println("Reservation added.");

        System.out.println("Next Priority: " + reservationPQ.peek());
    }

    // MAIN METHOD
    public static void main(String[] args) {

        while (true) {

            System.out.println("\n----- Library Management System -----");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book");
            System.out.println("4. Sort Books");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. View Request Queue");
            System.out.println("8. Check Book Availability");
            System.out.println("9. Priority Reservation");
            System.out.println("10. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    displayBooks();
                    break;

                case 3:
                    searchBook();
                    break;

                case 4:
                    sortBooks();
                    break;

                case 5:
                    issueBook();
                    break;

                case 6:
                    returnBook();
                    break;

                case 7:
                    viewQueue();
                    break;

                case 8:
                    checkAvailability();
                    break;

                case 9:
                    priorityReservation();
                    break;

                case 10:
                    System.out.println("Exiting program...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}