package lab8;

public class Order {

    // static variables
    private static int count;

    // instance variables
    private int ID;
    private double portion;
    private double totalPrice;
    private double discountRate = 0;
    private Food orderedFood;
    private User user;
    private boolean checkedOut = false;

    // constructors
    public Order (double portion, String name, double price) {
        setOrderedFood(new Food(name, price));
        setID(count);
        setPortion(portion);
        addOrder();
    }

    public Order (double portion, Food orderedFood) {
        setOrderedFood(orderedFood);
        setPortion(portion);
        setID(count);
        addOrder();
    }

    // accessors
    public int getID() {
        return ID;
    }
    
    public Food getOrderedFood() {
        return orderedFood;
    }

    public double getPortion() {
        return portion;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    
    public User getUser() {
        return user;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public static int getCount() {
        return count;
    }

    // mutators
    public void setID(int iD) {
        ID = iD;
    }

    public void setOrderedFood(Food orderedFood) {
        this.orderedFood = orderedFood;
    }

    public void setPortion(double portion) {
        if (portion < 0) {
            this.portion = 0;
        }
        else {
            this.portion = portion;
        }
        checkedOut = false;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public void setDiscountRate(double discountRate) {
        if (discountRate > 0) {
            this.discountRate = discountRate;
        }
    }

    // static methods


    // instance methods
    public void checkout() {
        if (getOrderedFood() != null) {
            setTotalPrice(portion * getOrderedFood().getPrice() * (1 - getDiscountRate()));
            checkedOut = true;
        }
        else {
            System.out.println("Could not checkout! Food is undefined.");
        }
    }

    public void increasePortion(double extraPortion) {
        setPortion(getPortion() + extraPortion);
    }

    @Override
    public boolean equals (Object obj) {
        if (obj instanceof Order) {
            Order order = (Order) obj;
            return (getID() == order.getID());
        }
        return false;
    }

    @Override
    public String toString() {
        if (!checkedOut) {
            return ">> Warning: This order is not complete.\nDetails for Order " + getID() +":\n\t\t" + orderedFood.getName() + "(x " + getPortion() +")\n\t\tTOTAL = " + getTotalPrice() + "\n";
        }
        return "Details for Order " + getID() +":\n\t\t" + orderedFood.getName() + "(x " + getPortion() +")\n\t\tTOTAL = " + getTotalPrice() + "\n";
    }

    // private methods
    private void addOrder() {
        count++;
    }
    
}
