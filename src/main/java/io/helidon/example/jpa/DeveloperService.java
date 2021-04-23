
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
import javax.persistence.criteria.Order;
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

    public CollectionResponse<Developer> searchDevelopers(String name, String team, Integer page, Integer pageSize, String sort)
    {
        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(Developer.class);
        var rootEntry = cq.from(Developer.class);
        var all = cq.select(rootEntry);
        addSearchFilters(name, team, cb, rootEntry, all);
        var countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(cq.from(Developer.class)));
        if(all.getRestriction()!=null) {
            countQuery.where(all.getRestriction());
        }
        var totalCount = entityManager.createQuery(countQuery).getSingleResult();
        var order = createOrderExpression(sort,cb,rootEntry);
        if(order!=null) {
            all.orderBy(order);
        }
        var allQuery = entityManager.createQuery(all).setFirstResult((page-1)*pageSize).setMaxResults(pageSize);
        var response = new CollectionResponse<Developer>();
        response.setHasNext((totalCount - ((page-1)*pageSize))>pageSize);
        response.setItems(allQuery.getResultList());
        return response;
    }

    private Order createOrderExpression(String sort, CriteriaBuilder cb, Root<Developer> root) {
        Order order = null;
        if(sort!=null) {
            String[] split = sort.split(":");
            if (split.length == 2)
            {
                if(split[1].equalsIgnoreCase("asc"))
                {
                    order = cb.asc(root.get(split[0]));
                }
                else
                {
                    order = cb.desc(root.get(split[0]));
                }
            }
            else
            {
                throw new ValidationException("Invalid sort parameter");
            }
        }
        return order;
    }

    private void addSearchFilters(String name, String team, CriteriaBuilder cb, Root<Developer> rootEntry, CriteriaQuery<Developer> all) {
        var namePredicate = !StringUtil.isNullOrEmpty(name) ? cb.equal(rootEntry.get("name"), name) : null;
        var teamPredicate = !StringUtil.isNullOrEmpty(team) ? cb.equal(rootEntry.get("team"), team) : null;
        if(namePredicate!=null && teamPredicate != null)
        {
            var andPredicate = cb.and(namePredicate,teamPredicate);
            all.where(andPredicate);
        }
        else
        {
            if(teamPredicate!=null)
            {
                all.where(teamPredicate);
            }
            if(namePredicate!=null)
            {
                all.where(namePredicate);
            }
        }
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
