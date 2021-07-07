package kaylin.main;

import kaylin.dto.BrandList;
import kaylin.dto.CarList;
import kaylin.dto.Menu;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CarManager {
    public static void main(String[] args) {
        ArrayList<String> ops = createMenu();
        BrandList brandList = new BrandList();
        brandList.loadFromFile("/Users/phamhagiang/Desktop/Giang/Summer2021/PRO192/Assignment2/src/kaylin/data/brands.txt");
        CarList carList = new CarList(brandList);
        carList.loadFromFile("/Users/phamhagiang/Desktop/Giang/Summer2021/PRO192/Assignment2/src/kaylin/data/cars.txt");
        int choice;
        boolean flag = true;
        Menu<String> menu = new Menu<>();

        do {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            choice = menu.int_getChoice(ops);
            switch (choice) {
                case 1:
                    brandList.listBrands();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                case 3:
                    brandList.searchBrand();
                    break;
                case 4:
                    brandList.updateBrand();
                    break;
                case 5:
                    brandList.saveToFile("/Users/phamhagiang/Desktop/Giang/Summer2021/PRO192/Assignment2/src/kaylin/data/brands.txt");
                    break;
                case 6:
                    carList.listCars();
                    break;
                case 7:
                    carList.printBasedBrandName();
                    break;
                case 8:
                    carList.addCar();
                    break;
                case 9:
                    carList.removeCar();
                    break;
                case 10:
                    carList.updateCar();
                    break;
                case 11:
                    carList.saveToFile("/Users/phamhagiang/Desktop/Giang/Summer2021/PRO192/Assignment2/src/kaylin/data/cars.txt");
                    break;
                default:
                    System.out.println("Goodbye");
                    flag = false;
            }
        } while (flag);
    }

    public static ArrayList<String> createMenu() {
        ArrayList<String> res = new ArrayList<>();

        res.add("List all brands.");
        res.add("Add a new brand.");
        res.add("Search a brand based on its ID.");
        res.add("Update a brand.");
        res.add("Save brands to the file, named brands.txt");
        res.add("List all cars in ascending order of brand names.");
        res.add("List cars based on a part of an input brand name.");
        res.add("Add a car.");
        res.add("Remove a car based on its ID.");
        res.add("Update a car based on its ID.");
        res.add("Save cars to file, named cars.txt");
        res.add("Exit.");

        return res;
    }
}
