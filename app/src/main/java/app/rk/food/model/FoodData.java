package app.rk.food.model;

import java.util.Date;

/**
 *
 */
public class FoodData {
    String  foodId,
            foodName,
            foodType,
            foodImgURL,
            foodDescription,
            itemAddedCartTime;
    Double   foodPrice;
    Integer foodQuantity;
    public Integer getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(Integer foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodImgURL() {
        return foodImgURL;
    }

    public void setFoodImgURL(String foodImgURL) {
        this.foodImgURL = foodImgURL;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public Double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(Double foodPrice) {
        this.foodPrice = foodPrice;
    }
    public String getCartAddedTime() {
        return itemAddedCartTime;
    }

    public void setCartAddedTime(String itemAddedCartTime) {
        this.itemAddedCartTime = itemAddedCartTime;
    }
}
