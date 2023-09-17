package sys.dao;

import sys.modelo.Sesion;



public interface UsuarioDAO {
    //Busqueda de usuario
    public Sesion search(Sesion sesion);

    //Inicia sesion
    public Sesion login(Sesion Sesion);

}
