package il.ac.hit.viewmodels;

import il.ac.hit.models.IModel;
import il.ac.hit.views.IView;
import il.ac.hit.views.Item;

public interface IViewModel {
    public void setView(IView view);
    public void setModel(IModel model);
    public void addItem(Item item);
    public void getItems();
    //public void deleteAllItems();
    public void deleteItem(int id);
}