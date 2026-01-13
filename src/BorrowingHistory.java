import java.util.ArrayList;
import java.util.List;

public class BorrowingHistory {
    // Classe interne pour un nœud de la liste chaînée
    private class Node {
        String borrower;  // Nom de la personne qui emprunte
        Node next;        // Lien vers le prochain nœud
        
        Node(String borrower) {
            this.borrower = borrower;
            this.next = null;
        }
    }
    
    private Node head;  // Premier élément de la liste
    
    // Constructeur
    public BorrowingHistory() {
        head = null;
    }
    
    // Ajouter un emprunteur
    public void addBorrower(String borrower) {
        Node newNode = new Node(borrower);
        
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }
    
    // Obtenir tous les emprunteurs
    public List<String> getBorrowers() {
        List<String> borrowers = new ArrayList<>();
        Node current = head;
        
        while (current != null) {
            borrowers.add(current.borrower);
            current = current.next;
        }
        
        return borrowers;
    }
    
    // Afficher l'historique
    public void displayHistory() {
        if (head == null) {
            System.out.println("Aucun emprunt enregistré.");
            return;
        }
        
        Node current = head;
        int count = 1;
        System.out.println("\n=== Historique des emprunts ===");
        while (current != null) {
            System.out.println(count + ". " + current.borrower);
            current = current.next;
            count++;
        }
    }
}