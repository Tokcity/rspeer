package org.tokcity.tasks;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class clean extends Task {

    private final int GRIMY_HARRALANDER = 205;
    private final int VIAL_WATER = 227;

    private static final String WATER = "Vial of water";
    private static final String HERB = "Harralander";

    private int[] cleanOrder() {
        int[] clean1 = new int[]{0, 4, 8, 12, 16, 20, 24, 25, 21, 17, 13, 9, 5, 1, 2, 6, 10, 14, 18, 22, 26, 27, 23, 19, 15, 11, 7, 3};//pipe her up down
        int[] clean2 = new int[]{0, 1, 4, 5, 8, 9, 12, 13, 16, 17, 20, 21, 24, 25, 26, 27, 22, 23, 18, 19, 14, 15, 10, 11, 6, 7, 2, 3};//pencil ziggy
        int[] clean3 = new int[]{0, 1, 2, 3, 7, 6, 5, 4, 8, 9, 10, 11, 15, 14, 13, 12, 16, 17, 18, 19, 23, 22, 21, 20, 24, 25, 26, 27};//get long ziggy

        switch (Random.nextInt(1, 3)) { //also ask ur doctor about sporks
            case 1:
                Log.info("Clean 1");
                return clean1;
            case 2:
                Log.info("Clean 2");
                return clean2;
            case 3:
                Log.info("Clean 3");
                return clean3;
        }
        return cleanOrder();
    }
    @Override
    public boolean validate() {
        return Inventory.contains(GRIMY_HARRALANDER);
    }
    @Override
    public int execute() {
        Log.info("Cleaning");
        int[] indexes = cleanOrder();
        for (int index : indexes) {
            Item herb = Inventory.getItemAt(index);
            if (herb != null && herb.getName().equalsIgnoreCase("Grimy harralander")) {
                herb.interact("Clean");
                Time.sleep(400,600);
            }
        }
        return 600;
    }
}