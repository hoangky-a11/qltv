public class Book {
    private String id;      // Unique identifier for each book
    private String title;
    private String author;
    private int year;
    public Book(String id, String title, String author, int year) {
        this.id = id;      // Assign id to the book
        this.title = title;
        this.author = author;
        this.year = year;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    @Override
    public String toString() {
        return "ID: " + id + ", Title: " + title + ", Author: " + author + ", Year: " + year;
    }
}
