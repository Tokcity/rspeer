package org.tokcity.tasks;

import org.rspeer.runetek.adapter.component.InterfaceComponent;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.Interfaces;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class mix extends Task {

    private static final int GRIMY_HARRALANDER = 205;
    private static final int CLEAN_HARRALANDER = 255;
    private static final int UNF_HARRALANDER = 97;
    private static final int VIAL_WATER = 227;

    private static final String WATER = "Vial of water";
    private static final String HERB = "Harralander";

    @Override
    public boolean validate() {
        return Inventory.contains(HERB, WATER);
    }

    @Override
    public int execute() {
        Log.info("Mixing");

        if (Players.getLocal().getAnimation() != -1){
            Time.sleepUntil(()->Players.getLocal().getAnimation() == -1 || Dialog.isOpen(), 3000);
            Time.sleepUntil(()->Players.getLocal().getAnimation() != -1 || Dialog.isOpen(), Random.nextInt(2000,4000));
        }

        if (Dialog.isOpen()){
            if (Dialog.processContinue())
                Time.sleepUntil(()->!Dialog.isOpen(), Random.nextInt(800, 1000));
        }
        makePotions();
        return 600;
    }

    private static boolean isCreationDialogOpen(){
        return Interfaces.getComponent(270, 14) !=null;
    }

    private static void makePotions(){
        if (Bank.isOpen()) {
            if (Bank.isClosed())
                Time.sleepUntil(Bank::isClosed, 2500);
            return;
        }
        if (isCreationDialogOpen()){
            clickMakeALL();
            return;
        }
        if (Inventory.getCount(WATER) > 0 && Inventory.getCount(HERB) > 0) {
            if (Inventory.use(x->x.getName().equalsIgnoreCase(HERB), Inventory.getFirst(WATER)))
                Time.sleepUntil(mix::isCreationDialogOpen, 2500);
        }
    }

    private static void clickMakeALL() {
        if (!isCreationDialogOpen()){
            return;
        }
        if (!isMakeAllSelected()){
            selectMakeAll();
            return;
        }
        InterfaceComponent Make = Interfaces.getComponent(270,14);
        if (Make == null){
            Log.info("Could not find make button");
            return;
        }
        if (Make.interact("Make"))
            Time.sleepUntil(()->Inventory.containsOnly(UNF_HARRALANDER), 20000);
    }

    private static void selectMakeAll(){
        InterfaceComponent makeAllBtn = Interfaces.getComponent(270, 12);
        if (makeAllBtn == null)
            return;
        if (makeAllBtn.interact("All"))
            Time.sleepUntil(mix::isMakeAllSelected, 2000);
    }

    private static boolean isMakeAllSelected(){
        InterfaceComponent makeAllBtn = Interfaces.getComponent(270,12);
        if (makeAllBtn == null)
            return false;
        return !makeAllBtn.containsAction("All");
    }

}