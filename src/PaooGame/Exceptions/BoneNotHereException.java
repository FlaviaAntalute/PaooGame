package PaooGame.Exceptions;

public class BoneNotHereException extends Exception{
    public BoneNotHereException(String message)
    {
        super(message);
    }
    public static void handleException(BoneNotHereException e)
    {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
}
