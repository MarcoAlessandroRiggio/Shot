package pubcoding.org.shot.model;

public class Shotter {

    private String name;
    private Long record;

    public Shotter(String name, Long record) {
        this.name = name;
        this.record = record;
    }

    public Shotter(Message message) {
        this.name = message.getUser();
        this.record = message.getValue();
    }

    public String getName() { return name; }

    public Long getRecord() { return record; }

}
