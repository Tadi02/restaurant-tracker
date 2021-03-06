package restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String address;

    private String phoneNumber;

    private String url;

    @JsonIgnore
    @Enumerated(EnumType.ORDINAL)
    private PriceCategory priceCategory;

    @Lob
    private String description;

    private float gpsCoordX;

    private float gpsCoordY;

    private double valueOfMealScore;

    private double valueOfServiceScore;

    private double speedOfServiceScore;

    private double environmentScore;


    private boolean allowed;

    private transient String priceCategoryString;

    @OneToMany(mappedBy = "ratedRestaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Review> ratings;

    public void addReview(Review review){
        if(this.ratings == null){
            this.ratings = new ArrayList<>();
        }
        this.ratings.add(review);
        review.setRatedRestaurant(this);
    }

    public List<Review> getRatings() {
        return ratings;
    }

    public void setRatings(List<Review> ratings) {
        this.ratings = ratings;
    }

    public float getGpsCoordX() {
        return gpsCoordX;
    }

    public void setGpsCoordX(float gpsCoordX) {
        this.gpsCoordX = gpsCoordX;
    }

    public float getGpsCoordY() {
        return gpsCoordY;
    }

    public void setGpsCoordY(float gpsCoordY) {
        this.gpsCoordY = gpsCoordY;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PriceCategory getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(PriceCategory priceCategory) {
        this.priceCategory = priceCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValueOfMealScore() {
        return valueOfMealScore;
    }

    public void setValueOfMealScore(double valueOfMealScore) {
        this.valueOfMealScore = valueOfMealScore;
    }

    public double getValueOfServiceScore() {
        return valueOfServiceScore;
    }

    public void setValueOfServiceScore(double valueOfServiceScore) {
        this.valueOfServiceScore = valueOfServiceScore;
    }

    public double getSpeedOfServiceScore() {
        return speedOfServiceScore;
    }

    public void setSpeedOfServiceScore(double speedOfServiceScore) {
        this.speedOfServiceScore = speedOfServiceScore;
    }

    public double getEnvironmentScore() {
        return environmentScore;
    }

    public void setEnvironmentScore(double environmentScore) {
        this.environmentScore = environmentScore;
    }

    public String getPriceCategoryString() {
        return priceCategoryString;
    }

    public void setPriceCategoryString(String priceCategoryString) {
        this.priceCategoryString = priceCategoryString;
    }

    public boolean getAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

}
