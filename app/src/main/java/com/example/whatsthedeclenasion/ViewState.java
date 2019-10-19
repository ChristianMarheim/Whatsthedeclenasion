package com.example.whatsthedeclenasion;

public class ViewState {
    private static String nextCorrectAnswerText;
    private static String lastCorrectAnswerText;
    private static String currentCategory;
    private static String lastCategory;

    private static int iterations = 0;

    private static ViewState thiz;

    public static ViewState get(){
        if(thiz == null)
            thiz = new ViewState();
        return thiz;
    }

    public void buttonClick(){
        thiz.lastCorrectAnswerText = thiz.nextCorrectAnswerText;
        thiz.nextCorrectAnswerText = null;
        thiz.iterations++;
    }

    public void setCurrentCategory(String input) {
        thiz.currentCategory = input;
    }

    public void setLastCategory(String input) {
        thiz.lastCategory = input;
    }

    public void setCorrectText(String input) {
        thiz.nextCorrectAnswerText = input;
    }


    public String getTextContent() {
        String textContent = thiz.currentCategory;
        if(thiz.iterations > 0) {
            textContent += "\n lastCat='" + thiz.lastCategory + "', last='" + lastCorrectAnswerText + "'";
        }
        return textContent;
    }

    public void addCorrectText(String s) {
        nextCorrectAnswerText += s;
    }
}
