package com.pvthach.calculator.repository;

import com.pvthach.calculator.model.History;
import com.pvthach.calculator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by THACH-PC
 */

@Repository
public class HistoryCustomRepositoryImpl implements HistoryCustomRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<History> getHistoriesByUser(String username){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<History> criteria = builder.createQuery(History.class);
        Root<History> history = criteria.from(History.class);
        Join<History, User> joinUser = history.join("createdBy");
        criteria.where(builder.equal(joinUser.get("username"), username));
        criteria.orderBy(builder.desc(history.get("date")));
        List<History> histories = entityManager.createQuery(criteria).getResultList();

        return histories;
    }
}