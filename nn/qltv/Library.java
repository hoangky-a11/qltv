import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Library {
    private String id;
    private String name;
    private ArrayList<Book> books;

    public Library(String id, String name) {
        this.id = id;
        this.name = name;
        this.books = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBookById(String id) {
        books.removeIf(book -> book.getId().equals(id));
    }

    public ArrayList<Book> searchBooks(String keyword) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    public void sortBooksByYear() {
        books.sort(Comparator.comparingInt(Book::getYear));
    }

    public void sortBooksByTitle() {
        books.sort(Comparator.comparing(Book::getTitle));
    }

    public String toString() {
        return "Library ID: " + id + ", Name: " + name + ", Total Books: " + books.size();
    }
}
