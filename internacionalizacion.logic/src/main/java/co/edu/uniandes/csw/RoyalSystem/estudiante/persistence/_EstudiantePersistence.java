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

package co.edu.uniandes.csw.RoyalSystem.estudiante.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.RoyalSystem.estudiante.logic.dto.EstudianteDTO;
import co.edu.uniandes.csw.RoyalSystem.estudiante.logic.dto.EstudiantePageDTO;
import co.edu.uniandes.csw.RoyalSystem.estudiante.persistence.api._IEstudiantePersistence;
import co.edu.uniandes.csw.RoyalSystem.estudiante.persistence.converter.EstudianteConverter;
import co.edu.uniandes.csw.RoyalSystem.estudiante.persistence.entity.EstudianteEntity;

public abstract class _EstudiantePersistence implements _IEstudiantePersistence {

  	@PersistenceContext(unitName="internacionalizacionPU")
 
	protected EntityManager entityManager;
	
	public EstudianteDTO createEstudiante(EstudianteDTO estudiante) {
		EstudianteEntity entity=EstudianteConverter.persistenceDTO2Entity(estudiante);
		entityManager.persist(entity);
		return EstudianteConverter.entity2PersistenceDTO(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<EstudianteDTO> getEstudiantes() {
		Query q = entityManager.createQuery("select u from EstudianteEntity u");
		return EstudianteConverter.entity2PersistenceDTOList(q.getResultList());
	}

	@SuppressWarnings("unchecked")
	public EstudiantePageDTO getEstudiantes(Integer page, Integer maxRecords) {
		Query count = entityManager.createQuery("select count(u) from EstudianteEntity u");
		Long regCount = 0L;
		regCount = Long.parseLong(count.getSingleResult().toString());
		Query q = entityManager.createQuery("select u from EstudianteEntity u");
		if (page != null && maxRecords != null) {
		    q.setFirstResult((page-1)*maxRecords);
		    q.setMaxResults(maxRecords);
		}
		EstudiantePageDTO response = new EstudiantePageDTO();
		response.setTotalRecords(regCount);
		response.setRecords(EstudianteConverter.entity2PersistenceDTOList(q.getResultList()));
		return response;
	}

	public EstudianteDTO getEstudiante(Long id) {
		return EstudianteConverter.entity2PersistenceDTO(entityManager.find(EstudianteEntity.class, id));
	}

	public void deleteEstudiante(Long id) {
		EstudianteEntity entity=entityManager.find(EstudianteEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateEstudiante(EstudianteDTO detail) {
		EstudianteEntity entity=entityManager.merge(EstudianteConverter.persistenceDTO2Entity(detail));
		EstudianteConverter.entity2PersistenceDTO(entity);
	}

}