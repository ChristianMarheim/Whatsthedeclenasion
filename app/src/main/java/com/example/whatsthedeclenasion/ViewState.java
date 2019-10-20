package com.example.whatsthedeclenasion;

public class ViewState {
    private static String nextCorrectAnswerText = "";
    private static String lastCorrectAnswerText = "";
    private static String currentCategory;
    private static String lastCategory;
    private static String answer;

    private static int iterations = 0;

    private static ViewState thiz;

    public static ViewState get(){
        if(thiz == null)
            thiz = new ViewState();
        return thiz;
    }

    public void buttonClick(String answer){
        thiz.answer = answer;
        thiz.lastCorrectAnswerText = thiz.nextCorrectAnswerText;
        thiz.nextCorrectAnswerText = "";
        thiz.iterations++;
    }

    public void setCurrentCategory(String input) {
        thiz.currentCategory = input;
    }

    public void setLastCategory(String input) {
        thiz.lastCategory = input;
    }

    public String getCorrectText() {
        if(thiz.lastCorrectAnswerText == null)
            return "error";
        return thiz.lastCorrectAnswerText;
    }

    public String getTextContent() {
        String textContent = "Current category: [" + thiz.currentCategory + "]\n";
        if(thiz.iterations > 0) {
            textContent += "Last category:[" + thiz.lastCategory + "]\n" +
                    "Your answer=" + answer + "\n" +
                    "LastCorrect=\n" + lastCorrectAnswerText + "";
        }
        return textContent;
    }

    public void addCorrectText(String s) {
        if(s != null && !s.isEmpty() && !nextCorrectAnswerText.contains(s)){
            nextCorrectAnswerText += "[" + s + "]\n";
        } else {
            int a = 0;
            int b = a;
        }

    }
}
