package gitlet;



import java.util.Date;
import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import static gitlet.Utils.serialize;

import java.text.*;

/** Represents a gitlet commit object.
 *  does at a high level.
 *
 *  @author Patrick Jun
 *
 */
public class Commit implements Serializable {
    /**


     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
//    public String parent;
    private String time;
    private String ID;
    private String message;
    private HashMap<String, String> blobs;
    private String parentFrom;
    private String parentInto;


    public Commit() {
        parentFrom = null;
        time = null;
        ID = null;
        message = null;
        blobs = null;
        parentInto = null;
    }

    public Commit(String theMessage, HashMap<String, String> theContents, String theParent) {
        Format formatter;
        Date date;
        if (theParent == null) {
            date = new Date(0);
        } else {
            date = new Date();
        }
        formatter = new SimpleDateFormat("E MMM d HH:mm:ss yyyy Z");
        this.time = formatter.format(date);
        this.message = theMessage;
        this.blobs = theContents;
        this.parentFrom = theParent;
        this.parentInto = null;
        this.ID = Utils.sha1(message, serialize(this.time), Double.toString(Math.random()));
    }

    public void setParentFrom(String parent) {
        this.parentFrom = parent;
    }

    public void setParentInto(String parentInto) {
        this.parentInto = parentInto;
    }

    public String getId() {
        return ID;
    }
    public String getMessage() {
        return message;
    }
    public String getTime() {
        return time;
    }
    public HashMap<String, String> getblobs() {
        return blobs;
    }

    public String getParent() {
        return parentFrom;
    }

    public String getParentInto() {
        return parentInto;
    }
}

