package restaurant.domain;

import javax.persistence.*;

/**
 * Created by Máté on 2015.10.18..
 */
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User ratedUser;

    @ManyToOne
    private Restaurant ratedRest;

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

    public Restaurant getRatedRest() {
        return ratedRest;
    }

    public void setRatedRest(Restaurant retedRest) {
        this.ratedRest = retedRest;
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
