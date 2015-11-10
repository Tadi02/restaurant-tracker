package restaurant.dto;

import restaurant.domain.PriceCategory;

public class RestaurantSearchParams {

    private String name;

    private PriceCategory priceCategory;

    private String description;

    private Double valueOfMeal;
    private ReviewSearchDirection valueOfMealDirection;

    private Double valueOfService;
    private ReviewSearchDirection valueOfServiceDirection;

    private Double speedOfService;
    private ReviewSearchDirection speedOfServiceDirection;

    private Double environment;
    private ReviewSearchDirection environmentDirection;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Double getValueOfMeal() {
        return valueOfMeal;
    }

    public void setValueOfMeal(Double valueOfMeal) {
        this.valueOfMeal = valueOfMeal;
    }

    public ReviewSearchDirection getValueOfMealDirection() {
        return valueOfMealDirection;
    }

    public void setValueOfMealDirection(ReviewSearchDirection valueOfMealDirection) {
        this.valueOfMealDirection = valueOfMealDirection;
    }

    public Double getValueOfService() {
        return valueOfService;
    }

    public void setValueOfService(Double valueOfService) {
        this.valueOfService = valueOfService;
    }

    public ReviewSearchDirection getValueOfServiceDirection() {
        return valueOfServiceDirection;
    }

    public void setValueOfServiceDirection(ReviewSearchDirection valueOfServiceDirection) {
        this.valueOfServiceDirection = valueOfServiceDirection;
    }

    public Double getSpeedOfService() {
        return speedOfService;
    }

    public void setSpeedOfService(Double speedOfService) {
        this.speedOfService = speedOfService;
    }

    public ReviewSearchDirection getSpeedOfServiceDirection() {
        return speedOfServiceDirection;
    }

    public void setSpeedOfServiceDirection(ReviewSearchDirection speedOfServiceDirection) {
        this.speedOfServiceDirection = speedOfServiceDirection;
    }

    public Double getEnvironment() {
        return environment;
    }

    public void setEnvironment(Double environment) {
        this.environment = environment;
    }

    public ReviewSearchDirection getEnvironmentDirection() {
        return environmentDirection;
    }

    public void setEnvironmentDirection(ReviewSearchDirection environmentDirection) {
        this.environmentDirection = environmentDirection;
    }
}
