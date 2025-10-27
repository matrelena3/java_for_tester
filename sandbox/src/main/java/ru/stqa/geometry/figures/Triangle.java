package ru.stqa.geometry.figures;

public class Triangle {

    public static void printTriangleArea(double a, double b, double c) {
        System.out.println(String.format(
                "Площадь треугольника со сторонами %f, %f, %f = %f",  a,  b,  c, triangleSqrt(a, b, c) ));
    }


   public static double triangleSqrt(double a, double b, double c) {

       return Math.sqrt(trianglePerimetr(a, b, c) * (trianglePerimetr(a, b, c) - a) * (trianglePerimetr(a, b, c) - b) * (trianglePerimetr(a, b, c) - c));
    }

    public static double trianglePerimetr(double a, double b, double c) {
        return (a + b + c) / 2;
    }


}

