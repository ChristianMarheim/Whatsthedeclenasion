package com.example.whatsthedeclenasion;

import android.annotation.SuppressLint;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.ActionBar;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Stack;

import static com.example.whatsthedeclenasion.Data.*;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends FullscreenActivitySuper {


    int idOfCorrectCategory = -1;
    int NUMBER_OF_ANSWERS = 5;

    int streak = 0;
    int iterations = 0;

    String lastCorrectAnswerText = "";
    private String[] listOfAnswers;

    private String[] getCategories(){
        List<String> result = new ArrayList<>();
        List<String> correctOptions = new ArrayList<>();
        Random random = new Random();
        lastCorrectAnswerText = "";
        idOfCorrectCategory = random.nextInt(categories.length);

        List<String[]> categoriesList = (List<String[]>) Arrays.asList(categories);


        for(int i = 0; i < NUMBER_OF_ANSWERS; i++) {
            int randomIntegerCategory = random.nextInt(categories.length);
        }

        Stack<String> categoriesStack = new Stack<>();

        for(int i = 0; i < NUMBER_OF_ANSWERS; i++){
            int randomIntegerCategory = random.nextInt(categories.length);
            String[] category = categories[randomIntegerCategory];
            int randomIntegerOption = random.nextInt(category.length);
            String option = category[randomIntegerOption];
            result.add(option);

            if(isOptionCorrect()){
                lastCorrectAnswerText = lastCorrectAnswerText + ", " + option;
            }

            if(randomIntegerCategory == idOfCorrectCategory) {

                correctOptions.add(option);

            }

        }

        return result.toArray(new String[] {});
    }

    private boolean isOptionCorrect() {
        return false;
    }

    public void drawUi() {
        setContentView(R.layout.activity_fullscreen);


        listOfAnswers = getCategories();
        shuffleUiElements(listOfAnswers);

        final ArrayAdapter adapter2 = new ArrayAdapter<>(this, R.layout.mylistviewitemlayout, listOfAnswers);
        final FullscreenActivitySuper thiz = this;
        setListView(findViewById(R.id.myListView));

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
                String answer = (String) adapter.getItemAtPosition(position);
                verifyAnswerAndMark(answer);

                final String[] mobileArray2 = getCategories();
                getListView().setAdapter(new ArrayAdapter<>(thiz, R.layout.mylistviewitemlayout, mobileArray2));
            }
        });

        populateTextField();

        ListView listView = findViewById(R.id.myListView);
        listView.setAdapter(adapter2);
    }

    private void populateTextField() {
        TextView text = findViewById(R.id.caseTextView);
        text.setText(categoryNames[idOfCorrectCategory] + " streak=" + streak + ", iter.=" + iterations + ", correct=" + lastCorrectAnswerText);
    }


    private void verifyAnswerAndMark(String answer) {
        boolean correct = Arrays.stream(Data.categories[idOfCorrectCategory]).anyMatch(answer::equals);
        Optional<String> correctText = Arrays.stream(categories[idOfCorrectCategory]).filter(answer::equals).findFirst();
        if(correctText.isPresent())
            lastCorrectAnswerText = correctText.get();

        View view = findViewById(R.id.background);
        if(correct){
            streak++;
            iterations++;
            view.setBackgroundColor(MYGREEN);
        } else {
            streak = 0;
            view.setBackgroundColor(MYRED);
        }
    }

    static void shuffleUiElements(String[] ar)
    {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

}
