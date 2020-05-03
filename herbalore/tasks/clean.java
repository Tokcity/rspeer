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

    @Override
    public boolean validate() {
        return Inventory.contains(GRIMY_HARRALANDER);
    }

    @Override
    public int execute() {
        Log.info("Cleaning");
        for (Item herb : Inventory.getItems(item -> item.getName().equals("Grimy harralander"))) {
            herb.interact("Clean");
            Time.sleep(Random.nextInt(350,700));

    }
        return 600;
    }
}