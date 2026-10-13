package assessmentCarProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<Car> loadCars(String filename) {
        ArrayList<Car> cars = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    String[] d = line.split(",");
                    cars.add(new Car(d[0].trim(), d[1].trim(), d[2].trim(), 
                            Integer.parseInt(d[3].trim()), d[4].trim(), 
                            d[5].trim(), Double.parseDouble(d[6].trim())));
                } catch (Exception e) {}
            }
        } catch (Exception e) {}
        return cars;
    }

    public static void sortByMileage(ArrayList<Car> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int min = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).getMileageKmpl() < list.get(min).getMileageKmpl()) {
                    min = j;
                }
            }
            Car temp = list.get(i);
            list.set(i, list.get(min));
            list.set(min, temp);
        }
    }

    public static ArrayList<Car> search(ArrayList<Car> list, double target) {
        ArrayList<Car> results = new ArrayList<>();
        int low = 0, high = list.size() - 1;
        
        while (low <= high) {
            int mid = (low + high) / 2;
            double m = list.get(mid).getMileageKmpl();
            
            if (Math.abs(m - target) < 0.01) {
                int i = mid;
                while (i >= 0 && Math.abs(list.get(i).getMileageKmpl() - target) < 0.01) {
                    results.add(0, list.get(i--));
                }
                i = mid + 1;
                while (i < list.size() && Math.abs(list.get(i).getMileageKmpl() - target) < 0.01) {
                    results.add(list.get(i++));
                }
                break;
            } else if (m < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return results;
    }

    public static void stats(ArrayList<Car> list) {
        double total = 0;
        int petrol = 0, diesel = 0, electric = 0, hybrid = 0, cng = 0;
        
        for (Car c : list) {
            total += c.getMileageKmpl();
            String f = c.getFuelType().toLowerCase();
            if (f.equals("petrol")) petrol++;
            else if (f.equals("diesel")) diesel++;
            else if (f.equals("electric")) electric++;
            else if (f.equals("hybrid")) hybrid++;
            else if (f.equals("cng")) cng++;
        }
        
        System.out.printf("\nAverage Mileage: %.2f km/l\n", total / list.size());
        System.out.println("Petrol: " + petrol);
        System.out.println("Diesel: " + diesel);
        System.out.println("Electric: " + electric);
        System.out.println("Hybrid: " + hybrid);
        System.out.println("CNG: " + cng);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Car> working = new ArrayList<>(loadCars("Car_Data.csv").subList(0, 2000));
        boolean sorted = false;

        while (true) {
            System.out.println("\n1. Sort by Mileage\n2. Search by Mileage\n3. Statistics\n4. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                sortByMileage(working);
                sorted = true;
                System.out.println("\nSorted. First 10:");
                for (int i = 0; i < 10; i++) System.out.println(working.get(i));
            } else if (choice == 2) {
                if (!sorted) {
                    System.out.println("Sort first");
                } else {
                    System.out.print("Enter mileage: ");
                    ArrayList<Car> r = search(working, sc.nextDouble());
                    if (r.isEmpty()) {
                        System.out.println("Not found");
                    } else {
                        System.out.println("Found " + r.size() + ":");
                        for (Car c : r) System.out.println(c);
                    }
                }
            } else if (choice == 3) {
                stats(working);
            } else if (choice == 4) {
                System.out.println("Exit");
                break;
            }
        }
        sc.close();
    }
}