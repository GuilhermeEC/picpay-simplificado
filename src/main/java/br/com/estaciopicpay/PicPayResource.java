package br.com.estaciopicpay;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PicPayResource {

    @Inject
    UserService userService;

    @POST
    @Path("/usuarios")
    public Response cadastrarUsuario(Usuario usuario) {
        Usuario usuarioCadastrado = userService.cadastrarUsuario(usuario);
        return Response.status(Response.Status.CREATED).entity(usuarioCadastrado).build();
    }

    @GET
    @Path("/usuarios/{id}")
    public Response encontrarUsuario(@PathParam("id") Long id) {
        return userService.encontrarUsuario(id)
                .map(usuario -> Response.ok(usuario).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Path("/transferir")
    public Response realizarTransferencia(TransferenciaDTO transferencia) {
        boolean sucesso = userService.realizarTransferencia(transferencia.getDeId(), transferencia.getParaId(), transferencia.getValor());
        if (sucesso) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Transferência falhou").build();
    }

    @POST
    @Path("/receber-pagamento/{lojistaId}")
    public Response receberPagamento(@PathParam("lojistaId") Long lojistaId, RecebimentoDTO recebimentoDTO) {
        boolean sucesso = userService.receberPagamento(lojistaId, recebimentoDTO.getValor());

        if (sucesso) {
            return Response.ok().build();
        }

        return Response.status(Response.Status.BAD_REQUEST).entity("Recebimento falhou").build();
    }
}
