package sys.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import sys.dao.InventarioDAO;
import sys.impl.InventarioDAOImpl;
import sys.modelo.Inventario;

@ManagedBean
@Named(value = "inventarioBean")
@ViewScoped
public class InventarioBean {

    private Inventario inventario = null;
    private List<Inventario> inventarios = null;

    public InventarioBean() {

    }

    public List<Inventario> getInventarios() {
        InventarioDAO datos = new InventarioDAOImpl();
        this.inventarios = datos.select();
        return inventarios;
    }

    public void prepareInventario() {
        this.inventario = new Inventario();
    }

    public void insertInventario() {
        InventarioDAO datos = new InventarioDAOImpl();
        datos.insert(inventario);
    }

    public void updateInventario() {
        InventarioDAO datos = new InventarioDAOImpl();
        datos.update(inventario);
        this.inventario = new Inventario();
    }

    public void deleteInventario() {
        InventarioDAO datos = new InventarioDAOImpl();
        datos.update(inventario);
        this.inventario = new Inventario();

    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public void setInventarios(List<Inventario> inventarios) {
        this.inventarios = inventarios;
    }

}
