package API;

import java.util.ArrayList;

public class QuizLogic
{
    public static Question question;

    public static String questionCategory;
    public static int questionPoints;

    public static String[] playerNames = {"Player 1", "Player 2"};
    public static int[] scores = {0,0};

    public static ArrayList<String> categories = new ArrayList<>();

    public static  int currentQuestion = -1;

    public static void showCategories()
    {
        for (String c : categories)
        {
            System.out.println(c);
        }
    }
}
