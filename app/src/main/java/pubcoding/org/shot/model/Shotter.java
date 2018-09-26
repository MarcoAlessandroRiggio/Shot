package pubcoding.org.shot.model;

public class Shotter {

    private String name;
    private Integer record;

    public Shotter(String name, Integer record) {
        this.name = name;
        this.record = record;
    }

    public String getName() { return name; }

    public Integer getRecord() { return record; }

}
