package il.ac.hit.views;
import il.ac.hit.models.IModel;
import il.ac.hit.models.SimpleInMemoryToDoListModel;
import il.ac.hit.viewmodels.IViewModel;
import il.ac.hit.viewmodels.SimpleViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

public class ClientUI implements IView
{

    private JFrame frame;
    private JTextField tfItem, tfMessage, deleteTa;
    private JButton button, deleteSpecificItem,backToHomePage;
    private JTextArea ta;
    private JPanel panelSouth, panelNorth;
    private IViewModel vm;


    public ClientUI() {}

    public void init() {
        frame = new JFrame();
        tfItem = new JTextField(10);
        tfMessage = new JTextField(20);
        button = new JButton("add item");
        deleteSpecificItem=new JButton("Delete Item");
        backToHomePage=new JButton("Home Page");
        deleteTa = new JTextField(10);
        ta = new JTextArea();
        panelSouth = new JPanel();
        panelNorth = new JPanel();
    }

    public void start() {
        panelNorth.setBackground(Color.GREEN);
        panelNorth.setLayout(new FlowLayout());
        panelNorth.add(tfMessage);
        panelSouth.setBackground(Color.YELLOW);
        panelSouth.setLayout(new FlowLayout());
        panelSouth.add(tfItem);
        panelSouth.add(button);
        panelSouth.add(backToHomePage);
        panelNorth.add(deleteSpecificItem);
        panelNorth.add(deleteTa);
        frame.setLayout(new BorderLayout());
        frame.add(panelSouth,BorderLayout.SOUTH);
        frame.add(ta,BorderLayout.CENTER);
        frame.add(panelNorth,BorderLayout.NORTH);
        frame.setSize(500,400);
        frame.setVisible(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item = new Item(1,tfItem.getText(),2.2,6);
                vm.addItem(item);
            }
        });
        deleteSpecificItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vm.deleteItem(Integer.parseInt(deleteTa.getText().toString()));
            }
        });
        backToHomePage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IView temp= new HomePage();
                creatingNewFrame(temp);
            }
        });
        vm.getItems();
    }

    @Override
    public void showItems(Item[] items) {
        StringBuffer sb = new StringBuffer();
        for (int i=0;i< items.length;i++){
            sb.append((i + 1) + ". " + items[i].getName()+ " with price: "+items[i].getPrice()+" id ["+items[i].getID()+"]\n");
        }
        ta.setText(sb.toString());
    }

    @Override
    public void showMessage(Message message) {
        tfMessage.setText(message.getText());
        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        tfMessage.setText("");
                    }
                });
            }
        },5000);
    }

    @Override
    public void setIViewModel(IViewModel vm) {
        this.vm = vm;
    }
    public void creatingNewFrame(IView the_view){
        frame.dispose();
        IModel model = new SimpleInMemoryToDoListModel();
        IViewModel vm = new SimpleViewModel();
        IView view = the_view;
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
