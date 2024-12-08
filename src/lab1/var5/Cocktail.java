package lab1.var5;

public class Cocktail extends Food {

    private String drink = null;
    private String fruit = null;

    public Cocktail(String drink, String fruit) {
        super("Коктейль");
        this.drink = drink;
        this.fruit = fruit;
    }

    @Override
    public void consume() {
        System.out.println(this + " выпит, " + this.calculateCalories() + " калорий");
    }

    public String getDrink() {
        return drink;
    }

    public String getFruit() {
        return fruit;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }


    public boolean equals(Object arg0) {
        if (super.equals(arg0)) {
            if (!(arg0 instanceof Cocktail)) return false;
            return  drink.equalsIgnoreCase(((Cocktail)arg0).getDrink())
                    && fruit.equalsIgnoreCase(((Cocktail)arg0).getFruit());
        } else
            return false;
    }

    public int calculateCalories() {
        return (drink.length() + fruit.length() + 1) * 5;
    }

    public String toString() {
        return super.toString() + " из напитка '" + drink.toUpperCase() + "' и фрукта '" + fruit.toUpperCase() + "'";
    }
}
