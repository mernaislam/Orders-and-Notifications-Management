package app.repos;

import java.util.ArrayList;

public interface Repository<T> {
    void add(T object);

    void delete(int objectID);

    void update(T object, int objectID);

    T findByID(int objectID);

    ArrayList<T> getAll();

    void loadData();
}
