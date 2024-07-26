package ra.category_product.repository;

import java.util.List;

public interface IGenericRepository<T,E> {
    List<T> getAll ();
    void save(T t);
    void update(E e,T t);
    T findById(E e);
    void delete(E e);
    List<T> searchByName(String name);
}
