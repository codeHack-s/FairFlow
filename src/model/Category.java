package model;


public class Category {
    private String name;
    private String id;
    private String description;

    public Category(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void editCategory(String name, String description) {
        // implementation details
    }

    public void deleteCategory() {
        // implementation details
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}