import java.util.ArrayList;
import java.util.List;

public class ActivityStack {
    private List<String> stack;  // Notre pile
    private int maxSize = 10;    // Taille maximum
    
    public ActivityStack() {
        stack = new ArrayList<>();
    }
    
    // Ajouter une activité
    public void push(String activity) {
        stack.add(activity);
        
        // Garder seulement les 10 dernières activités
        if (stack.size() > maxSize) {
            stack.remove(0);
        }
    }
    
    // Retirer la dernière activité
    public String pop() {
        if (!stack.isEmpty()) {
            return stack.remove(stack.size() - 1);
        }
        return null;
    }
    
    // Voir la dernière activité sans la retirer
    public String peek() {
        if (!stack.isEmpty()) {
            return stack.get(stack.size() - 1);
        }
        return null;
    }
    
    // Afficher toutes les activités
    public void displayActivities() {
        if (stack.isEmpty()) {
            System.out.println("\nAucune activité récente.");
            return;
        }
        
        System.out.println("\n=== 10 dernières activités ===");
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.println((stack.size() - i) + ". " + stack.get(i));
        }
    }
    
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    
    public int size() {
        return stack.size();
    }
}