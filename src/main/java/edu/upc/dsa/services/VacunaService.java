package edu.upc.dsa.services;


import edu.upc.dsa.Covid19Manager;
import edu.upc.dsa.Covid19ManagerImpl;
import edu.upc.dsa.models.Vacuna;
import edu.upc.dsa.models.Vacuna;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/vacuna", description = "Endpoint to Vacunes Service")
@Path("/Vacuna")
public class VacunaService {

    private Covid19Manager tm;

    public VacunaService() {
        this.tm = Covid19ManagerImpl.getInstance();
        if (tm.size()==0) {
            this.tm.addVacuna("Peepe","Pfizer",1);
            this.tm.addVacuna("Jose","Moderna",2);
            this.tm.addVacuna("Luis","AstraZeneca",3);
        }
    }

    @GET
    @ApiOperation(value = "Retorna totes les vacunes", notes = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Vacuna.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVacunas() {

        List<Vacuna> vacunas = this.tm.findAllVacunas();

        GenericEntity<List<Vacuna>> entity = new GenericEntity<List<Vacuna>>(vacunas) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "Retorna una vacuna segons el seu vacunat", notes = "GET, has d'indicar un id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Vacuna.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVacuna(@PathParam("id") String id) {
        Vacuna a = this.tm.getVacunaFromVacunado(id);
        if (a == null) return Response.status(404).build();
        else  return Response.status(201).entity(a).build();
    }

    @DELETE
    @ApiOperation(value = "Elimina una vacuna segons el seu vacunat", notes = "DELETE, has d'indicar un id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    public Response deleteVacuna(@PathParam("id") String id) {
        Vacuna a = this.tm.getVacunaFromVacunado(id);
        if (a == null) return Response.status(404).build();
        else this.tm.deleteVacuna(id);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "Inserta una vacuna", notes = "PUT, has de introduir les dades de la vacuna")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateVacuna(Vacuna vacuna) {

        Vacuna a = this.tm.updateVacuna(vacuna);

        if (a == null) return Response.status(404).build();

        return Response.status(201).build();
    }





}
