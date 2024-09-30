package com.fit.Ya_eottae.repository.inquiryRepository;

import com.fit.Ya_eottae.domain.inquiry.Inquiry;
import com.fit.Ya_eottae.domain.member.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Primary
//@Repository
@Transactional
public class JpaInquiryRepository implements InquiryRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Inquiry save(Inquiry inquiry) {
        em.persist(inquiry);
        return inquiry;
    }

    @Override
    public Inquiry setAnswer(long inquiryId, String answer) {
        Inquiry findInquiry = em.find(Inquiry.class, inquiryId);
        findInquiry.setAnswer(answer);
        return findInquiry;
    }

    @Override
    public void deleteInquiry(long id) {
        Inquiry findInquiry = em.find(Inquiry.class, id);
        em.remove(findInquiry);
    }

    @Override
    public Inquiry findById(long id) {
        Inquiry findInquiry = em.find(Inquiry.class, id);
        return findInquiry;
    }

    @Override
    public List<Inquiry> findByMemberId(String memberId) {
        String jpql = "select i from Inquiry i Where i.member.memberId = :memberId";
        TypedQuery<Inquiry> query = em.createQuery(jpql, Inquiry.class)
                .setParameter("memberId", memberId);

        List<Inquiry> inquiryList = query.getResultList();
        return inquiryList;
    }

    @Override
    public List<Inquiry> findAll() {
        String jpql = "select i from Inquiry i";
        TypedQuery<Inquiry> query = em.createQuery(jpql, Inquiry.class);
        List<Inquiry> inquiryList = query.getResultList();
        return inquiryList;
    }
}
