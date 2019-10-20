package com.example.whatsthedeclenasion;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    private ArrayList<ArrayList<String>> list = new ArrayList<>();

    public FullscreenActivity() {
        super();
        if(debug)
            NUMBER_OF_ANSWERS = DEBUG_ITEMS;
    }



    External external = null; //new PhoneMock();

    int idOfCorrectCategory = -1;
    int NUMBER_OF_ANSWERS = 5;
    int streak = 0;

    private String[] listOfAnswers;
    private Random random = new Random();

    public boolean debug = false;
    final int DEBUG_ITEMS = 2;

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
                ViewState.get().buttonClick(answer);

                verifyAnswerAndMark(answer);

                final String[] mobileArray2 = getCategories();
                getListView().setAdapter(new ArrayAdapter<>(thiz, R.layout.mylistviewitemlayout, mobileArray2));
            }
        });

        ListView listView = findViewById(R.id.myListView);
        listView.setAdapter(adapter2);

        populateTextField();
    }

    public String[] getCategories() {
        List<String> result = new ArrayList<>();
        String[][] categoriesObject = getCategoriesObject();
        list = new ArrayList<>();
        for(String[] category : categoriesObject){
            list.add(new ArrayList<>(Arrays.asList(category)));
        }

        List<String> correctCategory = getRandomCorrectCategory();
        for(String corr : correctCategory)
            ViewState.get().addCorrectText(corr);

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals(correctCategory)){
                String categoryName = getCategoriesNames()[i];
                ViewState.get().setCurrentCategory(categoryName);
                idOfCorrectCategory = i;
            }
        }

        int randomIntegerCategory = idOfCorrectCategory;
        int randomIntegerOption = getRandom(list.get(randomIntegerCategory).size());

        String option = getCategoryAndRemoveButChecked(randomIntegerCategory, randomIntegerOption);
        result.add(option);

        for(int i = 0; i < NUMBER_OF_ANSWERS; i++) {
            randomIntegerCategory = getRandom(list.size());
            randomIntegerOption = getRandom(list.get(randomIntegerCategory).size());

            option = getCategoryAndRemoveButChecked(randomIntegerCategory, randomIntegerOption);
            result.add(option);

//            if(randomIntegerCategory == idOfCorrectCategory) {
//                if(!ViewState.get().getCorrectText().contains(option) && option != null){
//                    ViewState.get().addCorrectText(option);
//                }
//            }
        }

        return result.toArray(new String[]{});
    }

    private String getCategoryAndRemoveButChecked(int randomIntegerCategory, int randomIntegerOption) {
        try {
            ArrayList<String> rootCategory = list.get(randomIntegerCategory);
            int indexToRemove = randomIntegerOption% list.get(randomIntegerCategory).size();
            if(indexToRemove > rootCategory.size()){
                indexToRemove = indexToRemove % rootCategory.size();
            }
            String option = rootCategory.remove(indexToRemove);
            return option;
        } catch (Exception e){
            int a = 0;
            int b = a;
        }
        throw new RuntimeException("SHIT IS FUCKED UP!");
    }

    private int getRandom(int i) {
        if(external != null){
            return external.random(i - 1);
        }
        return random.nextInt(i);
    }

    private void populateTextField() {
        TextView text = findViewById(R.id.caseTextView);
        text.setText(ViewState.get().getTextContent());
    }

    private void verifyAnswerAndMark(String answer) {
        boolean correct = false;//Arrays.stream(getCategoriesObject()[idOfCorrectCategory]).anyMatch(answer::equals);

//        Optional<String> correctText = Arrays.stream(getCategoriesObject()[idOfCorrectCategory])
//                .findFirst();

//        if(correctText.isPresent()) {
        ViewState.get().setLastCategory(getCategoriesNames()[idOfCorrectCategory]);


        if(ViewState.get().getCorrectText().contains(answer)) {
            correct = true;
        }

        View view = findViewById(R.id.background);

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
                debugSlice(genitive)//,
                //debugSlice(dative)
        };
        return result;
    }


    private List<String> getRandomCorrectCategory() {
        if(debug){
            return Arrays.asList(list.get(0).toArray(new String[list.get(0).size()]));
        }
        return Arrays.asList(categories[random.nextInt(categories.length)]);
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

    public External getExternal() {
        return external;
    }

    public void setExternal(External external) {
        this.external = external;
    }

}

