package pubcoding.org.shot.model;

import java.util.List;

public class Shotter {

    private final String name;
    private final Long record;
    private final List<Log> log;

    public Shotter(Message message) {
        this.name = message.getUser();
        this.record = message.getValue();
        this.log = message.getLog();
    }

    public String getName() { return name; }

    public Long getRecord() { return record; }

    public List<Log> getLog() { return this.log; }

}
