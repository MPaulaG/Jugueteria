package sys.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import sys.componentes.EncriptarPassword;

import sys.dao.UsuarioDAO;
import sys.modelo.Sesion;

import sys.util.HibernateUtil;

public class UsuarioDAOImpl implements UsuarioDAO {

    @Override
    public Sesion search(Sesion sesion) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Sesion WHERE usuario=:usuario";
        Query query = session.createQuery(hql); //preparamos la query
        query.setParameter("usuario", sesion.getUsuario());

        return (Sesion) query.uniqueResult();// si no encuentra registro arroja nulo
    }

    @Override
    public Sesion login(Sesion Sesion) {
        Sesion usuarioLogin = search(Sesion);
        if (usuarioLogin != null) {
           if(!usuarioLogin.getPassword().equals(EncriptarPassword.sha512(Sesion.getPassword()))){
                usuarioLogin = null;
            }
        }
        return usuarioLogin;

    }
}
