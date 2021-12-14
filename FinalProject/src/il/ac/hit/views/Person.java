package il.ac.hit.views;

public class Person {
    private String firstName;
    private String lastName;
    private String password;
    private int gender;
    private double budget;
    private String email;

    public Person(String firstName, String lastName, String password, int gender, double budget, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
        setGender(gender);
        setBudget(budget);
        setEmail(email);
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getGender() {
        return gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public String getFullName(){
        return getFirstName() +" "+ getLastName();
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
