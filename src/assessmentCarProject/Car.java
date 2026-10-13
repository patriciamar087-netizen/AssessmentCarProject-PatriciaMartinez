package assessmentCarProject;

public class Car {
    // Private fields matching CSV columns
    private String carId;      // Changed to String (IDs like "C49706")
    private String brand;
    private String model;
    private int year;
    private String fuelType;
    private String color;
    private double mileageKmpl;

    // Constructor
    public Car(String carId, String brand, String model, int year, 
               String fuelType, String color, double mileageKmpl) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.color = color;
        this.mileageKmpl = mileageKmpl;
    }

    // Getter methods
    public String getCarId() { return carId; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public String getFuelType() { return fuelType; }
    public String getColor() { return color; }
    public double getMileageKmpl() { return mileageKmpl; }

    // toString method for display
    @Override
    public String toString() {
        return String.format("ID: %-7s | Brand: %-12s | Model: %-12s | Year: %d | Fuel: %-8s | Color: %-8s | Mileage: %.2f km/l",
                carId, brand, model, year, fuelType, color, mileageKmpl);
    }
}