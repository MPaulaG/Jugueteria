package sys.bean;

import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.PrimeFaces;
import sys.dao.UsuarioDAO;
import sys.impl.UsuarioDAOImpl;
import sys.modelo.Sesion;

@ManagedBean
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private Sesion sesion;
    private String usuario;
    private String password;

    public LoginBean() {
        this.sesion = new Sesion();
    }

    public String getNombreUsuario() {
        return usuario;
    }

    public void setNombreUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public void login() {
        FacesMessage message = null; //envia mensajes emergentes
        boolean loggedIn = false;
        String ruta = "";

        UsuarioDAO datos = new UsuarioDAOImpl();
        this.sesion = datos.login(sesion);

        if (sesion != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sesion", this.sesion.getIdUsuario());
            loggedIn = true;
            ruta = "/Jugueteria/faces/views/Bienvenido.xhtml";
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.sesion.getUsuario());
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error de acceso", "Usuario o password incorrectos");
            sesion = new Sesion();
        }

        FacesContext.getCurrentInstance().addMessage(null, message);//toma todas las faces por las que pasa una pagina, y muestra la fase que contiene cada pagina
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);//manda cualquier parametro a la vista. lso gestiona dentro de un java script
        PrimeFaces.current().ajax().addCallbackParam("ruta", ruta);
    }

    public String logout() {
        this.usuario = null;
        this.password = null;

        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        httpSession.invalidate();//Borra la sesion
        return "/Login";

    }
}
