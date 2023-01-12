package com.example.houzit;

public class PG {
    private String ImageUrl;
    private String Area;
    private String PGName;
    private String RentAmt;
    private String DepositAmt;
    private String College;

    public PG(String imageUrl, String area, String pgName, String rentAmt, String depositAmt, String college) {
        ImageUrl = imageUrl;
        Area = area;
        this.PGName = pgName;
        RentAmt = rentAmt;
        DepositAmt = depositAmt;
        College = college;
    }

    public PG() {
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

    public String getPGName() {
        return PGName;
    }

    public void setPGName(String pgName) {
        this.PGName = pgName;
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
