public class Triangle {
    public static void main(String[] args) {
        printTriangleArea (3.00, 6.00, 2.00);

    }

    private static void printTriangleArea(double a, double b, double c) {
        System.out.println("Площадь треугольника со сторонами " + a + ", " + b + ", " + c + " = " + triangleSqrt ( a, b, c) );
    }

    private static double triangleSqrt(double a, double b, double c) {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }


}

