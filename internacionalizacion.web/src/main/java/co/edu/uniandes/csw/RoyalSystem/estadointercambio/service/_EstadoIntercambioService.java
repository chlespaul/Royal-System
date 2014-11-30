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

package co.edu.uniandes.csw.RoyalSystem.estadointercambio.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.RoyalSystem.estadointercambio.logic.api.IEstadoIntercambioLogicService;
import co.edu.uniandes.csw.RoyalSystem.estadointercambio.logic.dto.EstadoIntercambioDTO;
import co.edu.uniandes.csw.RoyalSystem.estadointercambio.logic.dto.EstadoIntercambioPageDTO;


public abstract class _EstadoIntercambioService {

	@Inject
	protected IEstadoIntercambioLogicService estadoIntercambioLogicService;
	
	@POST
	public EstadoIntercambioDTO createEstadoIntercambio(EstadoIntercambioDTO estadoIntercambio){
		return estadoIntercambioLogicService.createEstadoIntercambio(estadoIntercambio);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteEstadoIntercambio(@PathParam("id") Long id){
		estadoIntercambioLogicService.deleteEstadoIntercambio(id);
	}
	
	@GET
	public EstadoIntercambioPageDTO getEstadoIntercambios(@QueryParam("page") Integer page, @QueryParam("maxRecords") Integer maxRecords){
		return estadoIntercambioLogicService.getEstadoIntercambios(page, maxRecords);
	}
	
	@GET
	@Path("{id}")
	public EstadoIntercambioDTO getEstadoIntercambio(@PathParam("id") Long id){
		return estadoIntercambioLogicService.getEstadoIntercambio(id);
	}
	
	@PUT
	public void updateEstadoIntercambio(@PathParam("id") Long id, EstadoIntercambioDTO estadoIntercambio){
		estadoIntercambioLogicService.updateEstadoIntercambio(estadoIntercambio);
	}
	
}