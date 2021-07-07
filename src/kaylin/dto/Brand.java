package kaylin.dto;

import java.io.Serializable;

public class Brand implements Serializable {
    private String id;
    private String name;
    private String soundBrand;
    private double price;

    public Brand() {
    }

    public Brand(String id, String name, String soundBrand, double price) {
        this.id = id.toUpperCase().trim();
        setName(name);
        setSoundBrand(soundBrand);
        setPrice(price);
    }

    public String getId() {
        return id;
    }

//    public void setId(String id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty.");
        this.name = name.trim();
    }

    public String getSoundBrand() {
        return soundBrand;
    }

    public void setSoundBrand(String soundBrand) {
        if (soundBrand.trim().isEmpty())
            throw new IllegalArgumentException("Sound brand cannot be empty.");
        this.soundBrand = soundBrand.trim();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0)
            throw new IllegalArgumentException("Price must be greater than 0");
        this.price = price;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + soundBrand + ": " + price;
    }

    public void details() {
        System.out.printf("|%-10s|%-30s|%-20s|%6.3f|\n", id, name, soundBrand, price);
    }
}
