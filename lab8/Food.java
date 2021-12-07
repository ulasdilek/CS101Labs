package lab8;

public class Food {
    
    // static variables


    // instance variables
    private int calories;
    private double price;
    private String name;
    private String ingredients; // seperated by ", "
    private String type;

    // constructors
    public Food(String name, double price) {
        setName(name);
        setPrice(price);
        setCalories(0);
        setIngredients("");
        setType("");
    }

    public Food(String name, String ingredients, int calories, String type, double price) {
        setName(name);
        setIngredients(ingredients);
        setCalories(calories);
        setType(type);
        setPrice(price);
    }

    // accessors
    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public int getCalories() {
        return calories;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    // mutators
    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setCalories(int calories) {
        if (calories < 0) {
            this.calories = 0;
        }
        else {
            this.calories = calories;
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        if (price < 0) {
            this.price = 0;
        }
        else {
            this.price = roundDouble(price, 2);
        }
    }

    // static methods


    // instance methods
    public void addNewIngredients (String ingredient) {
        if (ingredients.length() == 0) {
            setIngredients(ingredient);
        }
        else {
            setIngredients(getIngredients() + ", " + ingredient);
        }
    }

    public boolean doesContain (String ingredient) {
        return ingredients.contains(ingredient);
    }

    @Override
    public boolean equals (Object obj) {
        if (obj instanceof Food) {
            Food food = (Food) obj;
            return ( getName().equals(food.getName()) && getIngredients().equals(food.getIngredients()) );
        }
        return false;
    }

    @Override
    public String toString() {
        return getName() + " is a(n) " + getType() + " dish.\nIt includes " + getIngredients() + ".\nSingle portion contains " + getCalories() + " calories.\nSingle serving cost = " + getPrice();
    }

    // private methods
    /**
     * A utility to be able to format the total price
     * @param d the double to be formatted
     * @param places decimal places to be used
     * @return {@code d} rounded to {@code places} decimal places
     */
    private double roundDouble(double d, int places) {
        int factor = 1;
        for (int i = 0; i < places; i++) {
            factor *= 10;
        }
        d = d * factor;
        d = Math.round(d);
        d = d / factor;
        return d;
    }
    
}
