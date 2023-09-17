package sys.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sys.dao.InventarioDAO;
import sys.modelo.Inventario;
import sys.util.HibernateUtil;

public class InventarioDAOImpl implements InventarioDAO {

    @Override
    public List<Inventario> select() {
        List<Inventario> inventarios = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        String hql = "FROM Inventario";

        try {
            inventarios = session.createQuery(hql).list();
            trans.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            trans.rollback();
        }
        return inventarios;
    }

    @Override
    public void insert(Inventario inventario) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(inventario);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Inventario inventario) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(inventario);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public void delete(Inventario inventario) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(inventario);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

    }

}
