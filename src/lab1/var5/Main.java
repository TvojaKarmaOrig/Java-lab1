package lab1.var5;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    // Конструктор класса отсутствует!!!

    // Главный метод главного класса
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        // Определение ссылок на продукты завтрака
        Food[] breakfast = new Food[20];
        // Анализ аргументов командной строки и создание для них
        // экземпляров соответствующих классов для завтрака
        int calorieValue = 0;
        int itemsSoFar = 0;
        boolean isCaloriesCalculate = false;
        boolean isNeedToSort = false;
        for (String arg: args) {
            String[] parts = arg.split("/");

            if(parts[0].equals("-calories")){
                isCaloriesCalculate = true;
                continue;
            }
            if(parts[0].equals("-sort")){
                isNeedToSort = true;
                continue;
            }

            Class myClass;

            try {
                myClass = Class.forName("lab1.var5." + parts[0]);
            }
            catch (ClassNotFoundException e){
                System.out.println("Извините, продукта '" + parts[0] + "' нет в рационе завтрака, он будте пропущен.");
                continue;
            }

            try{
                if (parts.length == 1) {
                    // дополнительных параметров нет
                    Constructor constructor = myClass.getConstructor();
                    breakfast[itemsSoFar] = (Food)constructor.newInstance();
                } else
                if (parts.length == 2) {
                    // параметр в parts[1]
                    Constructor constructor = myClass.getConstructor(String.class);
                    breakfast[itemsSoFar] = (Food) constructor.newInstance(parts[1]);
                } else
                if (parts.length == 3) {
                    Constructor constructor = myClass.getConstructor(String.class, String.class);
                    breakfast[itemsSoFar] = (Food) constructor.newInstance(parts[1], parts[2]);
                }
            }
            catch (NoSuchMethodException e){
                System.out.println("Извините, продукта '" + parts[0] +
                        "' с такими параметрами нет в рационе завтрака, он будте пропущен.");
                continue;
            }


            calorieValue += breakfast[itemsSoFar].calculateCalories();
            itemsSoFar++;
        }

        Food f = breakfast[0];

        if(isNeedToSort)
            Arrays.sort(breakfast, new Comparator<Food>() {
                    @Override
                    public int compare(Food o1, Food o2) {
                        if(o1 == null) return 1;
                        if(o2 == null) return -1;
                        int c1 = o1.calculateCalories();
                        int c2 = o2.calculateCalories();
                        return Integer.compare(c2, c1);
                    }
                }
            );

        // Перебор всех элементов массива
        for (Food item: breakfast)
            if (item!=null)
                // Если элемент не null – употребить продукт
                item.consume();
            else
                // Если дошли до элемента null – значит достигли конца
                // списка продуктов, ведь 20 элементов в массиве было
                // выделено с запасом, и они могут быть не
                // использованы все
                break;
        if(isCaloriesCalculate){
            System.out.println( "Общая калорийность: " + calorieValue);
        }

        System.out.println("Количество продуктов как '" + f.getName() + "': " + countOfFood(f, breakfast));
        System.out.println("Всего хорошего!");
    }

    public static int countOfFood(Food f, Food[] breakfast){
        int count = 0;
        for(Food item: breakfast){
            if(item != null && item.equals(f)) {
                count++;
            }
        }
        return count;
    }
}