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

package co.edu.uniandes.csw.RoyalSystem.estadointercambio.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.RoyalSystem.estadointercambio.logic.dto.EstadoIntercambioDTO;
import co.edu.uniandes.csw.RoyalSystem.estadointercambio.logic.dto.EstadoIntercambioPageDTO;
import co.edu.uniandes.csw.RoyalSystem.estadointercambio.logic.api._IEstadoIntercambioLogicService;
import co.edu.uniandes.csw.RoyalSystem.estadointercambio.persistence.api.IEstadoIntercambioPersistence;

public abstract class _EstadoIntercambioLogicService implements _IEstadoIntercambioLogicService {

	@Inject
	protected IEstadoIntercambioPersistence persistance;

	public EstadoIntercambioDTO createEstadoIntercambio(EstadoIntercambioDTO estadoIntercambio){
		return persistance.createEstadoIntercambio( estadoIntercambio); 
    }

	public List<EstadoIntercambioDTO> getEstadoIntercambios(){
		return persistance.getEstadoIntercambios(); 
	}

	public EstadoIntercambioPageDTO getEstadoIntercambios(Integer page, Integer maxRecords){
		return persistance.getEstadoIntercambios(page, maxRecords); 
	}

	public EstadoIntercambioDTO getEstadoIntercambio(Long id){
		return persistance.getEstadoIntercambio(id); 
	}

	public void deleteEstadoIntercambio(Long id){
	    persistance.deleteEstadoIntercambio(id); 
	}

	public void updateEstadoIntercambio(EstadoIntercambioDTO estadoIntercambio){
	    persistance.updateEstadoIntercambio(estadoIntercambio); 
	}	
}