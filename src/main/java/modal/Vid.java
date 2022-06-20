package modal;

import utils.TipoVid;

import javax.persistence.*;

@Entity
@Table(name = "Vid")
public class Vid {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = true)
    private int id;
    @Column(name = "tipo")
    private TipoVid type;
    @Column(name = "cantidad")
    private int quantity;


    public Vid(int quantity, TipoVid type){
        this.type = type;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public TipoVid getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Vid{" +
                "id=" + id +
                ", type=" + type +
                ", quantity=" + quantity +
                '}';
    }
}
