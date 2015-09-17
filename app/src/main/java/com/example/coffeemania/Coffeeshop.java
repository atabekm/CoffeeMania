package com.example.coffeemania;

public class Coffeeshop implements Comparable<Coffeeshop> {
    private String name;
    private String lat;
    private String lng;
    private int distance;
    private String city;
    private String formattedAddress;
    private String category;
    private int checkinsCount;
    private int usersCount;
    private int tipCount;
    private String phone;
    private String formattedPhone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCheckinsCount() {
        return checkinsCount;
    }

    public void setCheckinsCount(int checkinsCount) {
        this.checkinsCount = checkinsCount;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public int getTipCount() {
        return tipCount;
    }

    public void setTipCount(int tipCount) {
        this.tipCount = tipCount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFormattedPhone() {
        return formattedPhone;
    }

    public void setFormattedPhone(String formattedPhone) {
        this.formattedPhone = formattedPhone;
    }

    @Override
    public int compareTo(Coffeeshop another) {
        if (this.getDistance() < another.getDistance()) {
            return -1;
        } else {
            return 1;
        }
    }
}
