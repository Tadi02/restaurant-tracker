package restaurant.domain;

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

    private int priceCategory;

    private String description;

    private float gpsCoordX;

    private float gpsCoordY;

    @OneToMany(mappedBy = "ratedRestaurant", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
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

    public int getPriceCategory() {
        return priceCategory;
    }

    public void setPriceCategory(int priceCategory) {
        this.priceCategory = priceCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
