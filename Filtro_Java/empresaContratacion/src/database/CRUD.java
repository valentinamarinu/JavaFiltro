package database;

import java.util.List;

public interface CRUD {
    // Agregar
    public Object insert(Object obj);

    // Listar
    public List<Object> findAll();

    // Actualizar
    public boolean update(Object obj);

    // Eliminar
    public boolean delete(Object obj);
}
