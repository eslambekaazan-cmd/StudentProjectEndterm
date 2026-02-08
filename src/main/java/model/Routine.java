package model;

public class Routine {
    private int id;
    private String name;
    private RoutineType type;

    public Routine(int id, String name, RoutineType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public RoutineType getType() { return type; }
    public void setType(RoutineType type) { this.type = type; }
}
