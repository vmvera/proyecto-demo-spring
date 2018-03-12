package cl.seiza.demo.dominio;

import com.google.common.base.MoreObjects;

public class Usuario {
    
    private int id;
    private String nombre;
    private String email;

    public Usuario() {
        // Usado por bean mapper
    }

    private Usuario(Builder builder) {
        setId(builder.id);
        setNombre(builder.nombre);
        setEmail(builder.email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("nombre", nombre)
                .add("email", email)
                .toString();
    }


    public static final class Builder {
        private int id;
        private String nombre;
        private String email;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Usuario build() {
            return new Usuario(this);
        }
    }
}
