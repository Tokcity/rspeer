package org.tokcity.tasks;

import org.rspeer.runetek.adapter.scene.SceneObject;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class bank extends Task {

    private final int UNF_HARRALANDER = 97;
    private final int GRIMY_HARRALANDER = 205;
    private final int VIAL_WATER = 227;
    private final int BANK_BOOTH = 10583;
    private final int EXCHANGE_BOOTH = 10060;

    @Override
    public boolean validate() {
        return (Inventory.containsOnly(UNF_HARRALANDER));
    }

    @Override
    public int execute() {
        Log.info("Banking");
        SceneObject BANK = SceneObjects.getNearest(BANK_BOOTH, EXCHANGE_BOOTH);
        if (Bank.isClosed()) {
            Bank.open();{
                Time.sleepUntil(()->Bank.isOpen(),2000);
            }
            Bank.depositInventory();{
                Time.sleepUntil(()->Inventory.isEmpty(),2000);
            }
            Bank.withdraw(GRIMY_HARRALANDER, 14);{
                Time.sleepUntil(()->Inventory.contains(GRIMY_HARRALANDER), 2000);
            }
            Bank.withdraw(VIAL_WATER, 14);{
                Time.sleepUntil(()->Inventory.contains(VIAL_WATER), 2000);
            }
            Bank.close();
        }
        return 600;
    }
}
