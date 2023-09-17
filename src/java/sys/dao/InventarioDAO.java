package sys.dao;

import java.util.List;
import sys.modelo.Inventario;

public interface InventarioDAO {

    public List<Inventario> select();

    public void insert(Inventario inventario);

    public void update(Inventario inventario);

    public void delete(Inventario inventario);

}
