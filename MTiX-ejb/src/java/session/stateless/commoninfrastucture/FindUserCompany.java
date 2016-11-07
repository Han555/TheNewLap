/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.stateless.commoninfrastucture;

import entity.SectionCategoryEntity;
import entity.UserEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author JingYing
 */
@Stateless
public class FindUserCompany implements FindUserCompanyLocal {

    @PersistenceContext
    EntityManager em;

    @Override
    public String findcompany(String username) {
        Query q = em.createQuery("SELECT a FROM UserEntity a WHERE a.username=:username");
        q.setParameter("username", username);
        UserEntity user = (UserEntity) q.getSingleResult();

        return user.getCompany().getId().toString();
    }
}
