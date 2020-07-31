package parcial_2_web.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // crear el ID de forma automática
    private int id;
    private String nombreUsuario;
    private String password;
    private boolean admin;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Registro> listaRegistros = new ArrayList<>();

    public Usuario() {
    }

    // Los usuarios nuevos nunca son admin, a menos que se especifique lo contrario.
    public Usuario(String nombreUsuario, String password) {
        this.setNombreUsuario(nombreUsuario);
        this.setPassword(password);
        this.admin = false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Registro> getListaRegistros() {
        return listaRegistros;
    }

    public void setListaRegistros(List<Registro> listaRegistros) {
        this.listaRegistros = listaRegistros;
    }
    
}