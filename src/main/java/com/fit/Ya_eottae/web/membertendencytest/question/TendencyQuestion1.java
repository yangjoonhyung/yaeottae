package com.fit.Ya_eottae.web.membertendencytest.question;

public enum TendencyQuestion1 {

    PAGE1_QUESTION_1("대화에 집중 할 수 있는 차분한 분위기"),
    PAGE1_QUESTION_2("웃고 떠들기 좋은 활기찬 분위기");

    private String question;

    TendencyQuestion1(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }
}
