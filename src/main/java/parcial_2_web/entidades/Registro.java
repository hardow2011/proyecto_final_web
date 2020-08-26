package parcial_2_web.entidades;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy="registro")
    @JsonManagedReference
    private Foto foto;

    public Registro() {
        
    }

    public Registro(String nombre, String nivelEscolar, float latitud, float longitud) {
        this.setNombre(nombre);
        this.setNivelEscolar(nivelEscolar);
        this.setLatitud(latitud);
        this.setLongitud(longitud);
    }

    public int getId() {
        return id;
    }

    @JsonProperty("name")
    public String getNombre() {
        return nombre;
    }

    @JsonProperty("nombre")
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @JsonProperty("educationLevel")
    public String getNivelEscolar() {
        return nivelEscolar;
    }

    @JsonProperty("nivelEscolar")
    public void setNivelEscolar(String nivelEscolar) {
        this.nivelEscolar = nivelEscolar;
    }

    @JsonProperty("latitude")
    public float getLatitud() {
        return latitud;
    }

    @JsonProperty("latitud")
    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    @JsonProperty("longitude")
    public float getLongitud() {
        return longitud;
    }

    @JsonProperty("longitud")
    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    @JsonProperty("user")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

}