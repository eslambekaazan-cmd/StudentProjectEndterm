package patterns;

import model.Routine;
import model.RoutineType;

public class RoutineBuilder {

    private int id;
    private String name;
    private RoutineType type;

    public RoutineBuilder id(int id) {
        this.id = id;
        return this;
    }

    public RoutineBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RoutineBuilder type(RoutineType type) {
        this.type = type;
        return this;
    }

    public Routine build() {
        return new Routine(id, name, type);
    }
}
