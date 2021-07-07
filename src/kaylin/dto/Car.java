package kaylin.dto;

public class Car implements Comparable<Car> {
    private String id;
    private Brand brand;
    private String color;
    private String frameID;
    private String engineID;

    public Car() {
    }

    public Car(String id, Brand brand, String color, String frameID, String engineID) {
        this.id = id.toUpperCase().trim();
        this.brand = brand;
        setColor(color);
        setFrameID(frameID);
        setEngineID(engineID);
    }

    public String getId() {
        return id;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color.trim().isEmpty())
            throw new IllegalArgumentException("Color cannot be empty.");
        this.color = color.trim().toLowerCase();
    }

    public String getFrameID() {
        return frameID;
    }

    public void setFrameID(String frameID) {
        String id = frameID.trim().toUpperCase();
        if (!id.matches("^F[0-9]{5}$"))
            throw new IllegalArgumentException("Invalid Frame ID.");
        this.frameID = id;
    }

    public String getEngineID() {
        return engineID;
    }

    public void setEngineID(String engineID) {
        String id = engineID.trim().toUpperCase();
        if (!id.matches("^E[0-9]{5}$"))
            throw new IllegalArgumentException("Invalid Engine ID.");
        this.engineID = id;
    }

    public void details() {
        System.out.printf("|%-10s|%-20s|%-15s|%6s|%6s|\n", id, brand.getId(), color, frameID, engineID);
    }

    @Override
    public String toString() {
        return id + ", " + brand.getId() + ", " + color + ", " + frameID + ", " + engineID;
    }


    @Override
    public int compareTo(Car o) {
        int d = brand.getId().compareTo(o.brand.getId());
        if (d != 0)
            return d;
        return id.compareTo(o.id);
    }

    public String screenString() {
        return brand + "\n" + id + ", " + color + ", " + frameID + ", " + engineID;
    }
}
