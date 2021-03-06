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

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.*;


import co.edu.uniandes.csw.RoyalSystem.estudiante.logic.dto.EstudiantePageDTO;
import co.edu.uniandes.csw.RoyalSystem.estudiante.logic.dto.EstudianteDTO;
import co.edu.uniandes.csw.RoyalSystem.estudiante.persistence.api.IEstudiantePersistence;
import co.edu.uniandes.csw.RoyalSystem.estudiante.persistence.entity.EstudianteEntity;
import co.edu.uniandes.csw.RoyalSystem.estudiante.persistence.converter.EstudianteConverter;
import static co.edu.uniandes.csw.RoyalSystem.util._TestUtil.*;

@RunWith(Arquillian.class)
public class EstudiantePersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(EstudiantePersistence.class.getPackage())
				.addPackage(EstudianteEntity.class.getPackage())
				.addPackage(IEstudiantePersistence.class.getPackage())
                .addPackage(EstudianteDTO.class.getPackage())
                .addPackage(EstudianteConverter.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IEstudiantePersistence estudiantePersistence;

	@PersistenceContext
	private EntityManager em;

	@Inject
	UserTransaction utx;

	@Before
	public void configTest() {
		System.out.println("em: " + em);
		try {
			utx.begin();
			clearData();
			insertData();
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				utx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private void clearData() {
		em.createQuery("delete from EstudianteEntity").executeUpdate();
	}

	private List<EstudianteEntity> data=new ArrayList<EstudianteEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			EstudianteEntity entity=new EstudianteEntity();
			entity.setName(generateRandom(String.class));
			entity.setCreditosAprovados(generateRandom(Integer.class));
			entity.setCodigo(generateRandom(Integer.class));
			entity.setPromedio(generateRandom(Integer.class));
			entity.setTipoId(generateRandom(Long.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createEstudianteTest(){
		EstudianteDTO dto=new EstudianteDTO();
		dto.setName(generateRandom(String.class));
		dto.setCreditosAprovados(generateRandom(Integer.class));
		dto.setCodigo(generateRandom(Integer.class));
		dto.setPromedio(generateRandom(Integer.class));
		dto.setTipoId(generateRandom(Long.class));
		
		EstudianteDTO result=estudiantePersistence.createEstudiante(dto);
		
		Assert.assertNotNull(result);
		
		EstudianteEntity entity=em.find(EstudianteEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());
		Assert.assertEquals(dto.getCreditosAprovados(), entity.getCreditosAprovados());
		Assert.assertEquals(dto.getCodigo(), entity.getCodigo());
		Assert.assertEquals(dto.getPromedio(), entity.getPromedio());
		Assert.assertEquals(dto.getTipoId(), entity.getTipoId());
	}
	
	@Test
	public void getEstudiantesTest(){
		List<EstudianteDTO> list=estudiantePersistence.getEstudiantes();
		Assert.assertEquals(list.size(), data.size());
        for(EstudianteDTO dto:list){
        	boolean found=false;
            for(EstudianteEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getEstudianteTest(){
		EstudianteEntity entity=data.get(0);
		EstudianteDTO dto=estudiantePersistence.getEstudiante(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getCreditosAprovados(), dto.getCreditosAprovados());
		Assert.assertEquals(entity.getCodigo(), dto.getCodigo());
		Assert.assertEquals(entity.getPromedio(), dto.getPromedio());
		Assert.assertEquals(entity.getTipoId(), dto.getTipoId());
        
	}
	
	@Test
	public void deleteEstudianteTest(){
		EstudianteEntity entity=data.get(0);
		estudiantePersistence.deleteEstudiante(entity.getId());
        EstudianteEntity deleted=em.find(EstudianteEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateEstudianteTest(){
		EstudianteEntity entity=data.get(0);
		
		EstudianteDTO dto=new EstudianteDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		dto.setCreditosAprovados(generateRandom(Integer.class));
		dto.setCodigo(generateRandom(Integer.class));
		dto.setPromedio(generateRandom(Integer.class));
		dto.setTipoId(generateRandom(Long.class));
		
		
		estudiantePersistence.updateEstudiante(dto);
		
		
		EstudianteEntity resp=em.find(EstudianteEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getCreditosAprovados(), resp.getCreditosAprovados());	
		Assert.assertEquals(dto.getCodigo(), resp.getCodigo());	
		Assert.assertEquals(dto.getPromedio(), resp.getPromedio());	
		Assert.assertEquals(dto.getTipoId(), resp.getTipoId());	
	}
	
	@Test
	public void getEstudiantePaginationTest(){
		//Page 1
		EstudiantePageDTO dto1=estudiantePersistence.getEstudiantes(1,2);
        Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
        //Page 2
        EstudiantePageDTO dto2=estudiantePersistence.getEstudiantes(2,2);
        Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
        
        for(EstudianteDTO dto:dto1.getRecords()){
        	boolean found=false;	
            for(EstudianteEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(EstudianteDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(EstudianteEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
}