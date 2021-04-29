package edu.upc.dsa.services;


import edu.upc.dsa.Covid19Manager;
import edu.upc.dsa.Covid19ManagerImpl;
import edu.upc.dsa.models.Marca;
import edu.upc.dsa.models.Marca;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/marca", description = "Endpoint to Vacunes Service")
@Path("/Marca")
public class MarcaService {

    private Covid19Manager tm;

    public MarcaService() {
        this.tm = Covid19ManagerImpl.getInstance();
        if (tm.size()==0) {
            this.tm.addMarca2("Pfizer");
            this.tm.addMarca2("Moderna");
            this.tm.addMarca2("AstraZeneca");
        }
    }

    @GET
    @ApiOperation(value = "Retorna totes les vacunes", notes = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Marca.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMarcas() {

        List<Marca> marcas = this.tm.findAllMarcas();

        GenericEntity<List<Marca>> entity = new GenericEntity<List<Marca>>(marcas) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "Retorna una marca segons el seu marcat", notes = "GET, has d'indicar un id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Marca.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMarca(@PathParam("id") String id) {
        Marca a = this.tm.getMarca(id);
        if (a == null) return Response.status(404).build();
        else  return Response.status(201).entity(a).build();
    }

    @DELETE
    @ApiOperation(value = "Elimina una marca segons el seu marcat", notes = "DELETE, has d'indicar un id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{id}")
    public Response deleteMarca(@PathParam("id") String id) {
        Marca a = this.tm.getMarca(id);
        if (a == null) return Response.status(404).build();
        else this.tm.deleteMarca(id);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "Inserta una marca", notes = "PUT, has de introduir les dades de la marca")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateMarca(String marca) {

        Marca a = this.tm.updateMarca(marca);

        if (a == null) return Response.status(404).build();

        return Response.status(201).build();
    }





}
