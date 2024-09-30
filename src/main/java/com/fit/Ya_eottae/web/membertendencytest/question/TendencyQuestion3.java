package com.fit.Ya_eottae.web.membertendencytest.question;

public enum TendencyQuestion3 {

    PAGE3_QUESTION_1("필요한 때에만 조용히 다가오는 서비스"),
    PAGE3_QUESTION_2("자주 대화를 나누며 활발히 응대하는 서비스");

    private String question;

    TendencyQuestion3(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }
}
