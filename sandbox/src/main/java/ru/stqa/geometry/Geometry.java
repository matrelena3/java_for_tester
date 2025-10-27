package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(7.0);
        Square.printSquareArea(5.0);
        Square.printSquareArea(4.0);

        Rectangle.printRectanleArea(4.0, 5.0);
        Rectangle.printRectanleArea(6.0, 9.0);

        Triangle.printTriangleArea (3.00, 3.00, 2.00);
    }

}
