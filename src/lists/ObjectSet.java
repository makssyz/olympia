package lists;

import java.util.Set;
import java.util.TreeSet;

public class ObjectSet {
    private final Set<Object> objects = new TreeSet<>();

    public void add(Object object) {
        objects.add(object);
    }

    @Override
    public String toString() {
        String string = "";
        for (Object object : objects) {
            string = string.concat(object.toString() + "\n");
        }
        return string;
    }

    public Set<Object> getObjects() {
        return objects;
    }
}
