package kaylin.dto;

import kaylin.utils.InputHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class CarList extends ArrayList<Car> {
    private BrandList brandList;

    public CarList(BrandList brandList) {
        super();
        this.brandList = brandList;
    }

    public boolean loadFromFile(String filename) {
        if (this.size() > 0)
            this.clear();

        try {
            File file = new File(filename);
            if (!file.exists())
                return false;

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String details[] = line.split(", ");
                Car car = new Car(details[0], brandList.get(brandList.searchID(details[1])), details[2], details[3], details[4]);
                this.add(car);
            }

            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    public boolean saveToFile(String filename) {
        if (this.size() == 0) {
            System.out.println("Save FAILED. Empty list.");
            return false;
        }

        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Car car : this) {
                bw.write(car.toString());
                bw.newLine();
            }

            bw.close();
            fw.close();
            System.out.println("Save successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public int searchID(String code) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId().equals(code.trim().toUpperCase()))
                return i;
        }
        return -1;
    }

    private IDuplicate idDuplicate = (code) -> {
        return searchID(code) != -1;
    };

    public int searchFrame(String frameID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getFrameID().equals(frameID.trim().toUpperCase()))
                return i;
        }
        return -1;
    }

    private IDuplicate frameDuplicate = (code) -> {
        return searchFrame(code) != -1;
    };

    public int searchEngine(String engineID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getEngineID().equals(engineID.trim().toUpperCase()))
                return i;
        }
        return -1;
    }

    private IDuplicate engineDuplicate = (code) -> {
        return searchEngine(code) != -1;
    };

    public void addCar() {
        Menu<Brand> menu = new Menu<>();

        String id = InputHandler.noDuplicate(idDuplicate, "Enter ID: ", "No duplicate ID.");
        Brand brand = (Brand) menu.ref_getChoice(brandList);
        String color = InputHandler.inputNonBlankStr("Enter color: ");
        String frameID = InputHandler.noDuplicateAndPattern(frameDuplicate, "Enter Frame ID: ", "No duplicate Frame ID.", "^F[0-9]{5}$");
        String engineID = InputHandler.noDuplicateAndPattern(engineDuplicate, "Enter Engine ID: ", "No duplicate Engine ID.", "^E[0-9]{5}$");

        Car car = new Car(id, brand, color, frameID, engineID);
        this.add(car);
        System.out.println("Add successfully.");
    }


    public void printBasedBrandName() {
        String keyword = InputHandler.inputNonBlankStr("Enter brand name to find car: ");
        int count = 0;

        for (int i = 0; i < this.size(); i++) {
            Car car = this.get(i);
            if (car.getBrand().getName().contains(keyword)) {
                System.out.println(car.screenString());
                count++;
            }
        }

        if (count == 0)
            System.out.println("No car is detected.");
    }

    public boolean removeCar() {
        String id = InputHandler.inputNonBlankStr("Enter ID to remove car: ");
        int position = searchID(id);

        if (position < 0) {
            System.out.println("Not found.");
            return false;
        } else {
            this.remove(position);
            System.out.println("Remove successfully.");
        }

        return true;
    }

    public boolean updateCar() {
        String id = InputHandler.inputNonBlankStr("Enter ID to update car: ");
        int position = searchID(id);

        if (position < 0) {
            System.out.println("Not found.");
            return false;
        } else {
            Menu<Brand> menu = new Menu<>();
            Car car = this.get(position);
            car.details();

            car.setBrand((Brand) menu.ref_getChoice(brandList));
            car.setColor(InputHandler.inputNonBlankStr("Enter new color: "));
            car.setFrameID(InputHandler.noDuplicateAndPattern(frameDuplicate, "Enter new Frame ID: ", "No duplicate Frame ID.", "^F[0-9]{5}$"));
            car.setEngineID(InputHandler.noDuplicateAndPattern(engineDuplicate, "Enter new Engine ID: ", "No duplicate Engine ID.", "^E[0-9]{5}$"));
            System.out.println("Update successfully.");
            car.details();
        }

        return true;
    }

    public void listCars() {
        System.out.println("CARS LIST------------------------------------------");
        Collections.sort(this);
        for (int i = 0; i < this.size(); i++) {
            this.get(i).details();
        }
    }
}
