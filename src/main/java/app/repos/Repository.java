package app.repos;

public interface Repository {
    public void add(Object object);
    public void delete(Object object);
    public void update(Object object);
    public void save(Object object);
    public Object findByID(int id);
    public void loadData();
}
