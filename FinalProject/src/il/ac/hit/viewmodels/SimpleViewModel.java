package il.ac.hit.viewmodels;


import il.ac.hit.models.ToDoListException;
import il.ac.hit.models.IModel;
import il.ac.hit.views.IView;
import il.ac.hit.views.Item;
import il.ac.hit.views.Message;
import il.ac.hit.views.Person;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleViewModel implements IViewModel {

    private IModel model;
    private IView view;
    private ExecutorService service;

    public SimpleViewModel() {
        this.service = Executors.newFixedThreadPool(3);
    }

    @Override
    public void setView(IView view) {
        this.view = view;
    }

    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    @Override
    public void addItem(Item item) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addItem(item);
                    Item []items = model.getItemsArrayListFromDB();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showMessage(new Message("item was added"));
                            view.showItems(items);
                        }
                    });
                } catch(ToDoListException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void getItems() {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Item[] items = model.getItemsArrayListFromDB();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showItems(items);
                        }
                    });
                } catch(ToDoListException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showMessage(new Message("problem with fetching the items from the model"));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void deleteItem(int id) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.deleteItem(id);
                    Item []items = model.getItemsArrayListFromDB();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showMessage(new Message("the item was deleted"));
                            view.showItems(items);
                        }
                    });
                } catch(ToDoListException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showMessage(new Message("problem with deleting the item"));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void addPerson(Person person) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addPerson(person);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showMessage(new Message("you register successfully! please login to enjoy our features"));
                        }
                    });
                } catch(ToDoListException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showMessage(new Message(e.getMessage()));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void checkUserExistence(String firstName, String password) {
        service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Person person = model.getPerson(firstName,password);
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showPerson(person);
                        }
                    });
                } catch(ToDoListException e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            view.showMessage(new Message("there is something wrong with the name or the password!\n please try again"));
                        }
                    });
                }
            }
        });
    }
}
