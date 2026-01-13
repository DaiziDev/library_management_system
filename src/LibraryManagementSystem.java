import java.util.Scanner;

public class LibraryManagementSystem {
    private Book[] books;           // Tableau de livres
    private int bookCount;          // Nombre actuel de livres
    private ActivityStack activities; // Pile d'activités
    private Scanner scanner;        // Pour lire l'entrée utilisateur
    
    public LibraryManagementSystem(int capacity) {
        books = new Book[capacity];
        bookCount = 0;
        activities = new ActivityStack();
        scanner = new Scanner(System.in);
    }
    
    // 1. GESTION DES LIVRES
    
    // Ajouter un livre
    public void addBook() {
        if (bookCount >= books.length) {
            System.out.println("La bibliothèque est pleine!");
            return;
        }
        
        System.out.println("\n=== Ajouter un nouveau livre ===");
        
        scanner.nextLine(); // Nettoyer le buffer
        
        System.out.print("Titre: ");
        String title = scanner.nextLine();
        
        System.out.print("Auteur: ");
        String author = scanner.nextLine();
        
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        
        System.out.print("Année de publication: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Nettoyer le buffer
        
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        
        // Créer le livre et l'ajouter au tableau
        Book newBook = new Book(title, author, isbn, year, genre);
        books[bookCount] = newBook;
        bookCount++;
        
        // Ajouter à l'historique des activités
        activities.push("Ajout du livre: " + title);
        
        System.out.println("Livre ajouté avec succès!");
    }
    
    // Supprimer un livre par ISBN
    public void removeBook() {
        System.out.print("\nEntrez l'ISBN du livre à supprimer: ");
        String isbn = scanner.next();
        
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                activities.push("Suppression du livre: " + books[i].getTitle());
                
                // Décaler les livres pour remplir le trou
                for (int j = i; j < bookCount - 1; j++) {
                    books[j] = books[j + 1];
                }
                bookCount--;
                
                System.out.println("Livre supprimé avec succès!");
                return;
            }
        }
        System.out.println("Livre non trouvé!");
    }
    
    // Mettre à jour un livre
    public void updateBook() {
        System.out.print("\nEntrez l'ISBN du livre à modifier: ");
        String isbn = scanner.next();
        
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                System.out.println("\nLivre trouvé:");
                System.out.println(books[i]);
                
                scanner.nextLine(); // Nettoyer le buffer
                
                System.out.println("\n=== Nouveaux détails ===");
                System.out.print("Nouveau titre (laisser vide pour ne pas changer): ");
                String title = scanner.nextLine();
                if (!title.isEmpty()) books[i].setTitle(title);
                
                System.out.print("Nouvel auteur: ");
                String author = scanner.nextLine();
                if (!author.isEmpty()) books[i].setAuthor(author);
                
                System.out.print("Nouvelle année: ");
                String yearStr = scanner.nextLine();
                if (!yearStr.isEmpty()) books[i].setPublicationYear(Integer.parseInt(yearStr));
                
                System.out.print("Nouveau genre: ");
                String genre = scanner.nextLine();
                if (!genre.isEmpty()) books[i].setGenre(genre);
                
                activities.push("Mise à jour du livre: " + books[i].getTitle());
                System.out.println("Livre mis à jour avec succès!");
                return;
            }
        }
        System.out.println("Livre non trouvé!");
    }
    
    // Afficher tous les livres
    public void displayAllBooks() {
        if (bookCount == 0) {
            System.out.println("\nAucun livre dans la bibliothèque.");
            return;
        }
        
        System.out.println("\n=== Tous les livres (" + bookCount + ") ===");
        for (int i = 0; i < bookCount; i++) {
            System.out.println("\nLivre #" + (i + 1));
            System.out.println(books[i]);
            System.out.println("-------------------");
        }
    }
    
    // 2. RECHERCHE
    
    // Recherche linéaire par titre
    public void linearSearchByTitle() {
        System.out.print("\nEntrez le titre à rechercher: ");
        scanner.nextLine(); // Nettoyer le buffer
        String title = scanner.nextLine();
        
        System.out.println("\n=== Résultats de recherche (Recherche linéaire) ===");
        boolean found = false;
        
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                System.out.println(books[i]);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("Aucun livre trouvé avec ce titre.");
        }
        
        activities.push("Recherche linéaire par titre: " + title);
    }
    
    // Recherche binaire par titre (nécessite un tableau trié)
    public void binarySearchByTitle() {
        // Trier d'abord par titre
        bubbleSortByTitle();
        
        System.out.print("\nEntrez le titre à rechercher: ");
        scanner.nextLine(); // Nettoyer le buffer
        String title = scanner.nextLine();
        
        int left = 0;
        int right = bookCount - 1;
        boolean found = false;
        
        System.out.println("\n=== Résultats de recherche (Recherche binaire) ===");
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = books[mid].getTitle().compareToIgnoreCase(title);
            
            if (comparison == 0) {
                System.out.println(books[mid]);
                found = true;
                break;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        if (!found) {
            System.out.println("Aucun livre trouvé avec ce titre.");
        }
        
        activities.push("Recherche binaire par titre: " + title);
    }
    
    // 3. TRI
    
    // Tri à bulles par titre
    public void bubbleSortByTitle() {
        for (int i = 0; i < bookCount - 1; i++) {
            for (int j = 0; j < bookCount - i - 1; j++) {
                if (books[j].getTitle().compareToIgnoreCase(books[j + 1].getTitle()) > 0) {
                    // Échanger les livres
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                }
            }
        }
        activities.push("Tri à bulles par titre");
        System.out.println("Livres triés par titre (tri à bulles)!");
    }
    
    // Tri par sélection par auteur
    public void selectionSortByAuthor() {
        for (int i = 0; i < bookCount - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < bookCount; j++) {
                if (books[j].getAuthor().compareToIgnoreCase(books[minIndex].getAuthor()) < 0) {
                    minIndex = j;
                }
            }
            // Échanger les livres
            Book temp = books[minIndex];
            books[minIndex] = books[i];
            books[i] = temp;
        }
        activities.push("Tri par sélection par auteur");
        System.out.println("Livres triés par auteur (tri par sélection)!");
    }
    
    // Tri rapide (quicksort) par année
    public void quickSortByYear() {
        if (bookCount > 0) {
            quickSort(0, bookCount - 1);
            activities.push("Tri rapide par année");
            System.out.println("Livres triés par année (tri rapide)!");
        }
    }
    
    private void quickSort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            quickSort(low, pi - 1);
            quickSort(pi + 1, high);
        }
    }
    
    private int partition(int low, int high) {
        int pivot = books[high].getPublicationYear();
        int i = (low - 1);
        
        for (int j = low; j < high; j++) {
            if (books[j].getPublicationYear() < pivot) {
                i++;
                // Échanger les livres
                Book temp = books[i];
                books[i] = books[j];
                books[j] = temp;
            }
        }
        
        // Échanger le pivot
        Book temp = books[i + 1];
        books[i + 1] = books[high];
        books[high] = temp;
        
        return i + 1;
    }
    
    // 4. FONCTIONNALITÉS SUPPLÉMENTAIRES
    
    // Gérer l'historique d'emprunt pour un livre
    public void manageBorrowingHistory() {
        System.out.print("\nEntrez l'ISBN du livre: ");
        String isbn = scanner.next();
        
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                System.out.println("\nLivre: " + books[i].getTitle());
                
                BorrowingHistory history = new BorrowingHistory();
                
                System.out.println("\n=== Gestion des emprunts ===");
                System.out.println("1. Ajouter un emprunteur");
                System.out.println("2. Voir l'historique");
                System.out.print("Choix: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Nettoyer le buffer
                
                if (choice == 1) {
                    System.out.print("Nom de l'emprunteur: ");
                    String borrower = scanner.nextLine();
                    history.addBorrower(borrower);
                    activities.push("Ajout d'emprunteur pour: " + books[i].getTitle());
                    System.out.println("Emprunteur ajouté!");
                } else if (choice == 2) {
                    history.displayHistory();
                }
                return;
            }
        }
        System.out.println("Livre non trouvé!");
    }
    
    // Afficher le menu principal
    public void displayMenu() {
        System.out.println("\n=== SYSTÈME DE GESTION DE BIBLIOTHÈQUE ===");
        System.out.println("1. Ajouter un livre");
        System.out.println("2. Supprimer un livre");
        System.out.println("3. Modifier un livre");
        System.out.println("4. Afficher tous les livres");
        System.out.println("\n=== RECHERCHE ===");
        System.out.println("5. Recherche linéaire (titre)");
        System.out.println("6. Recherche binaire (titre - nécessite tri)");
        System.out.println("\n=== TRI ===");
        System.out.println("7. Tri à bulles (titre)");
        System.out.println("8. Tri par sélection (auteur)");
        System.out.println("9. Tri rapide (année)");
        System.out.println("\n=== AUTRES FONCTIONS ===");
        System.out.println("10. Gérer les emprunts");
        System.out.println("11. Voir les activités récentes");
        System.out.println("0. Quitter");
        System.out.print("\nVotre choix: ");
    }
    
    // Exécuter le système
    public void run() {
        int choice;
        
        do {
            displayMenu();
            choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    displayAllBooks();
                    break;
                case 5:
                    linearSearchByTitle();
                    break;
                case 6:
                    binarySearchByTitle();
                    break;
                case 7:
                    bubbleSortByTitle();
                    break;
                case 8:
                    selectionSortByAuthor();
                    break;
                case 9:
                    quickSortByYear();
                    break;
                case 10:
                    manageBorrowingHistory();
                    break;
                case 11:
                    activities.displayActivities();
                    break;
                case 0:
                    System.out.println("Merci d'avoir utilisé le système!");
                    break;
                default:
                    System.out.println("Choix invalide!");
            }
            
        } while (choice != 0);
        
        scanner.close();
    }
}