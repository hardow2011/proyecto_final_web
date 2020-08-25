package parcial_2_web.entidades;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.eclipse.jetty.util.thread.strategy.ProduceConsume;

@Entity
public class Foto implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob
    private String fotoBase64;

    @OneToOne
    @JsonBackReference
    private Registro registro;

    public Foto() {
    }

    public Foto(String fotoBase64){
        this.fotoBase64 = fotoBase64;
    }

    public Foto(String fotoBase64, Registro registro){
        this.fotoBase64 = fotoBase64;
        this.registro = registro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }

    public void setFotoBase64(String fotoBase64) {
        this.fotoBase64 = fotoBase64;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

}