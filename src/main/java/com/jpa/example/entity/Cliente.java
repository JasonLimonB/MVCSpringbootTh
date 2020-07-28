package com.jpa.example.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table( name = "clientes")
public class Cliente implements Serializable{

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;


    // Se puso el formato de la fecha que pide en la base de datos, ya que si no se manda como lo pide manda error en la base de datos
    @Column( name = "create_at")
    @Temporal( TemporalType.DATE )
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createAt;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
