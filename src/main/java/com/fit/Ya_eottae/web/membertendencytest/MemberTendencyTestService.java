package com.fit.Ya_eottae.web.membertendencytest;

import com.fit.Ya_eottae.domain.member.MemberTendency;
import com.fit.Ya_eottae.repository.memberrepository.MemberRepository;
import com.fit.Ya_eottae.web.membertendencytest.question.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberTendencyTestService {

    private final MemberRepository memberRepository;

    public MemberTendency checkTendency(TendencyQuestion1 answer1, TendencyQuestion2 answer2, TendencyQuestion3 answer3,
                                        TendencyQuestion4 answer4, TendencyQuestion5 answer5) {

        if (answer1 == TendencyQuestion1.PAGE1_QUESTION_1 && answer2 == TendencyQuestion2.PAGE2_QUESTION_1
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_1 && answer4 == TendencyQuestion4.PAGE4_QUESTION_1
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_1)
        {

            return MemberTendency.SOFT_TOFU_KIMCHI;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_1 && answer2 == TendencyQuestion2.PAGE2_QUESTION_1
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_1 && answer4 == TendencyQuestion4.PAGE4_QUESTION_1
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_2)
        {

            return MemberTendency.SOFT_TOFU_KIMCHI;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_1 && answer2 == TendencyQuestion2.PAGE2_QUESTION_1
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_1 && answer4 == TendencyQuestion4.PAGE4_QUESTION_2
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_1)
        {

            return MemberTendency.SOFT_TOFU_KIMCHI;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_1 && answer2 == TendencyQuestion2.PAGE2_QUESTION_1
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_1 && answer4 == TendencyQuestion4.PAGE4_QUESTION_2
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_2)
        {

            return MemberTendency.SOFT_TOFU_KIMCHI;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_2 && answer2 == TendencyQuestion2.PAGE2_QUESTION_1
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_2 && answer4 == TendencyQuestion4.PAGE4_QUESTION_1
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_1)
        {

            return MemberTendency.PATIENCEFUL_SPICY_TTEOKBOKKI;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_2 && answer2 == TendencyQuestion2.PAGE2_QUESTION_1
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_2 && answer4 == TendencyQuestion4.PAGE4_QUESTION_1
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_2)
        {

            return MemberTendency.PATIENCEFUL_SPICY_TTEOKBOKKI;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_2 && answer2 == TendencyQuestion2.PAGE2_QUESTION_1
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_2 && answer4 == TendencyQuestion4.PAGE4_QUESTION_2
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_1)
        {

            return MemberTendency.PATIENCEFUL_SPICY_TTEOKBOKKI;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_2 && answer2 == TendencyQuestion2.PAGE2_QUESTION_1
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_2 && answer4 == TendencyQuestion4.PAGE4_QUESTION_2
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_2)
        {

            return MemberTendency.PATIENCEFUL_SPICY_TTEOKBOKKI;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_2 && answer2 == TendencyQuestion2.PAGE2_QUESTION_2
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_2 && answer4 == TendencyQuestion4.PAGE4_QUESTION_1
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_1)
        {

            return MemberTendency.PASSIONATE_BULDAK;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_2 && answer2 == TendencyQuestion2.PAGE2_QUESTION_2
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_2 && answer4 == TendencyQuestion4.PAGE4_QUESTION_1
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_2)
        {

            return MemberTendency.PASSIONATE_BULDAK;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_2 && answer2 == TendencyQuestion2.PAGE2_QUESTION_2
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_2 && answer4 == TendencyQuestion4.PAGE4_QUESTION_2
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_1)
        {

            return MemberTendency.PASSIONATE_BULDAK;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_2 && answer2 == TendencyQuestion2.PAGE2_QUESTION_2
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_2 && answer4 == TendencyQuestion4.PAGE4_QUESTION_2
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_2)
        {

            return MemberTendency.PASSIONATE_BULDAK;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_1 && answer2 == TendencyQuestion2.PAGE2_QUESTION_2
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_1 && answer4 == TendencyQuestion4.PAGE4_QUESTION_1
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_1)
        {

            return MemberTendency.CAREFUL_CHEONGYANG_PEPPER;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_1 && answer2 == TendencyQuestion2.PAGE2_QUESTION_2
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_1 && answer4 == TendencyQuestion4.PAGE4_QUESTION_1
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_2)
        {

            return MemberTendency.CAREFUL_CHEONGYANG_PEPPER;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_1 && answer2 == TendencyQuestion2.PAGE2_QUESTION_2
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_1 && answer4 == TendencyQuestion4.PAGE4_QUESTION_2
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_1)
        {

            return MemberTendency.CAREFUL_CHEONGYANG_PEPPER;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_1 && answer2 == TendencyQuestion2.PAGE2_QUESTION_2
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_1 && answer4 == TendencyQuestion4.PAGE4_QUESTION_2
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_2)
        {

            return MemberTendency.CAREFUL_CHEONGYANG_PEPPER;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_1 && answer2 == TendencyQuestion2.PAGE2_QUESTION_1
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_2 && answer4 == TendencyQuestion4.PAGE4_QUESTION_1
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_1)
        {

            return MemberTendency.MILD_BRAISED_RIBS;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_1 && answer2 == TendencyQuestion2.PAGE2_QUESTION_1
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_2 && answer4 == TendencyQuestion4.PAGE4_QUESTION_1
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_2)
        {

            return MemberTendency.MILD_BRAISED_RIBS;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_1 && answer2 == TendencyQuestion2.PAGE2_QUESTION_1
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_2 && answer4 == TendencyQuestion4.PAGE4_QUESTION_2
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_1)
        {

            return MemberTendency.MILD_BRAISED_RIBS;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_1 && answer2 == TendencyQuestion2.PAGE2_QUESTION_1
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_2 && answer4 == TendencyQuestion4.PAGE4_QUESTION_2
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_2)
        {

            return MemberTendency.MILD_BRAISED_RIBS;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_1 && answer2 == TendencyQuestion2.PAGE2_QUESTION_2
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_2 && answer4 == TendencyQuestion4.PAGE4_QUESTION_1
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_1)
        {

            return MemberTendency.COLD_MALATANG;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_1 && answer2 == TendencyQuestion2.PAGE2_QUESTION_2
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_2 && answer4 == TendencyQuestion4.PAGE4_QUESTION_1
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_2)
        {

            return MemberTendency.COLD_MALATANG;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_1 && answer2 == TendencyQuestion2.PAGE2_QUESTION_2
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_2 && answer4 == TendencyQuestion4.PAGE4_QUESTION_2
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_1)
        {

            return MemberTendency.COLD_MALATANG;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_1 && answer2 == TendencyQuestion2.PAGE2_QUESTION_2
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_2 && answer4 == TendencyQuestion4.PAGE4_QUESTION_2
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_2)
        {

            return MemberTendency.COLD_MALATANG;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_2 && answer2 == TendencyQuestion2.PAGE2_QUESTION_1
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_1 && answer4 == TendencyQuestion4.PAGE4_QUESTION_1
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_1)
        {

            return MemberTendency.SPICY_EGGPLANT;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_2 && answer2 == TendencyQuestion2.PAGE2_QUESTION_1
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_1 && answer4 == TendencyQuestion4.PAGE4_QUESTION_1
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_2)
        {

            return MemberTendency.SPICY_EGGPLANT;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_2 && answer2 == TendencyQuestion2.PAGE2_QUESTION_1
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_1 && answer4 == TendencyQuestion4.PAGE4_QUESTION_2
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_1)
        {

            return MemberTendency.SPICY_EGGPLANT;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_2 && answer2 == TendencyQuestion2.PAGE2_QUESTION_1
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_1 && answer4 == TendencyQuestion4.PAGE4_QUESTION_2
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_2)
        {

            return MemberTendency.SPICY_EGGPLANT;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_2 && answer2 == TendencyQuestion2.PAGE2_QUESTION_2
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_1 && answer4 == TendencyQuestion4.PAGE4_QUESTION_1
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_1)
        {

            return MemberTendency.BULGOGI_OF_PASSION;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_2 && answer2 == TendencyQuestion2.PAGE2_QUESTION_2
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_1 && answer4 == TendencyQuestion4.PAGE4_QUESTION_1
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_2)
        {

            return MemberTendency.BULGOGI_OF_PASSION;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_2 && answer2 == TendencyQuestion2.PAGE2_QUESTION_2
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_1 && answer4 == TendencyQuestion4.PAGE4_QUESTION_2
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_1)
        {

            return MemberTendency.BULGOGI_OF_PASSION;

        } else if (answer1 == TendencyQuestion1.PAGE1_QUESTION_2 && answer2 == TendencyQuestion2.PAGE2_QUESTION_2
                && answer3 == TendencyQuestion3.PAGE3_QUESTION_1 && answer4 == TendencyQuestion4.PAGE4_QUESTION_2
                && answer5 == TendencyQuestion5.PAGE5_QUESTION_2)
        {

            return MemberTendency.BULGOGI_OF_PASSION;

        }

        return null;
    }

}
