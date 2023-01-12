package com.example.houzit;

public class House {
    private String ImageUrl;
    private String Area;
    private String HouseType;
    private String RentAmt;
    private String DepositAmt;
    private String College;

    public House(String imageUrl, String area, String houseType, String rentAmt, String depositAmt, String college) {
        ImageUrl = imageUrl;
        Area = area;
        HouseType = houseType;
        RentAmt = rentAmt;
        DepositAmt = depositAmt;
        College = college;
    }

    public House() {
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getHouseType() {
        return HouseType;
    }

    public void setHouseType(String houseType) {
        HouseType = houseType;
    }

    public String getRentAmt() {
        return RentAmt;
    }

    public void setRentAmt(String rentAmt) {
        RentAmt = rentAmt;
    }

    public String getDepositAmt() {
        return DepositAmt;
    }

    public void setDepositAmt(String depositAmt) {
        DepositAmt = depositAmt;
    }

    public String getCollege() {
        return College;
    }

    public void setCollege(String college) {
        College = college;
    }
}
