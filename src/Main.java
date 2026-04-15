import API.FileRead;
import GUI.AppWindow;



public class Main
{
    public static void main(String[] args)
    {
        FileRead f = new FileRead();
        f.readCSV();
        AppWindow A = new AppWindow();
        A.start();
    }

}