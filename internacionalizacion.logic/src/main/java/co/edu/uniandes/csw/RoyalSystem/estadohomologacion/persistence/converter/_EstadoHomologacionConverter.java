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


Source generated by CrudMaker version 1.0.0.201410152247

*/

package co.edu.uniandes.csw.RoyalSystem.estadohomologacion.persistence.converter;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;


import co.edu.uniandes.csw.RoyalSystem.estadohomologacion.logic.dto.EstadoHomologacionDTO;
import co.edu.uniandes.csw.RoyalSystem.estadohomologacion.persistence.entity.EstadoHomologacionEntity;

public abstract class _EstadoHomologacionConverter {

	public static EstadoHomologacionDTO entity2PersistenceDTO(EstadoHomologacionEntity entity){
		if (entity != null) {
			EstadoHomologacionDTO dto = new EstadoHomologacionDTO();
					dto.setName(entity.getName());
					dto.setId(entity.getId());
			return dto;
		}else{
			return null;
		}
	}
	
	public static EstadoHomologacionEntity persistenceDTO2Entity(EstadoHomologacionDTO dto){
		if(dto!=null){
			EstadoHomologacionEntity entity=new EstadoHomologacionEntity();
					entity.setName(dto.getName());
			
					entity.setId(dto.getId());
			
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<EstadoHomologacionDTO> entity2PersistenceDTOList(List<EstadoHomologacionEntity> entities){
		List<EstadoHomologacionDTO> dtos=new ArrayList<EstadoHomologacionDTO>();
		for(EstadoHomologacionEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<EstadoHomologacionEntity> persistenceDTO2EntityList(List<EstadoHomologacionDTO> dtos){
		List<EstadoHomologacionEntity> entities=new ArrayList<EstadoHomologacionEntity>();
		for(EstadoHomologacionDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}