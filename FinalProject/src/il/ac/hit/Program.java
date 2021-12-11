package il.ac.hit;

import il.ac.hit.models.IModel;
import il.ac.hit.models.SimpleInMemoryToDoListModel;
import il.ac.hit.viewmodels.IViewModel;
import il.ac.hit.viewmodels.SimpleViewModel;
import il.ac.hit.views.HomePage;
import il.ac.hit.views.IView;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Program
{
    public static void main(String[] args) {

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/costmanager","Anzor","0506534697");
            Statement  statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from categorieslist");
            while(rs.next()){
                System.out.println("ID:" + rs.getInt(1) + "Category Name: " + rs.getString(2));
            }

        }catch (Exception e){
            System.out.println("Unable to connect to the server");
        }

        IModel model = new SimpleInMemoryToDoListModel();
        IViewModel vm = new SimpleViewModel();
        IView view = new HomePage();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.init();
                view.start();
            }
        });
        vm.setModel(model);
        vm.setView(view);
        view.setIViewModel(vm);

    }
}
