//QUIZ_SCORE_TRACKER
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class Main {
    public static ArrayList<String> Questions=new ArrayList<>();

    public static ArrayList<String> Answers=new ArrayList<>();

    public static int totalScore=0;
    public static double average=0.0;

    public static void AskQuestions(ArrayList<String> duplicates) throws IOException {
        Scanner Input=new Scanner(System.in);
        Random random=new Random();
        int count=0;
        while(count<4){
            int r= random.nextInt(10)+1;
            if(!duplicates.contains(Questions.get(r))){
                System.out.println(Questions.get(r));
                String answer=Input.nextLine();
                CheckAnswer(answer,r);
                duplicates.add(Questions.get(r));
                count++;
            }
        }
    }

    public static void CheckAnswer(String answer, int index){
        answer = answer.toLowerCase().trim();
        String correctAnswer = Answers.get(index).toLowerCase().trim();
        if(answer.equals(correctAnswer)){
            int score = 1 + new Random().nextInt(10); // score between 1 and 10
            totalScore += score;
            System.out.println("Correct! You got " + score + " points.");
        } else {
            System.out.println("Wrong answer.");
        }
    }

    public static void CalculateAverage(){
        average= (double) (totalScore) /4;
    }

    public static void main(String[] args) throws IOException {
        Scanner Input=new Scanner(System.in);
        System.out.println("Enter the Number of Students : ");
        String NumberOfStudents=Input.nextLine();
        String[][] Data=new String[Integer.parseInt(NumberOfStudents)][3];
        FileReader fr=new FileReader("QUIZ_QUESTIONS.txt");
        Scanner sc=new Scanner(fr);
        while(sc.hasNextLine()){
            Questions.add(sc.nextLine()+" ");
        }
        sc.close();
        fr.close();


        FileReader fr2=new FileReader("QUIZ_QUESTIONS_ANSWERS.txt");
        Scanner sc2=new Scanner(fr2);

        while(sc2.hasNextLine()){
            Answers.add(sc2.nextLine()+" ");
        }
        sc2.close();
        fr2.close();

        for(int i=0;i<Data.length;i++){
            System.out.println("Enter the Name of the Student : ");
            String Name=Input.nextLine();
            Data[i][0]=Name;
            ArrayList<String> duplicates=new ArrayList<>();
            AskQuestions(duplicates);
            Data[i][1]= String.valueOf(totalScore);
            CalculateAverage();
            Data[i][2]= String.valueOf(average);
            totalScore=0;
            average=0.0;
        }
    }
}