package modal;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Bodega")
public class Bodega {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    //Como no añadirla a la tabla
    @OneToMany
    @JoinColumn(name = "id_bodega")
    private List <Vid> vidCollection;

    public Bodega(){}

    public Bodega(String name){
        this.name = name;
        this.vidCollection = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Vid> getVidCollection() {
        return vidCollection;
    }

    @Override
    public String toString() {
        return "Bodega{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vidCollection=" + vidCollection +
                '}';
    }
}