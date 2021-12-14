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
    private boolean isLogin= false;
    private Person userDtails;
    public class HomePage implements IView{
        private JFrame frame;
        private JTextField tfItem, tfMessage, deleteTa;
        private JButton button, deleteSpecificItem,backToHomePage;
        private JTextArea ta;
        private JPanel panelSouth, panelNorth;
        private IViewModel vm;
        public HomePage() {
        }
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
                    frame.setVisible(false);
                    changeView(new ClientUI());
                }
            });
            vm.getItems();
        }
        @Override
        public void showItems(Item[] items) {
            StringBuffer sb = new StringBuffer();
            for (int i=0;i< items.length;i++){
                sb.append((i + 1) + ". " + items[i].getName()+ " with price: "+items[i].getPrice()+" id ["+items[i].getId()+"]\n");
            }
            ta.setText(sb.toString());
        }

        @Override
        public void showPerson(Person person) {
            userDtails = person;
        }

        @Override
        public void getPerson(String name, String password) {

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

    }
    public class LoginPage implements IView{
        private JFrame frame;
        private JLabel login_and_out;
        private JLabel userName_text;
        private JLabel password_text;
        private JButton register, login;
        private JTextField userNameD,passwordD;
        private IViewModel vm;
        private JPanel panelNorth, panelCenter,panelSouth;

        public LoginPage() {}
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
                    vm.checkUserExistence(userNameD.getText(),passwordD.getText());
                    if(userDtails != null){
                        isLogin = true;
                        frame.setVisible(false);
                        changeView(new ClientUI());
                    }
                }
            });
            register.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    changeView(new ClientUI.RegisterPage());
                }
            });
        }
        @Override
        public void showItems(Item[] items) {

        }

        @Override
        public void showPerson(Person person) {
            userDtails = person;
        }

        @Override
        public void getPerson(String name, String password) {

        }

        @Override
        public void showMessage(Message message) {

        }
    }
    public class RegisterPage implements IView{
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
                    frame.setVisible(false);
                    String firstName = firstNameD.getText();
                    String lastName = lastNameD.getText();
                    String password = passwordD.getText();
                    int gender=(male.isSelected())?1:0;
                    double budget = Double.parseDouble(budgetD.getText());
                    String email = emailD.getText();
                    userDtails= new Person(firstName,lastName,password,gender,budget,email);
                    vm.addPerson(userDtails);
                    changeView(new ClientUI.LoginPage());
                }
            });
        }

        @Override
        public void showItems(Item[] items) {

        }

        @Override
        public void showPerson(Person person) {
            userDtails = person;
        }

        @Override
        public void getPerson(String name, String password) {

        }

        @Override
        public void showMessage(Message message) {

        }

    }
    private JFrame frame;
    private JLabel login_and_out;
    private JButton show_expenses, show_category_list, show_costs, show_detail_reports;
    private IViewModel vm;
    private JPanel panelNorth, panelCenter;
    public ClientUI() {}

    @Override
    public void setIViewModel(IViewModel vm) {
        this.vm = vm;
    }
    @Override
    public void showItems(Item[] items) {
    }

    @Override
    public void showPerson(Person person) {
        userDtails = person;
    }

    @Override
    public void getPerson(String name, String password) {

    }

    @Override
    public void showMessage(Message message) {

    }


    public void init() {
        frame = new JFrame();
        if(isLogin){
            login_and_out = new JLabel("Hello "+userDtails.getFullName());
        }else {
            login_and_out = new JLabel("Hello stranger, please login :)");
        }
        show_expenses = new JButton("Show Expences");
        show_category_list = new JButton("Show Category List");
        show_costs = new JButton("Show Costs");
        show_detail_reports = new JButton("Show Detail Reports");
        panelNorth = new JPanel();
        panelCenter = new JPanel();
    }

    public void start() {
        panelNorth.setBackground(Color.GREEN);
        panelNorth.setLayout(new FlowLayout());
        panelNorth.add(login_and_out);
        panelCenter.setBackground(Color.YELLOW);
        panelCenter.setLayout(new FlowLayout());
        panelCenter.add(show_expenses);
        panelCenter.add(show_category_list);
        panelCenter.add(show_costs);
        panelCenter.add(show_detail_reports);
        frame.setLayout(new BorderLayout());
        frame.add(panelNorth, BorderLayout.NORTH);
        frame.add(panelCenter, BorderLayout.CENTER);
        frame.setSize(500, 400);
        frame.setVisible(true);

        show_costs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                if(!isLogin){
                    changeView(new ClientUI.LoginPage());
                }else{
                    changeView(new ClientUI.LoginPage());
                }
            }
        });
        show_expenses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                if(!isLogin){
                    changeView(new ClientUI.LoginPage());
                }else{
                    changeView(new ClientUI.HomePage());
                }
            }
        });
        show_category_list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                if(!isLogin){
                    changeView(new ClientUI.LoginPage());
                }else{
                    changeView(new ClientUI.HomePage());
                }

            }
        });
        show_detail_reports.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                if(!isLogin){
                    changeView(new ClientUI.LoginPage());
                }else{
                    changeView(new ClientUI.HomePage());
                }
            }
        });

    }

    public void changeView(IView theNewView){
        IView newView = theNewView;
        newView.setIViewModel(vm);
        newView.init();
        newView.start();
    }
}
