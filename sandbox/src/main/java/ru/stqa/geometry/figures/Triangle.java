package ru.stqa.geometry.figures;

public record Triangle (double a, double b, double c) {

    public Triangle {
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException( "Сторона треугольника не может быть отрицательной");
        }
        if ((a + b) > c || (a + c) > b || (c + b) > a) {
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
}

