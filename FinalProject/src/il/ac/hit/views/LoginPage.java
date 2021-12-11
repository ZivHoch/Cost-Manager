package il.ac.hit.views;

import il.ac.hit.models.IModel;
import il.ac.hit.models.SimpleInMemoryToDoListModel;
import il.ac.hit.viewmodels.IViewModel;
import il.ac.hit.viewmodels.SimpleViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage implements IView {
    private JFrame frame;
    private JLabel login_and_out;
    private JLabel userName_text;
    private JLabel password_text;
    private JButton register, login;
    private JTextField userNameD,passwordD;
    private IViewModel vm;
    private JPanel panelNorth, panelCenter,panelSouth;

    public LoginPage() {}
    //deleteTa = new JTextField(10);

    @Override
    public void setIViewModel(IViewModel vm) {
        this.vm = vm;
    }

    public void init() {
        frame = new JFrame();

        login_and_out = new JLabel("Hello, please enter your userName and password to login :)");
        userName_text = new JLabel("UserName: ");
        password_text = new JLabel("Password: ");
        userNameD = new JTextField(10);
        passwordD = new JTextField(10);
        login = new JButton("Login");
        register = new JButton("not a user? Register here");
        panelNorth = new JPanel();
        panelCenter = new JPanel();
        panelSouth= new JPanel();
    }

    public void start() {
        panelNorth.setBackground(Color.GREEN);
        panelNorth.setLayout(new FlowLayout());
        panelNorth.add(login_and_out);
        panelCenter.setBackground(Color.YELLOW);
        panelCenter.setLayout(new FlowLayout());
        panelCenter.add(userName_text);
        panelCenter.add(userNameD);
        panelCenter.add(password_text);
        panelCenter.add(passwordD);
        panelSouth.setLayout(new FlowLayout());
        panelSouth.add(login);
        panelSouth.add(register);
        frame.setLayout(new BorderLayout());
        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.add(panelSouth,BorderLayout.SOUTH);
        frame.setSize(500, 400);
        frame.setVisible(true);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IView temp= new HomePage();
                creatingNewFrame(temp);
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IView temp= new RegisterPage();
                creatingNewFrame(temp);
            }
        });
    }

    @Override
    public void showItems(Item[] items) {

    }

    @Override
    public void showMessage(Message message) {

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
