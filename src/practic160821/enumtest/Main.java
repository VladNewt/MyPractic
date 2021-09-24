package practic160821.enumtest;

import static practic160821.enumtest.Colors.*;

public class Main {

    public static void main(String[] args) {



       int cc = ColorsConstants.RED;
       switch (cc) {
           case ColorsConstants.RED: System.out.println("red");
               break;
           case ColorsConstants.GREEN: System.out.println("GREEN");
                break;
           case ColorsConstants.BLUE: System.out.println("BLUE");
                break;
       }
       //
        System.out.println();
       Colors c = Colors.RED;
       switch (c) {
           case RED:
               System.out.println(RED);
               break;
           case GREEN:
               System.out.println(GREEN);
               break;
           case BLUE:
               System.out.println(BLUE);
               break;
       }
       //
       test(ColorsConstants.RED);
       //
        System.out.println(RED.ordinal());
        System.out.println(RED.toString());
        System.out.println(RED.name());         //возвращает имя константы
        System.out.println(RED.GREEN);
        System.out.println(Colors.valueOf("RED"));  //получить константу по имени
        Colors.values();                            //получить константы в виде массива

        for (Colors values: values()) {
            System.out.println("value = " + values);
        }

        System.out.println(RED.number());

        RED.out();
        GREEN.out();


    }

    public static void test(int col) {
        switch (col) {
            case ColorsConstants.RED: System.out.println("RED");
                break;
            case ColorsConstants.GREEN: System.out.println("GREEN");
                break;
            case ColorsConstants.BLUE: System.out.println("BLUE");
                break;
        }
    }

    public static void test(Colors colors) {        //лучше использовать перечисления в аргументе

    }
}
