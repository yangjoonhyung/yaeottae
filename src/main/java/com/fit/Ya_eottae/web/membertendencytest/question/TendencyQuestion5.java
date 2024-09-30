package com.fit.Ya_eottae.web.membertendencytest.question;

public enum TendencyQuestion5 {

    PAGE5_QUESTION_1("아니요 그냥 마음에 들면 갑니다"),
    PAGE5_QUESTION_2("네 저는 다 조사해보고 가요");

    private String question;

    TendencyQuestion5(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }
}
