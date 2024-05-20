package easy.tuto.RegletteCSSCT;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ans1, ans2, ans3, ans4, ans5, ans6, ans7, ans8,ans9, ans10;
    Button submitBtn;

    String score="";
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    //SimpleDateFormat parseFormat = new SimpleDateFormat("");
    //Date date =new Date();
    //String today=parseFormat.format(date);
    //coincoin
    Date today = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    String TodayF = sdf.format(today);
    String csvName= TodayF+".cssct";
    public static File dataDir;
    File fileSaveDir = Environment.getExternalStoragePublicDirectory(Environment. DIRECTORY_DOWNLOADS);
    File outFile = new File(fileSaveDir, csvName);
    BufferedWriter out;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ans1 = findViewById(R.id.ans_1);
        ans2 = findViewById(R.id.ans_2);
        ans3 = findViewById(R.id.ans_3);
        ans4 = findViewById(R.id.ans_4);
        ans5 = findViewById(R.id.ans_5);
        ans6 = findViewById(R.id.ans_6);
        ans7 = findViewById(R.id.ans_7);
        ans8 = findViewById(R.id.ans_8);
        ans9 = findViewById(R.id.ans_9);
        ans10 = findViewById(R.id.ans_10);
        submitBtn = findViewById(R.id.submit_btn);

        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);
        ans4.setOnClickListener(this);
        ans5.setOnClickListener(this);
        ans6.setOnClickListener(this);
        ans7.setOnClickListener(this);
        ans8.setOnClickListener(this);
        ans9.setOnClickListener(this);
        ans10.setOnClickListener(this);

        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Reglette du bonheur");

        loadNewQuestion();




    }

    @Override
    public void onClick(View view) {

        ans1.setBackgroundColor(Color.WHITE);
        ans2.setBackgroundColor(Color.WHITE);
        ans3.setBackgroundColor(Color.WHITE);
        ans4.setBackgroundColor(Color.WHITE);
        ans5.setBackgroundColor(Color.WHITE);
        ans6.setBackgroundColor(Color.WHITE);
        ans7.setBackgroundColor(Color.WHITE);
        ans8.setBackgroundColor(Color.WHITE);
        ans9.setBackgroundColor(Color.WHITE);
        ans10.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            score+=selectedAnswer+";";
            selectedAnswer="";
            currentQuestionIndex++;
            loadNewQuestion();


        }else{
            //choices button clicked
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }

    }

    void loadNewQuestion(){

        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }

        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ans1.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ans2.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ans3.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ans4.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
        ans5.setText(QuestionAnswer.choices[currentQuestionIndex][4]);
        ans6.setText(QuestionAnswer.choices[currentQuestionIndex][5]);
        ans7.setText(QuestionAnswer.choices[currentQuestionIndex][6]);
        ans8.setText(QuestionAnswer.choices[currentQuestionIndex][7]);
        ans9.setText(QuestionAnswer.choices[currentQuestionIndex][8]);
        ans10.setText(QuestionAnswer.choices[currentQuestionIndex][9]);


    }

    void finishQuiz(){
        new AlertDialog.Builder(this)
                .setTitle("FIN DU QUESTIONNAIRE")
                .setMessage("Passe a ton voisin")
                .setPositiveButton("J'ai Terminé",(dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();


    }

    void restartQuiz(){

        try {
            try {
                if(!outFile.exists()){
                    outFile.createNewFile();
                }
                out = new BufferedWriter(new FileWriter(outFile,true), 1024);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            out.write(score+"\r\n");;
            out.close();


        }
        catch (IOException e)
        {
            new AlertDialog.Builder(this)
                    .setTitle("CA MARCHE PAS")
                    .setMessage(e.getMessage())
                    .setCancelable(true)
                    .show();
        }
        score = "";
        currentQuestionIndex =0;
        loadNewQuestion();
    }

}