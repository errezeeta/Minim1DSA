package edu.upc.dsa;

import edu.upc.dsa.models.*;

import java.util.List;

public interface Covid19Manager {

    List<Vacuna> showVacunasByMarcaAndDate();
    List<Marca> showMarcasByAplicadas();
    List<Seguimiento> showSeguimientosByPersona(String idVacunado);


    //Operaciones Vacuna
    public Vacuna addVacuna(String idUsuario, String marca, int orden);
    public Vacuna addVacuna(Vacuna v);
    public Vacuna getVacunaFromVacunado (String idUsuario);
    public List<Vacuna> findAllVacunas();
    public void deleteVacuna(String id);
    public Vacuna updateVacuna(Vacuna v);
    public int vacunaSize();
    public int size();

    //Operaciones Marca
    public Marca addMarca2(String Marca);
    public Marca getMarca(String marca);
    public Marca addMarca2(Marca m);
    public List<Marca> findAllMarcas();
    public void deleteMarca(String Marca);
    public Marca updateMarca(String Marca);

    //Operaciones Seguimientos
    public Seguimiento addSeguimiento(String idVacunado, int orden, String estado);
    public Seguimiento addSeguimiento(Seguimiento s);
    public List<Seguimiento> findAllSeguimientosFromVacunado(String idVacunado);
    public void deleteSeguimiento(Seguimiento s);
    public Seguimiento updateSeguimiento(Seguimiento s);

    //Operaciones Personas
    public Usuario addUsuario(String nombre);
    public Usuario addUsuaio(Usuario u);
}
