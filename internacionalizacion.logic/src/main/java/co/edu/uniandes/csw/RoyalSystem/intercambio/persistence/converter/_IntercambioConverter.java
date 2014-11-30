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

package co.edu.uniandes.csw.RoyalSystem.intercambio.persistence.converter;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;


import co.edu.uniandes.csw.RoyalSystem.intercambio.logic.dto.IntercambioDTO;
import co.edu.uniandes.csw.RoyalSystem.intercambio.persistence.entity.IntercambioEntity;

public abstract class _IntercambioConverter {

 
	private final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	public static IntercambioDTO entity2PersistenceDTO(IntercambioEntity entity){
		if (entity != null) {
			IntercambioDTO dto = new IntercambioDTO();
					dto.setId(entity.getId());
					dto.setName(entity.getName());
 
			    if(entity.getFechaCreacion() != null){
					dto.setFechaCreacion(DATE_FORMAT.format(entity.getFechaCreacion()));
				}	
					dto.setEstudianteId(entity.getEstudianteId());
					dto.setEstadoId(entity.getEstadoId());
					dto.setConvenioId(entity.getConvenioId());
			return dto;
		}else{
			return null;
		}
	}
	
	public static IntercambioEntity persistenceDTO2Entity(IntercambioDTO dto){
		if(dto!=null){
			IntercambioEntity entity=new IntercambioEntity();
					entity.setId(dto.getId());
			
					entity.setName(dto.getName());
			
 
			      try{ 
			        if(dto.getFechaCreacion() != null){
						entity.setFechaCreacion(DATE_FORMAT.parse(dto.getFechaCreacion()));
					}
				  } catch (Exception ex) {
                        Logger.getLogger(_IntercambioConverter.class.getName()).log(Level.SEVERE, null, ex);
                  }	
			
					entity.setEstudianteId(dto.getEstudianteId());
			
					entity.setEstadoId(dto.getEstadoId());
			
					entity.setConvenioId(dto.getConvenioId());
			
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<IntercambioDTO> entity2PersistenceDTOList(List<IntercambioEntity> entities){
		List<IntercambioDTO> dtos=new ArrayList<IntercambioDTO>();
		for(IntercambioEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<IntercambioEntity> persistenceDTO2EntityList(List<IntercambioDTO> dtos){
		List<IntercambioEntity> entities=new ArrayList<IntercambioEntity>();
		for(IntercambioDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}