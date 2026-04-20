package API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Question
{
    public static String[] categoryNames = {"questions" , "geography" , "gossip" , "hiddenQuestion" , "history" , "top5"};
    public static Map<String, ArrayList<Question>[]> categories = new HashMap<>();

    public String question;
    public String answer;
    public int points;

    static
    {
        for (String category : categoryNames)
        {
            categories.put(category , new ArrayList[]{new ArrayList<>(), new ArrayList<>(), new ArrayList<>()});
        }
    }

    public Question()
    {

    }

    public Question(String question, String answer, String category)
    {
        this.question = question;
        this.answer = answer;
        this.points = 0;
        categories.get(category)[points].add(this);
    }

    public Question(String question, String answer, int points, String category)
    {
        this.question = question;
        this.answer = answer;
        this.points = points;
        categories.get(category)[points-1].add(this);
    }

    public static void getAndThenRemoveQuestion()
    {
        int random = (int) (Math.random() * categories.get(QuizLogic.questionCategory)[QuizLogic.questionPoints - 1].size());
        QuizLogic.currentQuestion = categories.get(QuizLogic.questionCategory)[QuizLogic.questionPoints - 1].get(random);
        categories.get(QuizLogic.questionCategory)[QuizLogic.questionPoints - 1].remove(QuizLogic.currentQuestion);
    }

    public static boolean checkIfEmpty()
    {
        if(QuizLogic.categories.isEmpty())
        {
            return false;
        }
        for(String c : QuizLogic.categories) {
            for (int i = 0; i < 3; i++)
            {
                if(!categories.get(c)[i].isEmpty())
                {
                    return false;
                }
            }
        }
        return true;
    }
}