package restaurant.domain;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User ratedUser;

    @ManyToOne
    private Restaurant ratedRestaurant;

    private int valueOfMeal;

    private int valueOfService;

    private int speedOfService;

    private int environment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getRatedUser() {
        return ratedUser;
    }

    public void setRatedUser(User rateUser) {
        this.ratedUser = rateUser;
    }

    public Restaurant getRatedRestaurant() {
        return ratedRestaurant;
    }

    public void setRatedRestaurant(Restaurant retedRest) {
        this.ratedRestaurant = retedRest;
    }

    public int getValueOfMeal() {
        return valueOfMeal;
    }

    public void setValueOfMeal(int valueOfMeal) {
        this.valueOfMeal = valueOfMeal;
    }

    public int getValueOfService() {
        return valueOfService;
    }

    public void setValueOfService(int valueOfService) {
        this.valueOfService = valueOfService;
    }

    public int getSpeedOfService() {
        return speedOfService;
    }

    public void setSpeedOfService(int speedOfService) {
        this.speedOfService = speedOfService;
    }

    public int getEnvironment() {
        return environment;
    }

    public void setEnvironment(int environment) {
        this.environment = environment;
    }
}
