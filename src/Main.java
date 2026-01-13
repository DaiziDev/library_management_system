public class Main {
    public static void main(String[] args) {
        System.out.println("=== Démarrage du Système de Gestion de Bibliothèque ===\n");
        
        // Créer le système avec une capacité de 100 livres
        LibraryManagementSystem library = new LibraryManagementSystem(100);
        
        // Ajouter quelques livres de démonstration
        addSampleBooks(library);
        
        // Démarrer le système
        library.run();
    }
    
    // Méthode pour ajouter des livres de test
    private static void addSampleBooks(LibraryManagementSystem library) {
        // Cette méthode est simplifiée pour la démonstration
        // En réalité, tu devrais créer une méthode dans LibraryManagementSystem
        System.out.println("Ajout de livres de démonstration...");
        
        // Note: Dans la version finale, tu peux supprimer cette méthode
        // et ajouter des livres via le menu
    }
}