package com.fit.Ya_eottae.web.membertendencytest.question;

public enum TendencyQuestion4 {

    PAGE4_QUESTION_1("1시간 가볍네요!"),
    PAGE4_QUESTION_2("다른곳으로 먹으러 가겠습니다..");

    private String question;

    TendencyQuestion4(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }
}
