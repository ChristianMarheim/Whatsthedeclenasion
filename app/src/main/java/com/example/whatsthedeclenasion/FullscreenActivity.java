package com.example.whatsthedeclenasion;

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

import static com.example.whatsthedeclenasion.Data.*;

/*
List<String[]> categoriesList = (List<String[]>) Arrays.asList(getCategoriesObject());
for(int i = 0; i < NUMBER_OF_ANSWERS; i++) {
    int randomIntegerCategory = random.nextInt(getCategoriesObject().length);
}
Stack<String> categoriesStack = new Stack<>();
*/

public class FullscreenActivity extends FullscreenActivitySuper {

    public FullscreenActivity() {
        super();
        if(debug)
            NUMBER_OF_ANSWERS = DEBUG_ITEMS;
    }

    int idOfCorrectCategory = -1;
    int NUMBER_OF_ANSWERS = 5;
    int streak = 0;
    int iterations = 0;

    String nextCorrectAnswerText = "";
    String lastCorrectAnswerText = "";
    private String[] listOfAnswers;
    private Random random = new Random();
    private String lastCategory;

    private boolean debug = true;
    final int DEBUG_ITEMS = 3;

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
                lastCorrectAnswerText = nextCorrectAnswerText;
                populateTextField();
            }
        });

        ListView listView = findViewById(R.id.myListView);
        listView.setAdapter(adapter2);

        populateTextField();
    }

    private String[] getCategories() {
        List<String> result = new ArrayList<>();
        List<String> correctOptions = new ArrayList<>();
        nextCorrectAnswerText = "";
        String[][] categoriesObject = getCategoriesObject();
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        for(String[] category : categoriesObject){
            list.add(new ArrayList<>(Arrays.asList(category)));
        }

        idOfCorrectCategory = random.nextInt(categoriesObject.length);

        for(int i = 0; i < NUMBER_OF_ANSWERS; i++) {
            int randomIntegerCategory = random.nextInt(list.size());
            int randomIntegerOption = random.nextInt(list.get(randomIntegerCategory).size());
            String option = list.get(randomIntegerCategory).remove(randomIntegerOption);
            result.add(option);

            if(randomIntegerCategory == idOfCorrectCategory) {
                nextCorrectAnswerText = nextCorrectAnswerText + ", " + option;
                correctOptions.add(option);
            }
        }

        return result.toArray(new String[]{});
    }

    private void populateTextField() {
        String textContent = getCategoriesNames()[idOfCorrectCategory];
        if(iterations > 0) {
            textContent += "\n lastCat='" + lastCategory + "', last='" + lastCorrectAnswerText + "'";
        }

        TextView text = findViewById(R.id.caseTextView);
        text.setText(textContent);
    }

    private void verifyAnswerAndMark(String answer) {
        boolean correct = Arrays.stream(getCategoriesObject()[idOfCorrectCategory])
                .anyMatch(answer::equals);

        Optional<String> correctText = Arrays.stream(getCategoriesObject()[idOfCorrectCategory])
                .findFirst();

        if(correctText.isPresent()) {
            nextCorrectAnswerText = correctText.get();
            lastCategory = getCategoriesNames()[idOfCorrectCategory];
        }

        View view = findViewById(R.id.background);
        iterations++;
        if(correct) {
            streak++;
            view.setBackgroundColor(MYGREEN);
        } else {
            streak = 0;
            view.setBackgroundColor(MYRED);
        }
        populateTextField();
    }





    /*
    HELPERS     HELPERS     HELPERS     HELPERS     HELPERS     HELPERS     HELPERS     HELPERS
    HELPERS     HELPERS     HELPERS     HELPERS     HELPERS     HELPERS     HELPERS     HELPERS
    HELPERS     HELPERS     HELPERS     HELPERS     HELPERS     HELPERS     HELPERS     HELPERS
     */
    private String[] getCategoriesNames() {
        if(debug){
            return Arrays.asList(categoryNames).subList(0,DEBUG_ITEMS).toArray(new String[DEBUG_ITEMS]);
        }
        return categoryNames;
    }

    private String[][] getCategoriesObject() {
        if(debug){
            return debugCategories();
        }
        return categories;
    }

    private String [][] debugCategories() {
        String [][] result = {
                debugSlice(normative),
                debugSlice(genitive),
                debugSlice(dative)
        };
        return result;
    }

    private String[] debugSlice(String [] input) {
        List<String> strings = Arrays.asList(input);
        strings = strings.subList(0, DEBUG_ITEMS);
        String[] result = strings.toArray(new String[strings.size()]);
        return  result;
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
