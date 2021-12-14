package il.ac.hit.views;

public class Item {
    private int Id;
    private String name;
    private double price;
    private int category;

    public Item(int Id, String name, double price, int category) {
        setId(Id);
        setName(name);
        setPrice(price);
        setCategory(category);
    }
    public int getCategory(){ return category;}
    public int getId() { return Id; }
    public String getName() {
        return this.name;
    }
    public double getPrice() {
        return this.price;
    }
    public void setId(int Id) {
        this.Id = Id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setCategory(int category) {
        this.category = category;
    }

   
    

}
