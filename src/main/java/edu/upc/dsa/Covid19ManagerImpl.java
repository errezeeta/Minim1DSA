package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.util.*;

import org.apache.log4j.Logger;

public class Covid19ManagerImpl implements Covid19Manager {
    private static Covid19Manager instance;
    protected List<Vacuna> vacunas;
    protected List<Seguimiento> seguimientos;
    protected List<Marca> marcas;
    protected HashMap<String, Usuario> usuaris= new HashMap<String,Usuario>();

    final static Logger logger = Logger.getLogger(Covid19ManagerImpl.class);


    private Covid19ManagerImpl() {
        this.usuaris = new HashMap<>();
        this.vacunas = new LinkedList<>();
        this.marcas= new ArrayList<>();
        this.seguimientos= new LinkedList<>();
    }

    public static Covid19Manager getInstance() {
        if (instance==null) instance = new Covid19ManagerImpl();
        return instance;
    }

    @Override
    public List<Vacuna> showVacunasByMarcaAndDate() {
        Collections.sort(this.vacunas, new Comparator<Vacuna>() {
            @Override
            public int compare(Vacuna o1, Vacuna o2) {
                return Integer.compare(o2.getOrdenFecha(), o1.getOrdenFecha());
            }
        });
        Collections.sort(this.vacunas, new Comparator<Vacuna>() {
            @Override
            public int compare(Vacuna o1, Vacuna o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        return this.vacunas;
    }

    @Override
    public List<Marca> showMarcasByAplicadas() {
        Collections.sort(this.marcas, new Comparator<Marca>() {
            @Override
            public int compare(Marca o1, Marca o2) {
                return Integer.compare(o1.getCont(),o2.getCont());
            }
        });
        return  this.marcas;
    }

    @Override
    public List<Seguimiento> showSeguimientosByPersona(String idVacunado) {
        List<Seguimiento> SeguimientosByParam = null;
        for (Seguimiento s: this.seguimientos) {
            if (s.getIdVacunado().equals(idVacunado)) {
                SeguimientosByParam.add(s);
            }
        }
        return  SeguimientosByParam;

    }

    //Vacunas
    @Override
    public Vacuna addVacuna(String idUsuario, String marca, int orden) {
        return this.addVacuna(new Vacuna(idUsuario,marca,orden));
    }

    @Override
    public Vacuna addVacuna(Vacuna v) {
        logger.info("nueva vacuna "+v);
        this.vacunas.add(v);
        logger.info("Nueva vacunación añadida correctamente!");
        return v ;
    }

    @Override
    public Vacuna getVacunaFromVacunado(String idUsuario) {
        logger.info("getVacuna de("+idUsuario+")");

        for (Vacuna v: this.vacunas) {
            if (v.getIdVacunado().equals(idUsuario)) {
                logger.info(v.toString());
                return v;
            }
        }
        logger.warn("not found");
        return null;
    }

    @Override
    public List<Vacuna> findAllVacunas() {
        return this.vacunas;
    }

    @Override
    public void deleteVacuna(String id) {
        Vacuna v =this.getVacunaFromVacunado(id);
        if (v==null)
        {
            logger.warn("No id found");
        }
        else logger.info(v+" deleted");
        this.vacunas.remove(v);

    }

    @Override
    public Vacuna updateVacuna(Vacuna v) {
        Vacuna vacuna = this.getVacunaFromVacunado(v.getIdVacunado());

        if (vacuna !=null) {
            logger.info(v+"Actualizando...");

            vacuna.setIdVacunado(v.getIdVacunado());
            vacuna.setMarca(v.getMarca());
            vacuna.setOrdenFecha(v.getOrdenFecha());

            logger.info(vacuna +" actualizada!");
        }
        else {
            logger.warn("vacuna not found "+v);
        }

        return vacuna;
    }

    @Override
    public int vacunaSize() {
        int ret = this.vacunas.size();
        logger.info("size " + ret);

        return ret;
    }

    @Override
    public int size() {
        int ret= this.vacunas.size();
        logger.info("tamany: "+ret);
        return ret;
    }

    //Marcas
    public Marca addMarca2(String marca) {
        return this.addMarca2((new Marca(marca,0)));
    }

    @Override
    public Marca addMarca2(Marca m) {
        logger.info("Nueva marca"+m);
        this.marcas.add(m);
        return  m;
    }

    @Override
    public Marca getMarca(String marca) {
        logger.info("getmarca("+marca+")");

        for (Marca m: this.marcas) {
            if (m.getMarca().equals(marca)) {
                logger.info("getMarca("+marca+"): "+m);
                return m;
            }
        }

        logger.warn("not found " + marca);
        return null;
    }


    @Override
    public List<Marca> findAllMarcas() {
        return this.marcas;
    }

    @Override
    public void deleteMarca(String Marca) {

        Marca m = this.getMarca(Marca);
        if (m==null) {
            logger.warn("not found " + m);
        }
        else logger.info(m+" deleted ");

        this.marcas.remove(m);
    }

    @Override
    public Marca updateMarca(String Marca) {
        return null;
    }

    public Marca updateMarca(Marca marca)
    {
        Marca m = this.getMarca(marca.getMarca());

        if (m !=null) {
            logger.info(m+" encontrad! sctualizando ");

            m.setMarca(marca.getMarca());
            m.setCont(marca.getCont());

            logger.info(marca +" updated ");
        }
        else {
            logger.warn("not found "+m);
        }

        return m;
    }

    //Seguimiento
    @Override
    public Seguimiento addSeguimiento(String idVacunado, int orden, String estado) {
            return this.addSeguimiento(new Seguimiento(idVacunado,orden,estado));
    }

    @Override
    public Seguimiento addSeguimiento(Seguimiento s) {
        logger.info("nuevo seguimiento "+s);
        this.seguimientos.add(s);
        logger.info("Nuevo seguimiento añadido correctamente!");
        return s;
    }

    @Override
    public List<Seguimiento> findAllSeguimientosFromVacunado(String idVacunado) {
        logger.info("getSeguimientos de("+idVacunado+")");
        List<Seguimiento> listaSeguimientos = null;

        for (Seguimiento s: this.seguimientos) {
            if (s.getIdVacunado().equals(idVacunado)) {
                listaSeguimientos.add(s);
            }
        }
        return  listaSeguimientos;

    }

    @Override
    public void deleteSeguimiento(Seguimiento s) {
        Seguimiento seg = s;
        if (s==null) {
            logger.warn("not found " + s);
        }
        else logger.info(s+" deleted ");

        this.marcas.remove(s);

    }

    @Override
    public Seguimiento updateSeguimiento(Seguimiento s) {
        Seguimiento seguimiento = s;

        if (seguimiento !=null) {
            logger.info(s+" recibido! actualizando ");

            seguimiento.setEstado(s.getEstado());
            seguimiento.setIdVacunado(s.getIdVacunado());
            seguimiento.setOrdenFecha(s.getOrdenFecha());

            logger.info(seguimiento +" updated ");
        }
        else {
            logger.warn("not found "+s);
        }

        return seguimiento;
    }

    @Override
    public Usuario addUsuario(String nombre) {
        return null;
    }

    @Override
    public Usuario addUsuaio(Usuario u) {
        return null;
    }

}