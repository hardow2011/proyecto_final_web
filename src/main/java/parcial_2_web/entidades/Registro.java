package parcial_2_web.entidades;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // crear el ID de forma automática
    private int id;
    private String nombre;
    private String nivelEscolar;
    private float latitud;
    private float longitud;
    @ManyToOne
    private Usuario usuario;

    public Registro() {
        
    }

    public Registro(String nombre, String nivelEscolar, float latitud, float longitud) {
        this.setNombre(nombre);
        this.setNivelEscolar(nivelEscolar);
        this.setLatitud(latitud);
        this.setLongitud(longitud);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivelEscolar() {
        return nivelEscolar;
    }

    public void setNivelEscolar(String nivelEscolar) {
        this.nivelEscolar = nivelEscolar;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}