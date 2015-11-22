package restaurant.service;

import org.springframework.data.jpa.domain.Specification;
import restaurant.domain.Restaurant;
import restaurant.dto.RestaurantSearchParams;
import restaurant.dto.ReviewSearchDirection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RestaurantSpecification {

    public static Specification<Restaurant> restaurantSearch(RestaurantSearchParams searchParams){
        return new Specification<Restaurant>() {

            @Override
            public Predicate toPredicate(Root<Restaurant> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                query.distinct(true);

                predicates.add(cb.equal(root.get("allowed"),1));

                if(searchParams.getName() != null ){
                    if(!searchParams.getName().isEmpty()) {
                        predicates.add(cb.like(root.get("name"), "%" + searchParams.getName() + "%"));
                    }
                }

                if(searchParams.getDescription() != null) {
                    if (!searchParams.getDescription().isEmpty()) {
                        predicates.add(cb.like(root.get("description"), "%" + searchParams.getDescription() + "%"));
                    }
                }

                if(searchParams.getPriceCategory() != null){
                    predicates.add(cb.equal(root.get("priceCategory"),searchParams.getPriceCategory()));
                }

                if(searchParams.getEnvironment() != null){
                    if(searchParams.getEnvironmentDirection() == ReviewSearchDirection.LESS){
                        predicates.add(cb.lessThanOrEqualTo(root.get("environmentScore"),searchParams.getEnvironment()));
                    }else{
                        predicates.add(cb.greaterThanOrEqualTo(root.get("environmentScore"),searchParams.getEnvironment()));
                    }
                }

                if(searchParams.getValueOfService() != null){
                    if(searchParams.getValueOfServiceDirection() == ReviewSearchDirection.LESS){
                        predicates.add(cb.lessThanOrEqualTo(root.get("valueOfServiceScore"),searchParams.getValueOfService()));
                    }else{
                        predicates.add(cb.greaterThanOrEqualTo(root.get("valueOfServiceScore"),searchParams.getValueOfService()));
                    }
                }
                
                if(searchParams.getValueOfMeal() != null){
                    if(searchParams.getValueOfMealDirection() == ReviewSearchDirection.LESS){
                        predicates.add(cb.lessThanOrEqualTo(root.get("valueOfMealScore"),searchParams.getValueOfMeal()));
                    }else{
                        predicates.add(cb.greaterThanOrEqualTo(root.get("valueOfMealScore"),searchParams.getValueOfMeal()));
                    }
                }

                if(searchParams.getSpeedOfService() != null){
                    if(searchParams.getSpeedOfServiceDirection() == ReviewSearchDirection.LESS){
                        predicates.add(cb.lessThanOrEqualTo(root.get("speedOfServiceScore"),searchParams.getSpeedOfService()));
                    }else{
                        predicates.add(cb.greaterThanOrEqualTo(root.get("speedOfServiceScore"),searchParams.getSpeedOfService()));
                    }
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }

        };
    }
}
