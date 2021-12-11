package il.ac.hit.models;


import il.ac.hit.views.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SimpleInMemoryToDoListModel implements IModel {
    //static public ArrayList<category> categoryArrayList = new ArrayList<>();
    static public List<String> categoryNames = new ArrayList<>();
    static public ArrayList<Item> ItemArrayList;
    private List<Item> list = new LinkedList<>();

//    @Override
//    public void addItem(Item item) throws ToDoListException {
//        list.add(item);
//    }
    @Override
    public void addItem(Item item) throws ToDoListException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/costmanager", "Anzor", "0506534697");
            Statement statement = connection.createStatement();
            statement.execute("insert into costlist (CostName ,Price, CategoryID)" +
                    "values (" +
                    "'" + item.getName() + "'," +
                    item.getPrice() + "," +
                    "" + item.getCategoty() + " " +
                    ")");
        } catch (Exception e) {
            System.out.println(e);
            throw new ToDoListException("DB access failed", e);
        }
    }


    public void deleteItem(int itemId) throws ToDoListException {
//if the user enter an id to delete
        if (itemId !=-1) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/costmanager", "Anzor", "0506534697");
                Statement statement = connection.createStatement();
                statement.execute("delete from costlist where CostID=" + itemId + " \n");

            } catch (Exception e) {
                System.out.println(e);
                throw new ToDoListException("DB access failed", e);
            }
        }
    }

//    @Override
//    public void deleteItem(int id) throws ToDoListException {
//        boolean flag = true;
//        for(Item item:getItems()){
//            if (item.getID()==id){
//                list.remove(item);
//                flag = false;
//            }
//        }
//        if(flag){
//            throw new ToDoListException("");
//        }
//
//    }
//
//    @Override
//    public Item[] getItems() throws ToDoListException {
//        return list.toArray(new Item[]{});
//    }

    @Override
    public Item[] getItemsArrayListFromDB() throws ToDoListException {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/costmanager","Anzor","0506534697");
            Statement  statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from costlist");
            while(rs.next()){
                int id = rs.getInt(1);
                String Name= rs.getString(2);
                double price = rs.getDouble(3);
                int categeryID = rs.getInt(4);
                list.add(new Item(id,Name,price,categeryID));
            }

        }catch (Exception e){
            System.out.println("Unable to connect to the server");
        }
        return list.toArray(new Item[]{});
    }


}
