package il.ac.hit.views;

import il.ac.hit.models.IModel;
import il.ac.hit.models.SimpleInMemoryToDoListModel;
import il.ac.hit.viewmodels.IViewModel;
import il.ac.hit.viewmodels.SimpleViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage implements IView {
    private GridBagConstraints gbc_data;
    private JFrame frame;
    private JLabel login_and_out;
    private JLabel firstName_text;
    private JLabel lastName_text;
    private JLabel password_text;
    private JLabel email_text;
    private JLabel budget_text;
    private JLabel gender_text;
    private JButton register;
    private JTextField firstNameD,lastNameD,passwordD,emailD,budgetD;
    private JRadioButton male,female;
    private IViewModel vm;
    private JPanel panelNorth, panelCenter,panelSouth;
    private ButtonGroup genderGroup;
//the order of the data is : ID,FirstName,LastName,Gender,Email,Budget,PassWord
    public RegisterPage() {}


    @Override
    public void setIViewModel(IViewModel vm) {
        this.vm = vm;
    }

    public void init() {
        frame = new JFrame();
        login_and_out = new JLabel("Hello, please fill your information to Register :)");
        firstName_text = new JLabel("First Name: ");
        lastName_text = new JLabel("First Name: ");
        password_text = new JLabel("Password: ");
        email_text = new JLabel("Email: ");
        budget_text = new JLabel("Budget: ");
        gender_text = new JLabel("Gender: ");

        firstNameD = new JTextField(10);
        lastNameD = new JTextField(10);
        passwordD = new JTextField(10);
        emailD = new JTextField(10);
        budgetD = new JTextField(10);
        male=new JRadioButton("Male");
        female=new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        register = new JButton("Register now");
        panelNorth = new JPanel();
        panelCenter = new JPanel();
        panelSouth= new JPanel();
        gbc_data= new GridBagConstraints();
    }
/*    c.gridx=0;c.gridy=0;
        panelCenter.add();
        */
    public void start() {
        panelNorth.setBackground(Color.GREEN);
        panelNorth.setLayout(new FlowLayout());
        panelNorth.add(login_and_out);
        panelCenter.setBackground(Color.YELLOW);
        panelCenter.setLayout(new GridBagLayout());

        gbc_data.gridx=0;gbc_data.gridy=0;
        panelCenter.add(firstName_text,gbc_data);
        gbc_data.gridx=1;gbc_data.gridy=0;
        panelCenter.add(firstNameD,gbc_data);

        gbc_data.gridx=0;gbc_data.gridy=1;
        panelCenter.add(lastName_text,gbc_data);
        gbc_data.gridx=1;gbc_data.gridy=1;
        panelCenter.add(lastNameD,gbc_data);

        gbc_data.gridx=0;gbc_data.gridy=2;
        panelCenter.add(password_text,gbc_data);
        gbc_data.gridx=1;gbc_data.gridy=2;
        panelCenter.add(passwordD,gbc_data);

        gbc_data.gridx=0;gbc_data.gridy=3;
        panelCenter.add(email_text,gbc_data);
        gbc_data.gridx=1;gbc_data.gridy=3;
        panelCenter.add(emailD,gbc_data);

        gbc_data.gridx=0;gbc_data.gridy=4;
        panelCenter.add(budget_text,gbc_data);
        gbc_data.gridx=1;gbc_data.gridy=4;
        panelCenter.add(budgetD,gbc_data);

        gbc_data.gridx=0;gbc_data.gridy=5;
        panelCenter.add(gender_text,gbc_data);
        gbc_data.gridx=0;gbc_data.gridy=6;
        panelCenter.add(male,gbc_data);
        gbc_data.gridx=1;gbc_data.gridy=6;
        panelCenter.add(female,gbc_data);

        panelSouth.setLayout(new FlowLayout());
        panelSouth.add(register);
        frame.setLayout(new BorderLayout());
        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.add(panelSouth,BorderLayout.SOUTH);
        frame.setSize(500, 400);
        frame.setVisible(true);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IView temp= new LoginPage();
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
