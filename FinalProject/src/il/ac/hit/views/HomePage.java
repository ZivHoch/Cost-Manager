package il.ac.hit.views;

import il.ac.hit.models.IModel;
import il.ac.hit.models.SimpleInMemoryToDoListModel;
import il.ac.hit.viewmodels.IViewModel;
import il.ac.hit.viewmodels.SimpleViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage implements IView {

    private JFrame frame;
    private JLabel login_and_out;
    private JButton show_expenses, show_category_list, show_costs, show_detail_reports;
    private IViewModel vm;
    private JPanel panelNorth, panelCenter;

    public HomePage() {
    }


    @Override
    public void setIViewModel(IViewModel vm) {
        this.vm = vm;
    }

    public void init() {
        frame = new JFrame();
        login_and_out = new JLabel("Hello stranger, please login :)");
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
                IView temp= new LoginPage();
                creatingNewFrame(temp);
            }
        });
        show_expenses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IView temp= new ClientUI();
                creatingNewFrame(temp);
            }
        });
        show_category_list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IView temp= new LoginPage();
                creatingNewFrame(temp);
            }
        });
        show_detail_reports.addActionListener(new ActionListener() {
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
