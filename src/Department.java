public class Department {
    private String name;
    private String reference;
    
    public Department(String name, String reference) {
        this.name = name;
        this.reference = reference;
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getReference() {
        return reference;
    }
    
    public void setReference(String reference) {
        this.reference = reference;
    }
    
    @Override
    public String toString() {
        return name + " (" + reference + ")";
    }
}