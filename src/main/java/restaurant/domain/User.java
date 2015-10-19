package restaurant.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String email;

    private String password;

    private int permissionLevel;

    @OneToMany(mappedBy = "ratedUser", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Review> ratedRestaurants;

    public void addRestaurantReview(Review review){
        if(this.ratedRestaurants == null){
            this.ratedRestaurants = new ArrayList<>();
        }
        this.ratedRestaurants.add(review);
        review.setRatedUser(this);
    }

    public List<Review> getRatedRestaurants() {
        return ratedRestaurants;
    }

    public void setRatedRestaurants(List<Review> ratedRestaurants) {
        this.ratedRestaurants = ratedRestaurants;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }
}

