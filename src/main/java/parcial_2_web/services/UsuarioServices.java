package parcial_2_web.services;

import parcial_2_web.entidades.Usuario;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.*;


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

    public List<Usuario> listarSinRelaciones() {
        EntityManager em = getEntityManager();
        Query query = em.createNativeQuery("select id, nombreUsuario, password, admin from usuario ", Usuario.class);
        //query.setParameter("nombre", apellido+"%");
        List<Usuario> lista = query.getResultList();
        return lista;
    }

}