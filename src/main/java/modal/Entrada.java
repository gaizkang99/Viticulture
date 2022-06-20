package modal;

import javax.persistence.*;

@Entity
@Table(name="Entrada")
public class Entrada {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = true)
    private int id;

    @Column
    private String instruccion;

    public Entrada(){ }

    public Entrada(String instruccion){
        this.instruccion = instruccion;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return instruccion;
    }
}
