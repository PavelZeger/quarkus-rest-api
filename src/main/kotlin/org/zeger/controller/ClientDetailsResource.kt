package org.zeger.controller

import org.zeger.model.ClientDetails
import org.zeger.service.ClientDetailsService
import java.util.Objects.nonNull
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * @author Pavel Zeger
 */
@Path("/api")
class ClientDetailsResource {

    @Inject
    lateinit var clientDetailsService: ClientDetailsService

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    fun findAll(): Response {
        return Response.ok(clientDetailsService.findAll()).build()
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findById(@PathParam("id") id: Long): Response {
        val clientDetails = clientDetailsService.findById(id)
        if (nonNull(clientDetails)) return Response.ok(clientDetails).build()
        return Response.ok("Client doesn't exist").status(Response.Status.NOT_FOUND).build()
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    fun findByName(@PathParam("name") name: String): Response {
        val clientDetails = clientDetailsService.findByName(name)
        if (nonNull(clientDetails)) return Response.ok(clientDetails).build()
        return Response.ok("Client doesn't exist").status(Response.Status.NOT_FOUND).build()
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun saveClient(clientDetails: ClientDetails): Response {
        clientDetailsService.saveClient(clientDetails)
        return Response.ok(clientDetails).status(Response.Status.CREATED).build()
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun updateClient(clientDetails: ClientDetails): Response {
        clientDetailsService.updateClient(clientDetails)
        val updatedClient = clientDetailsService.findById(clientDetails.id!!)
        if (nonNull(updatedClient)) return Response.ok(updatedClient).build()
        return Response.ok("Client not found").build()
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun deleteClient(@PathParam("id") id: Long): Response {
        val clientDetails = clientDetailsService.findById(id)
        if (nonNull(clientDetails)) {
            clientDetailsService.deleteClient(id)
            return Response.ok("Client deleted").build()
        }
        return Response.ok("Client doesn't exist").status(Response.Status.NOT_FOUND).build()
    }

}