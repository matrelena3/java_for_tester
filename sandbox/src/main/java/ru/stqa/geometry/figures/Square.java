package ru.stqa.geometry.figures;

public class Square {
   public static void printSquareArea(double side) {
        System.out.println("Площадь квадрата со стороной " + side + " = " + Area(side));
    }

    public static double Area(double a) {
        return a * a;
    }

    public static double perimetr(double a) {
       return 4 * a;
    }
}
