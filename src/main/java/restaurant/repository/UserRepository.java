package restaurant.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import restaurant.domain.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {

    User findByEmail(String email);

}
