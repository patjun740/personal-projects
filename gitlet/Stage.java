package gitlet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;

public class Stage implements Serializable {
    private HashMap<String, String> adding;
    private LinkedList<String> removing;

    public Stage() {
        adding = new HashMap<>();
        removing = new LinkedList<>();
    }

    public Stage(HashMap<String, String> newAdding) {
        adding = newAdding;
    }
    public Stage(LinkedList<String> newRemoving) {
        removing = newRemoving;
    }

    public Stage(HashMap<String, String> copy, LinkedList<String> theRemoving) {
        adding = copy;
        removing = theRemoving;
    }

    public HashMap<String, String> getAdding() {
        return adding;
    }

    public Stage setAdding(String fileName, String id) {
        this.adding.put(fileName, id);
        return this;
    }

    public Stage setRemoving(String fileName) {
        this.removing.add(fileName);
        return this;
    }

    public LinkedList<String> getRemoving() {
        return removing;
    }
}
