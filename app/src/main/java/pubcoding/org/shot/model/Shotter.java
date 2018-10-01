package pubcoding.org.shot.model;

import java.util.List;

import pubcoding.org.shot.custom.Nominable;

public class Shotter implements Nominable {

    private String name;
    private Long record;
    private List<Log> log;

    public Shotter(Message message) {
        this.name = message.getUser();
        this.record = message.getValue();
        this.log = message.getLog();
    }

    public String getName() { return name; }

    public Long getRecord() { return record; }

    public List<Log> getLog() { return this.log; }

    @Override
    public boolean equals(String name) {
        return this.name.equals(name);
    }

}
