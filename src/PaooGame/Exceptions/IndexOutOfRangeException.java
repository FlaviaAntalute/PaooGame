package PaooGame.Exceptions;

public class IndexOutOfRangeException extends Exception{
    public  IndexOutOfRangeException(String message){
        super(message);
    }
    public static void handleException(IndexOutOfRangeException e)
    {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }

}
