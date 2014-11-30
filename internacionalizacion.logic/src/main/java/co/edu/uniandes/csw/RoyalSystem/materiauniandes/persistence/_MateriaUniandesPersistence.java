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

package co.edu.uniandes.csw.RoyalSystem.materiauniandes.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.RoyalSystem.materiauniandes.logic.dto.MateriaUniandesDTO;
import co.edu.uniandes.csw.RoyalSystem.materiauniandes.logic.dto.MateriaUniandesPageDTO;
import co.edu.uniandes.csw.RoyalSystem.materiauniandes.persistence.api._IMateriaUniandesPersistence;
import co.edu.uniandes.csw.RoyalSystem.materiauniandes.persistence.converter.MateriaUniandesConverter;
import co.edu.uniandes.csw.RoyalSystem.materiauniandes.persistence.entity.MateriaUniandesEntity;

public abstract class _MateriaUniandesPersistence implements _IMateriaUniandesPersistence {

  	@PersistenceContext(unitName="internacionalizacionPU")
 
	protected EntityManager entityManager;
	
	public MateriaUniandesDTO createMateriaUniandes(MateriaUniandesDTO materiaUniandes) {
		MateriaUniandesEntity entity=MateriaUniandesConverter.persistenceDTO2Entity(materiaUniandes);
		entityManager.persist(entity);
		return MateriaUniandesConverter.entity2PersistenceDTO(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<MateriaUniandesDTO> getMateriaUniandess() {
		Query q = entityManager.createQuery("select u from MateriaUniandesEntity u");
		return MateriaUniandesConverter.entity2PersistenceDTOList(q.getResultList());
	}

	@SuppressWarnings("unchecked")
	public MateriaUniandesPageDTO getMateriaUniandess(Integer page, Integer maxRecords) {
		Query count = entityManager.createQuery("select count(u) from MateriaUniandesEntity u");
		Long regCount = 0L;
		regCount = Long.parseLong(count.getSingleResult().toString());
		Query q = entityManager.createQuery("select u from MateriaUniandesEntity u");
		if (page != null && maxRecords != null) {
		    q.setFirstResult((page-1)*maxRecords);
		    q.setMaxResults(maxRecords);
		}
		MateriaUniandesPageDTO response = new MateriaUniandesPageDTO();
		response.setTotalRecords(regCount);
		response.setRecords(MateriaUniandesConverter.entity2PersistenceDTOList(q.getResultList()));
		return response;
	}

	public MateriaUniandesDTO getMateriaUniandes(Long id) {
		return MateriaUniandesConverter.entity2PersistenceDTO(entityManager.find(MateriaUniandesEntity.class, id));
	}

	public void deleteMateriaUniandes(Long id) {
		MateriaUniandesEntity entity=entityManager.find(MateriaUniandesEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateMateriaUniandes(MateriaUniandesDTO detail) {
		MateriaUniandesEntity entity=entityManager.merge(MateriaUniandesConverter.persistenceDTO2Entity(detail));
		MateriaUniandesConverter.entity2PersistenceDTO(entity);
	}

}