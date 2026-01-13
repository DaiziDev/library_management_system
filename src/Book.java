public class Book {
    // Attributs (variables qui décrivent le livre)
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private String genre;
    
    // Constructeur - pour créer un nouveau livre
    public Book(String title, String author, String isbn, int publicationYear, String genre) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.genre = genre;
    }
    
    // Getters (méthodes pour obtenir les valeurs)
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getPublicationYear() { return publicationYear; }
    public String getGenre() { return genre; }
    
    // Setters (méthodes pour modifier les valeurs)
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setPublicationYear(int year) { this.publicationYear = year; }
    public void setGenre(String genre) { this.genre = genre; }
    
    // Méthode pour afficher les détails du livre
    @Override
    public String toString() {
        return "Titre: " + title + 
               "\nAuteur: " + author + 
               "\nISBN: " + isbn + 
               "\nAnnée: " + publicationYear + 
               "\nGenre: " + genre;
    }
}