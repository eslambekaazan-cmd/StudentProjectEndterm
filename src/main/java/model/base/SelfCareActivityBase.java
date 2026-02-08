package model.base;

import exception.InvalidInputException;
import interfaces.Printable;
import interfaces.Validatable;
import model.Routine;

public abstract class SelfCareActivityBase implements Printable, Validatable<SelfCareActivityBase> {

    private int id;
    private String name;
    private Routine routine;

    protected SelfCareActivityBase(int id, String name, Routine routine) {
        this.id = id;
        this.name = name;
        this.routine = routine;
    }

    public abstract String getCategory();
    public abstract int estimateScore();

    public String shortLabel() {
        return "#" + id + " " + name + " (" + getCategory() + ")";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Routine getRoutine() { return routine; }
    public void setRoutine(Routine routine) { this.routine = routine; }

    @Override
    public void printInfo() {
        System.out.println(shortLabel());
        if (routine != null && routine.getType() != null) {
            System.out.println("Routine: " + routine.getName() +
                               " | Type: " + routine.getType().getName());
        }
    }

    @Override
    public void validate() {
        if (id < 0) throw new InvalidInputException("id must be >= 0");
        if (name == null || name.trim().isEmpty())
            throw new InvalidInputException("name cannot be empty");
        if (routine == null)
            throw new InvalidInputException("routine cannot be null");
    }
}
