package restaurant.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restaurant.domain.Review;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
}
