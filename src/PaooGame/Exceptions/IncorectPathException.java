package PaooGame.Exceptions;

public class IncorectPathException extends Exception{
    public IncorectPathException(String message)
    {
        super(message);
    }
    public static void handleException(IncorectPathException e)
    {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
}
