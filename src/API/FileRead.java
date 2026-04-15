package API;

import java.io.*;

public class FileRead
{
    public void readCSV()
    {
        String line;
        String filename = "src/resources/questions.csv" ;

        try (BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            while ((line = br.readLine()) != null)
            {
                //χωρίζονται απο κόμμα
                String[] values = line.split(",");
                new Question(values[0], values[1]);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }

    static
    {
        String filename = "src/resources/questions.csv" ;
        int lines=0;
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            while (reader.readLine() != null)
            {
                lines++;
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        Question.questionNumber = lines;
        Question.questions = new Question[lines];
    }
}