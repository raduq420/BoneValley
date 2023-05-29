package GamePackage;

public class OverflowException extends Exception {
    public OverflowException()
    {
        System.out.println("Too many enemies!!");
    }
}
