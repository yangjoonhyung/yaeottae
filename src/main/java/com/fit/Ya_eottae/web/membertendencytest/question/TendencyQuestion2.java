package com.fit.Ya_eottae.web.membertendencytest.question;

public enum TendencyQuestion2 {

    PAGE2_QUESTION_1("네 저는 신라면도 못먹어요 ㅠㅠ"),
    PAGE2_QUESTION_2("신라면 정도는 거뜬하죠!");

    private String question;

    TendencyQuestion2(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }
}
