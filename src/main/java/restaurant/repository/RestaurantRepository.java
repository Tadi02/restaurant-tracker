package restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import restaurant.domain.Restaurant;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Long>, JpaSpecificationExecutor {
    List<Restaurant> findByAllowed(boolean allowed);
}

