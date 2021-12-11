package il.ac.hit.views;

public class Item {
    public Item(int id,String Name,double price,int categoty) {
        this.Name = Name;
        this.Price=price;
        this.ID =id;
        this.Categoty=categoty;
    }
    private int ID;
    private String Name;
    private double Price;
    private int Categoty;
    public int getCategoty() {return Categoty;}
    public int getID() {
        return ID;
    }
    public String getName() {
        return Name;
    }
    public double getPrice() {
        return Price;
    }

}
