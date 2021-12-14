package il.ac.hit.models;


import il.ac.hit.views.Item;
import il.ac.hit.views.Person;

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
                    "" + item.getCategory() + " " +
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
            } catch (SQLException | ClassNotFoundException e) {
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
        list.clear();
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

        }catch (SQLException | ClassNotFoundException e){
            System.out.println("Unable to connect to the server");
        }
        return list.toArray(new Item[]{});
    }

    @Override
    public void addPerson(Person person) throws ToDoListException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/costmanager", "Anzor", "0506534697");
            Statement statement = connection.createStatement();
            statement.execute("insert into userdetails (FirstName ,LastName, Gender,Email,Budget,PassWord)" +
                    "values (" +
                    "'" + person.getFirstName() + "'," +
                    "'" + person.getLastName() + "'," +
                      person.getGender()+
                    "'" + person.getEmail() + "'," +
                    person.getBudget() +
                    "'" + person.getPassword() + "'," +
                    ")");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            throw new ToDoListException("DB access failed", e);
        }
    }

    @Override
    public Person getPerson(String firstName, String password) throws ToDoListException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/costmanager", "Anzor", "0506534697");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("Select * FROM userdetails WHERE FirstName = " + firstName +"and PassWord="+password+";");
            while(rs.next()){
                String lastName= rs.getString(2);
                int gender= rs.getInt(3);
                String email= rs.getString(4);
                double budget = rs.getDouble(5);
                return new Person(firstName,lastName,password,gender,budget,email);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
            throw new ToDoListException("DB access failed", e);
        }
        return null;
    }


}
