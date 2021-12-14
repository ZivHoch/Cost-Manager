package il.ac.hit.models;

import il.ac.hit.views.Item;
import il.ac.hit.views.Person;

import java.util.ArrayList;


public interface IModel
{
    public void addItem(Item item) throws ToDoListException;
    public void deleteItem(int id) throws ToDoListException;
    public Item[] getItemsArrayListFromDB()throws  ToDoListException;
    public void addPerson(Person person) throws  ToDoListException;
    public Person getPerson(String firstName, String password) throws ToDoListException;
    //public List<String> getCategories()throws ToDoListException;
    //public void addCategory(String categoryName) throws ToDoListException;
    //public ArrayList<Item> getReport(java.util.Date date1, java.util.Date date2)throws ToDoListException;


}
