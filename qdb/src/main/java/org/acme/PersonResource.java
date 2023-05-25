package org.acme;

import Person.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/persons")
@PreMatching
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Person> persons = Person.listAll();
        return Response.ok(persons).build();
    }

    @GET
    @Path("/find/{f_name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByFName(@PathParam("f_name") String f_name) {
        Person person = Person.find("f_name", f_name).firstResult();
        return Response.ok(person).build();
    }

    @GET
    @Path("/find/{l_name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByLName(@PathParam("l_name") String l_name) {
        Person person = Person.find("l_name", l_name).firstResult();
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(person).build();
    }

    @POST
    @Path("/add")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newPerson(Person person) {

        // Return a 400 Bad Request status if the person object is null
        if (person == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Person object is null").build();
        }

        person.persist();
        return Response.ok(person).build();

    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("id") int id, Person update) {
        Person person = Person.findById(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        person.setF_name(update.getF_name());
        person.setL_name(update.getL_name());

        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("id") int id){
        Person person = Person.findById(id);
        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        person.delete();
        return Response.ok(person).build();
    }

}
