package cl.seiza.demo.dominio;

import com.google.common.base.MoreObjects;

public class DemoObject {
    
    private String nombre;
    private Integer id;

    public String getNombre() {
        return nombre;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("nombre", nombre)
                .add("id", id)
                .toString();
    }

    private DemoObject(Builder builder) {
        nombre = builder.nombre;
        id = builder.id;
    }

    public static final class Builder {
        private String nombre;
        private int id;

        public Builder nombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public DemoObject build() {
            return new DemoObject(this);
        }
    }
}
