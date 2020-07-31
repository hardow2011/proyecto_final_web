package parcial_2_web.services;

import parcial_2_web.entidades.Usuario;

public class UsuarioServices extends GestionDb<Usuario>{

    private static UsuarioServices instancia;

    public UsuarioServices() {
        super(Usuario.class);
        // TODO Auto-generated constructor stub
    }
    
    public static UsuarioServices getInstancia(){
        if(instancia == null){
            instancia = new UsuarioServices();
        }
        return instancia;
    }

}