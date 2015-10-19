package restaurant.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import restaurant.domain.Restaurant;

@Repository
public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant,Long> {
}
