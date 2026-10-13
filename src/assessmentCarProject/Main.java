package assessmentCarProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    // Method to load cars from CSV file
    public static ArrayList<Car> loadCars(String filename) {
        ArrayList<Car> cars = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine(); // Skip header row

            while ((line = br.readLine()) != null) {
                try {
                    String[] data = line.split(",");
                    if (data.length < 7) continue;

                    String carId = data[0].trim();
                    String brand = data[1].trim();
                    String model = data[2].trim();
                    int year = Integer.parseInt(data[3].trim());
                    String fuelType = data[4].trim();
                    String color = data[5].trim();
                    double mileageKmpl = Double.parseDouble(data[6].trim());

                    Car car = new Car(carId, brand, model, year, fuelType, color, mileageKmpl);
                    cars.add(car);

                } catch (Exception e) {
                    continue;
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return cars;
    }

    public static void main(String[] args) {
        // Load all cars from CSV
        ArrayList<Car> cars = loadCars("Car_Data.csv");
        System.out.println("Total cars loaded: " + cars.size());

        // Create working list with first 2,000 cars
        ArrayList<Car> working = new ArrayList<>(cars.subList(0, 2000));
        System.out.println("Working list size: " + working.size());
    }
}