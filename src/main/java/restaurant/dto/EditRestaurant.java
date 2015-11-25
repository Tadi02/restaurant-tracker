package restaurant.dto;

import restaurant.domain.PriceCategory;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

public class EditRestaurant {

    private long id;
    private boolean allowed;

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Size(min = 1, max = 50)
    private String name;

    @Size(min = 1, max = 100)
    private String address;

    @Size(min = 1, max = 50)
    private String phoneNumber;

    @Size(min=1)
    private String url;

    @Enumerated(EnumType.ORDINAL)
    private PriceCategory priceCategory;

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

    public String getGpsCoordX() {
        return gpsCoordX;
    }

    public void setGpsCoordX(String gpsCoordX) {
        this.gpsCoordX = gpsCoordX;
    }

    public String getGpsCoordY() {
        return gpsCoordY;
    }

    public void setGpsCoordY(String gpsCoordY) {
        this.gpsCoordY = gpsCoordY;
    }

    private String description;

    @Size(min = 1)
    private String gpsCoordX = "0";

    @Size(min = 1)
    private String gpsCoordY = "0";

}

