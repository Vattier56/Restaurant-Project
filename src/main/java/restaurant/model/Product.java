package restaurant.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private long price;
    @ElementCollection
    private List<Ingredient> ingredientList;

    public Product() {}

    public Product(String name, long price, List<Ingredient> ingredientList) {
        this.name = name;
        this.price = price;
        this.ingredientList = ingredientList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setListaSkladnikow(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public long getPrice() {
        return price;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }
}