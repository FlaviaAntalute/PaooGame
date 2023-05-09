package PaooGame.Exceptions;

public class IndexCropException extends Exception{

  public  IndexCropException(String message){
      super(message);
  }
  public static void handleException(IndexCropException e)
  {
      System.out.println(e.getMessage());
      e.printStackTrace();
  }
}
