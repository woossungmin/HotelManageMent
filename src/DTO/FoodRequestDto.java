package DTO;

import java.io.Serializable;

public class FoodRequestDto implements Serializable {
	
    private String orderNum;
    private String foodName;
    private String roomNum;

    public String getOrderNum() {
        return orderNum;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }
}
