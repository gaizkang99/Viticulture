package manager;

import LogicException.LogicExceptions;
import modal.Bodega;
import modal.Campo;
import modal.Entrada;
import modal.Vid;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import utils.TipoVid;

import java.util.ArrayList;
import java.util.List;

public class Manager {

    private Session session;
    private Transaction tx;
    private ArrayList<Entrada> lineas;
    private Bodega b;
    private Campo c;


    public Manager(){
        this.b = null;
        this.c = null;
        init();
    }

    public void init(){
        initSession();
        lineas = getAllInstrucciones();
        lectura(lineas);
        muestraCampos();
        endSession();
    }

    private void initSession() {
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();

        // Get the session object.
        session = sessionFactory.openSession();
    }
    private void endSession() {
        session.close();
    }

    private ArrayList<Entrada> getAllInstrucciones(){
        ArrayList<Entrada> instrucciones = new ArrayList<>();
        try{
            tx = session.beginTransaction();
            Query q = session.createQuery("select e from Entrada e");
            List<Entrada> instrucitonList = q.list();
            instrucciones.addAll(instrucitonList);
            tx.commit();
            System.out.println("Get All Successfully.");
        }catch(HibernateException e){
            if (tx != null){
                tx.rollback(); // Roll back if any exception occurs.
                e.printStackTrace();
            }
        }
        return instrucciones;
    }


    //Recibe la instrucción que quiere leer y la gestiona mediante un switch realizando las diferentes funciones segun el tipo de acción
    private void lectura(ArrayList<Entrada> instruccion){
        int restar = 0;
        for(Entrada e : instruccion){
            try{
                System.out.println(e.toString());
                String[] linea = e.toString().split(" ");
                switch(linea[0]){
                    case "B":
                        compruebaTamano(linea.length,2);
                        b = createCellar(linea[1]);
                        break;
                    case "C":
                        compruebaTamano(linea.length,1);
                        c = assignCellar();
                        break;
                    case "V":
                        compruebaTamano(linea.length,3);
                        TipoVid tv = TipoVid.typeVid(linea[1]);
                        createAssignVid(tv, Integer.parseInt(linea[2]));
                        break;
                    case "#":
                        compruebaTamano(linea.length,1);
                        vendimia();
                        restar++;
                        break;
                    default:
                        System.out.println("Esta instrucción no existe.");
                        break;
                }
            }catch(LogicExceptions le){
                System.out.println(le.getMessage());
            }
        }
    }

    //Comprueba el tamaño que tiene que tener una acción y el tamaño que tiene
    public void compruebaTamano(int i, int j) throws LogicExceptions {
        if (i != j)throw new LogicExceptions(LogicExceptions.Parametros_Incorrectos);
    }

    //Crea un objeto bodega segun el nombre recibido
    private Bodega createCellar(String create) throws HibernateException{
        b = new Bodega(create);
        tx = session.beginTransaction();
        int id = (Integer) session.save(b);
        b = session.get(Bodega.class, id);
        tx.commit();
        System.out.println("Inserted Successfully.");
        return b;
    }

    //Añade la bodega creada en la instrucción anterior a un campo nuevo
    private Campo assignCellar() throws HibernateException{
        c = new Campo(b);
        tx = session.beginTransaction();
        int id = (Integer) session.save(c);
        c = session.get(Campo.class, id);
        tx.commit();
        System.out.println("Inserted Successfully.");
        return c;
    }

    //Asigna un tipo de Vid y una cantidad al campo asignado en base de datos
    private void createAssignVid(TipoVid tv, int quantity) throws HibernateException{
        tx = session.beginTransaction();
        Vid v = new Vid(quantity, tv);
        c.getVidCollection().add(v);
        session.save(v);
        tx.commit();
        System.out.println("Inserted Successfully.");
    }

    //Volcado de la vendimia
    private void vendimia() throws HibernateException{
        b.getVidCollection().addAll(c.getVidCollection());
        tx = session.beginTransaction();
        session.save(b);
        tx.commit();
        System.out.println("Inserted Successfully.");
    }

    private void muestraCampos() throws HibernateException{
        ArrayList<Campo> campos = new ArrayList<>();
        tx = session.beginTransaction();
        Query q = session.createQuery("select c from Campo c");
        List<Campo> campoList = q.list();
        campos.addAll(campoList);
        for(Campo c : campos){
            System.out.println(c.toString());
        }
        tx.commit();
        System.out.println("Get All Successfully.");
    }

}
