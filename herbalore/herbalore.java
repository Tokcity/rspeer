package org.tokcity;

import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;

import org.rspeer.ui.Log;
import org.tokcity.tasks.bank;
import org.tokcity.tasks.clean;
import org.tokcity.tasks.mix;

@ScriptMeta(name = "Herbalore", developer = "Tokcity", desc = "Cleans and makes (unf)", version = 0.02)
public class herbalore extends TaskScript {

    private static final Task[] TASKS = { new bank(), new clean(), new mix() };

    @Override
    public void onStart() {
        Log.info("Script is starting");
        submit (TASKS);
    }

    @Override
    public void onStop() {
    }
}