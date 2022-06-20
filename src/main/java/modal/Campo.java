package modal;

import utils.TipoVid;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Campo")
public class Campo {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = true)
    private int id;
    @OneToMany
    @JoinColumn(name = "id_campo")
    private List<Vid> vidCollection;
    @OneToOne
    @JoinColumn(name = "id_bodega")
    private Bodega id_Bodega;

    public Campo(){}

    public Campo(Bodega id_Bodega){
        this.id_Bodega = id_Bodega;
        this.vidCollection = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Bodega getId_Bodega() {
        return id_Bodega;
    }

    public List<Vid> getVidCollection() {
        return vidCollection;
    }

    @Override
    public String toString() {
        return "Campo{" +
                "id=" + id +
                ", vidCollection=" + vidCollection.toString() +
                ", Bodega=" + id_Bodega.toString() +
                '}';
    }
}
