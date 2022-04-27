package Ivin.HW;

public class InvalidTriangleException extends Exception{
    public InvalidTriangleException() {
        super("Треугольник не существует");
    }
}
