
package io.helidon.example.jpa;

import io.netty.util.internal.StringUtil;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@ApplicationScoped
public class DeveloperService {
    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    @PersistenceContext 
    private EntityManager entityManager;

    @Transactional
    public Developer createDeveloper(Developer developer) {
        if(StringUtil.isNullOrEmpty(developer.getId()))
        {
            developer.setId(UUID.randomUUID().toString());
        }
        this.entityManager.persist(developer);
        return developer;
    }
    
    public List<Developer> getAllDevelopers() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Developer> cq = cb.createQuery(Developer.class);
        Root<Developer> rootEntry = cq.from(Developer.class);
        CriteriaQuery<Developer> all = cq.select(rootEntry);
        TypedQuery<Developer> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public Developer getDeveloper(String devId) {
        return this.entityManager.find(Developer.class, devId);
    }

    public Developer deleteDeveloper(String devId) {
        Developer developer = getDeveloper(devId);
        this.entityManager.remove(developer);
        return developer;
    }

}
