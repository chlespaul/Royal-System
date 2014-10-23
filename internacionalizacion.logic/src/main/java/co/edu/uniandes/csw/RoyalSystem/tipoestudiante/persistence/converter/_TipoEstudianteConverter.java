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

package co.edu.uniandes.csw.RoyalSystem.tipoestudiante.persistence.converter;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;


import co.edu.uniandes.csw.RoyalSystem.tipoestudiante.logic.dto.TipoEstudianteDTO;
import co.edu.uniandes.csw.RoyalSystem.tipoestudiante.persistence.entity.TipoEstudianteEntity;

public abstract class _TipoEstudianteConverter {

	public static TipoEstudianteDTO entity2PersistenceDTO(TipoEstudianteEntity entity){
		if (entity != null) {
			TipoEstudianteDTO dto = new TipoEstudianteDTO();
					dto.setName(entity.getName());
					dto.setId(entity.getId());
			return dto;
		}else{
			return null;
		}
	}
	
	public static TipoEstudianteEntity persistenceDTO2Entity(TipoEstudianteDTO dto){
		if(dto!=null){
			TipoEstudianteEntity entity=new TipoEstudianteEntity();
					entity.setName(dto.getName());
			
					entity.setId(dto.getId());
			
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<TipoEstudianteDTO> entity2PersistenceDTOList(List<TipoEstudianteEntity> entities){
		List<TipoEstudianteDTO> dtos=new ArrayList<TipoEstudianteDTO>();
		for(TipoEstudianteEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<TipoEstudianteEntity> persistenceDTO2EntityList(List<TipoEstudianteDTO> dtos){
		List<TipoEstudianteEntity> entities=new ArrayList<TipoEstudianteEntity>();
		for(TipoEstudianteDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}