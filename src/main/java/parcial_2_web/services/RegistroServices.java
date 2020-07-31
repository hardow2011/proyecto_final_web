package parcial_2_web.services;

import parcial_2_web.entidades.Registro;

public class RegistroServices extends GestionDb<Registro>{

    private static RegistroServices instancia;

    public RegistroServices() {
        super(Registro.class);
        // TODO Auto-generated constructor stub
    }

    public static RegistroServices getInstancia(){
        if(instancia == null){
            instancia = new RegistroServices();
        }
        return instancia;
    }
    
}