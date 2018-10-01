package pubcoding.org.shot.model;

import android.support.annotation.StringRes;

import java.util.LinkedList;
import java.util.List;

public class Message {

    private String user;
    private Long value;
    private List<Log> log;

    public Message() { }

    public Message(Shotter user) {
        this.user = user.getName();
        this.value = user.getRecord();
        this.log = user.getLog();
    }

    public String getUser() {
        return this.user;
    }

    public Long getValue() {
        return this.value;
    }

    public List<Log> getLog() {
        return this.log;
    }

    public void updateValue(long variation, @StringRes int description) {
        this.value += variation;
        updateLog(description);
    }

    private void updateLog(int description) {
        if (this.log == null) this.log = new LinkedList<>();
        this.log.add(Log.createNewLog(description));
    }
}
