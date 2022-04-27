package Ivin.HW;

public class DegenerateTriangleException extends Exception{
    public DegenerateTriangleException() {
        super("Вырожденный в отрезок или точку треугольник не имеет площади");
    }
}
