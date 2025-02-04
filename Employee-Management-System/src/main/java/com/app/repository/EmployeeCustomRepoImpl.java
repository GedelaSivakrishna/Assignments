package com.app.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeCustomRepoImpl implements EmployeeCustomRepo {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     *  Increments the employees salary in bulk
      * @param percent the percent value by which the salary need to be updated
     * @return the number of employee salary records updated
     */
    @Override
    @Transactional
    public int incrementSalaryBy(float percent) {
        String query = "update Employee e set e.salary = (e.salary * (100 + :percent))/100";
        int rows = entityManager.createQuery(query)
                .setParameter("percent", percent)
                .executeUpdate();
        System.out.println(rows + " Employees salary updated ");
        return rows;
    }
}
