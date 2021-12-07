package lab8;

public class User {
    
    // static variables
    private static int count;
    private static final int INVALID_AGE = -2;
    private static final int ILLEGAL_AGE = -1;

    // instance variables
    private int ID;
    private int age;
    private int numberOfOrders;
    private String name;
    private String orders;// stores the ID of orders, seperated by "-"
    private String eatenFoods;// stores the names of unique foods the user has tried, seperated by "-"

    // constructors
    public User (String name, int age) {
        setName(name);
        setAge(age);
        setID(count);
        setNumberOfOrders(0);
        setOrders("");
        setEatenFoods("");
        addUser();
    }

    // accessors
    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getOrders() {
        return orders;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public String getEatenFoods() {
        return eatenFoods;
    }

    public static int getCount() {
        return count;
    }
    
    // mutators
    public void setID(int iD) {
        ID = iD;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if (age < 0 || age > 200) {
            this.age = INVALID_AGE;
            System.out.println("Age input cannot be negative!");
        }
        else if (age < 18) {
            this.age = ILLEGAL_AGE;
            System.out.println("You are too young to use this service. Please grow up immediately.");
        }
        else {
            this.age = age;
        }
    }

    public void setOrders(String orders) {
        if (checkAge()) {
            this.orders = orders;
        }
    }

    public void setNumberOfOrders(int numberOfOrders) {
        if (checkAge()) {
            this.numberOfOrders = numberOfOrders;
        }
    }

    public void setEatenFoods(String eatenFoods) {
        if (checkAge()) {
            this.eatenFoods = eatenFoods;
        }
    }

    // static methods


    // instance methods
    public boolean didEat (Food food) {
        return getEatenFoods().contains(food.getName());
    }

    public String pickOrderbyIndex (int index) {
        String[] splittedOrders = getOrders().split("-");
        if (index > 1 && index < splittedOrders.length + 1) {
            return splittedOrders[index - 1];
        }
        return null;
    }

    public void addNewOrder (Order order) {
        System.out.println("Adding new order of " + order.getOrderedFood().getName() + " to " + getName() +"...");
        order.setUser(this);
        if (getOrders().length() == 0) {
            setOrders("" + order.getID());
        }
        else {
            setOrders(getOrders() + "-" + order.getID());
        }
        setNumberOfOrders(getNumberOfOrders() + 1);
        if (!didEat(order.getOrderedFood())) {
            addEatenFood(order.getOrderedFood());
        }
    }

    /**
     * Compares this user to the object that is passed as an argument.
     * equals(User user) would not be a proper overriding of the {@code Object}
     * equals method since the parameter must be an instance of {@code Object}.
     * This algorithm is heavily inspired by the equals method of the {@code String}.
     * 
     * @param obj the {@code Object} instance to be compared to the current {@code User} instance
     * @return whether this instance is considered the same as the {@code Object} passed as an argument
     */
    @Override
    public boolean equals (Object obj) {
        if (obj instanceof User) {
            User user = (User) obj;
            return ( getID() == user.getID() && getName().equals(user.getName()) && getAge() == user.getAge() );
        }
        return false;
    }

    @Override
    public String toString () {
        return "User ID: " + getID() + ", User Name: " + getName() + ", Age: " + getAge();
    }

    // private methods
    private void addUser() {
        count++;
    }

    private void addEatenFood (Food food) {
        if (getEatenFoods().length() == 0) {
            setEatenFoods(food.getName());
        }
        else {
            setEatenFoods(getEatenFoods() + "-" + food.getName());
        }
    }

    /**
     * Checks whether {@code age} is greater than 17 and smaller than 200
     * @return whether the age of the user is valid
     */
    private boolean checkAge() {
        if (getAge() == INVALID_AGE || getAge() == ILLEGAL_AGE) {
            System.out.println("Your age is not accepted for this action");
            return false;
        }
        return true;
    }

}