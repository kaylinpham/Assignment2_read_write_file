package kaylin.dto;

import kaylin.utils.InputHandler;

import java.io.*;
import java.util.ArrayList;

public class BrandList extends ArrayList<Brand> {
    public BrandList() {
        super();
    }

    public int searchID(String code) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getId().equals(code.trim().toUpperCase()))
                return i;
        }
        return -1;
    }

    public void searchBrand() {
        String id = InputHandler.inputNonBlankStr("Enter ID to search: ");
        int position = searchID(id);

        if (position == -1)
            System.out.println("Not found.");
        else {
            this.get(position).details();
        }
    }

    private boolean isDuplicated(String code) {
        return searchID(code) != -1;
    }

    public boolean loadFromFile(String filename) {
        if (this.size() > 0)
            this.clear();

        try {
            File file = new File(filename);
            if (!file.exists()) {
                return false;
            }

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String details[] = line.split(", ");
                String tmp[] = details[2].split(": ");
                Brand brand = new Brand(details[0].trim(), details[1].trim(), tmp[0].trim(), Double.parseDouble(tmp[1]));
                this.add(brand);
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

            for (Brand brand : this) {
                bw.write(brand.toString());
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

    public Brand getUserChoice() {
        Menu menu = new Menu();
        return (Brand) menu.ref_getChoice(this);
    }

    public void addBrand() {
        String id;
        do {
            id = InputHandler.inputNonBlankStr("Enter new ID: ");
            if (searchID(id) != -1) {
                System.out.println("No duplicate code.");
            }
        } while (searchID(id) != -1);

        Brand brand = new Brand(id.trim(), "BRAND_NAME", "SOUND_BRAND", 1);
        brand.setName(InputHandler.inputNonBlankStr("Enter name: "));
        brand.setSoundBrand(InputHandler.inputNonBlankStr("Enter sound brand: "));
        brand.setPrice(InputHandler.getDoubleWithMin(0, "Enter price (>0): "));

        this.add(brand);
        System.out.println("Add successfully.");
    }

    public void updateBrand() {
        String id;
        id = InputHandler.inputNonBlankStr("Enter ID to update: ");
        int position = searchID(id);

        if (position == -1) {
            System.out.println("Not found.");
        } else {
            Brand brand = this.get(position);
            brand.details();
            brand.setName(InputHandler.inputNonBlankStr("Enter new name: "));
            brand.setSoundBrand(InputHandler.inputNonBlankStr("Enter new sound brand: "));
            brand.setPrice(InputHandler.getDoubleWithMin(0, "Enter new price (>0): "));
        }
    }

    public void listBrands() {
        System.out.println("BRANDS LIST------------------------------------------");
        for (int i = 0; i < this.size(); i++) {
            this.get(i).details();
        }
    }
}
