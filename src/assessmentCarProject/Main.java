package assessmentCarProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static ArrayList<Car> loadCars(String filename) {
        ArrayList<Car> cars = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();

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

    public static void selectionSortByMileage(ArrayList<Car> list) {
        int n = list.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (list.get(j).getMileageKmpl() < list.get(minIndex).getMileageKmpl()) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Car temp = list.get(i);
                list.set(i, list.get(minIndex));
                list.set(minIndex, temp);
            }
        }
    }

    public static ArrayList<Car> binarySearchByMileage(ArrayList<Car> list, double targetMileage) {
        ArrayList<Car> results = new ArrayList<>();

        int low = 0;
        int high = list.size() - 1;
        int foundIndex = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            double midMileage = list.get(mid).getMileageKmpl();

            if (Math.abs(midMileage - targetMileage) < 0.01) {
                foundIndex = mid;
                break;
            } else if (midMileage < targetMileage) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (foundIndex != -1) {
            int i = foundIndex;
            while (i >= 0 && Math.abs(list.get(i).getMileageKmpl() - targetMileage) < 0.01) {
                results.add(0, list.get(i));
                i--;
            }

            i = foundIndex + 1;
            while (i < list.size() && Math.abs(list.get(i).getMileageKmpl() - targetMileage) < 0.01) {
                results.add(list.get(i));
                i++;
            }
        }

        return results;
    }

    public static void main(String[] args) {
        ArrayList<Car> cars = loadCars("Car_Data.csv");
        ArrayList<Car> working = new ArrayList<>(cars.subList(0, 2000));

        selectionSortByMileage(working);
        System.out.println("Sorted by mileage\n");

        System.out.println("First 10 cars:");
        for (int i = 0; i < 10; i++) {
            System.out.println(working.get(i));
        }

        System.out.println("\nSearching for mileage: 18.97");
        ArrayList<Car> results = binarySearchByMileage(working, 18.97);

        if (results.isEmpty()) {
            System.out.println("No cars found");
        } else {
            System.out.println("Found " + results.size() + " cars:");
            for (Car car : results) {
                System.out.println(car);
            }
        }
    }
}