/* ========================================================================
 * Copyright 2014 RoyalSystem
 *
 * Licensed under the MIT, The MIT License (MIT)
 * Copyright (c) 2014 RoyalSystem

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 * ========================================================================


Source generated by CrudMaker version 1.0.0.201411201032

*/

package co.edu.uniandes.csw.RoyalSystem.usuario.service;

import co.edu.uniandes.csw.RoyalSystem.usuario.logic.dto.UsuarioDTO;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Usuario")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioService extends _UsuarioService {

/**
     * Servicio que retorna un texto con el id de un cliente basado en su nombre
     * este servicio lo utiliza el carrito de compras para poder buscar en su
     * base de datos.
     *
     * @param name el nombre del cliente
     * @return texto con el id
     */
    @GET
    @Path("services/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUsuarioId(@PathParam("name") String name) {
        return "" + usuarioLogicService.getUsuarioId(name).getId();
    }
 
    /**
     * Servicio que retorna un cliente dado su nombre
     *
     * @param name el nombre del cliente
     * @return json con el cliente
     */
    @GET
    @Path("servicejson/{name}")
    public UsuarioDTO getUsuariobyName(@PathParam("name") String name) {
        return usuarioLogicService.getUsuarioId(name);
    }
 
}