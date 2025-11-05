package ru.stqa.geometry.figures;

import java.util.Objects;

public record Triangle (double a, double b, double c) {

    public Triangle {

        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException( "Сторона треугольника не может быть отрицательной");
        }
        if (a + b > c || a + c > b || c + b > a) {
            throw new IllegalArgumentException( "Сумма двух любых сторон не может быть меньше третьей");
        }
    }

    public static void printTriangleArea(Triangle t) {
        var text = String.format("Площадь треугольника со сторонами %f, %f, %f = %f",  t.a,  t.b,  t.c, t.triangleSqrt());
        System.out.println(text);
    }
    public static void printTrianglePerimetr(Triangle r) {
        var text = String.format("Периметр треугольника со сторонами %f, %f, %f = %f",  r.a,  r.b,  r.c, r.trianglePerimetr());
        System.out.println(text);
    }

    public double triangleSqrt() {
        double p = (this.a + this.b + this.c) / 2;
        return Math.sqrt(p * (p - this.a) * (p - this.b) * (p - this.c));
    }

    public double trianglePerimetr() {
        return (this.a + this.b + this.c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(triangle.a, this.a) == 0 && Double.compare(triangle.b, this.b) == 0 && Double.compare(triangle.c, this.c) == 0)
                || (Double.compare(triangle.a, this.b) == 0 && Double.compare(triangle.b, this.a) == 0 && Double.compare(triangle.c, this.c) == 0)
                || (Double.compare(triangle.a, this.c) == 0 && Double.compare(triangle.b, this.a) == 0 && Double.compare(triangle.c, this.c) == 0)
                || (Double.compare(triangle.a, this.c) == 0 && Double.compare(triangle.b, this.b) == 0 && Double.compare(triangle.c, this.c) == 0);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}

