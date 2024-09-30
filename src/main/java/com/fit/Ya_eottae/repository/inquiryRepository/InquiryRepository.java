package com.fit.Ya_eottae.repository.inquiryRepository;

import com.fit.Ya_eottae.domain.inquiry.Inquiry;
import com.fit.Ya_eottae.domain.member.Member;

import java.util.List;

public interface InquiryRepository {

    Inquiry save(Inquiry inquiry);

    Inquiry setAnswer(long inquiryId, String answer);

    void deleteInquiry(long id);

    Inquiry findById(long id);

    List<Inquiry> findByMemberId(String memberId);

    List<Inquiry> findAll();
}