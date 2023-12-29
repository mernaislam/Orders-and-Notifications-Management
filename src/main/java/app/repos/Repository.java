package app.repos;

import java.util.ArrayList;

public interface Repository<T> {
    public void add(T object);

    public void delete(int objectID);

    public void update(T object, int objectID);

    public T findByID(int objectID);

    public ArrayList<T> getAll();

    public void loadData();
}
