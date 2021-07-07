package kaylin.dto;

import kaylin.utils.InputHandler;

import java.util.ArrayList;

public class Menu<E> {
    public int int_getChoice(ArrayList<E> options) {
        int res;
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + " - " + options.get(i));
        }

        res = InputHandler.getIntegerInRange("Please choose an option 1..." + options.size() + ": ", 1, options.size());
        return res;
    }

    public E ref_getChoice(ArrayList<E> options) {
        return options.get(int_getChoice(options) - 1);
    }
}
