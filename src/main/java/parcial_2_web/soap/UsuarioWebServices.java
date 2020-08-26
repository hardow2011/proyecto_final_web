package parcial_2_web.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import parcial_2_web.entidades.Usuario;
import parcial_2_web.services.UsuarioServices;

@WebService
public class UsuarioWebServices {
    private UsuarioServices usuarioServices = UsuarioServices.getInstancia();

    @WebMethod
    public List<Usuario> getListaUsuarios(){
        return usuarioServices.listar();
    }

}