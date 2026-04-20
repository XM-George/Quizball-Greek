package API;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Question
{
    public static ArrayList<Question> questions = new ArrayList<>();

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
        questions.add(this);
    }

    public Question(String question, String answer, int points, String category)
    {
        this.question = question;
        this.answer = answer;
        this.points = points;
        categories.get(category)[points-1].add(this);
        questions.add(this);
    }

    public static Question getQuestion(String category)
    {
        return null;
    }

    public static void removeQuestion(int currentQuestion)
    {
        if(currentQuestion != -1) {
            Question.questions.remove(currentQuestion);
        }
    }

    public static boolean checkIfEmpty()
    {
        if(questions.isEmpty())
        {
            return true;
        }
        for(String key : categories.keySet())
        {
            for(int i = 0; i < categories.get(key).length;i++)
            {
                if(!categories.get(key)[i].isEmpty())
                {
                    return false;
                }
            }
        }
        return true;
    }
}