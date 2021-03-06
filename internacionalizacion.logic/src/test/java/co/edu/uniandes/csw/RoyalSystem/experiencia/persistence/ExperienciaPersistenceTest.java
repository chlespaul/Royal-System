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

package co.edu.uniandes.csw.RoyalSystem.experiencia.persistence;

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


import co.edu.uniandes.csw.RoyalSystem.experiencia.logic.dto.ExperienciaPageDTO;
import co.edu.uniandes.csw.RoyalSystem.experiencia.logic.dto.ExperienciaDTO;
import co.edu.uniandes.csw.RoyalSystem.experiencia.persistence.api.IExperienciaPersistence;
import co.edu.uniandes.csw.RoyalSystem.experiencia.persistence.entity.ExperienciaEntity;
import co.edu.uniandes.csw.RoyalSystem.experiencia.persistence.converter.ExperienciaConverter;
import static co.edu.uniandes.csw.RoyalSystem.util._TestUtil.*;

@RunWith(Arquillian.class)
public class ExperienciaPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ExperienciaPersistence.class.getPackage())
				.addPackage(ExperienciaEntity.class.getPackage())
				.addPackage(IExperienciaPersistence.class.getPackage())
                .addPackage(ExperienciaDTO.class.getPackage())
                .addPackage(ExperienciaConverter.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IExperienciaPersistence experienciaPersistence;

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
		em.createQuery("delete from ExperienciaEntity").executeUpdate();
	}

	private List<ExperienciaEntity> data=new ArrayList<ExperienciaEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ExperienciaEntity entity=new ExperienciaEntity();
			entity.setTexto(generateRandom(String.class));
			entity.setName(generateRandom(String.class));
			entity.setTitulo(generateRandom(String.class));
			entity.setFecha(generateRandom(Date.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createExperienciaTest(){
		ExperienciaDTO dto=new ExperienciaDTO();
		dto.setTexto(generateRandom(String.class));
		dto.setName(generateRandom(String.class));
		dto.setTitulo(generateRandom(String.class));
dto.setFecha(generateRandomDate());
		
		ExperienciaDTO result=experienciaPersistence.createExperiencia(dto);
		
		Assert.assertNotNull(result);
		
		ExperienciaEntity entity=em.find(ExperienciaEntity.class, result.getId());
		
		Assert.assertEquals(dto.getTexto(), entity.getTexto());
		Assert.assertEquals(dto.getName(), entity.getName());
		Assert.assertEquals(dto.getTitulo(), entity.getTitulo());
Assert.assertEquals(parseDate(dto.getFecha()), entity.getFecha());	
	}
	
	@Test
	public void getExperienciasTest(){
		List<ExperienciaDTO> list=experienciaPersistence.getExperiencias();
		Assert.assertEquals(list.size(), data.size());
        for(ExperienciaDTO dto:list){
        	boolean found=false;
            for(ExperienciaEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getExperienciaTest(){
		ExperienciaEntity entity=data.get(0);
		ExperienciaDTO dto=experienciaPersistence.getExperiencia(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getTexto(), dto.getTexto());
		Assert.assertEquals(entity.getName(), dto.getName());
		Assert.assertEquals(entity.getTitulo(), dto.getTitulo());
        
	}
	
	@Test
	public void deleteExperienciaTest(){
		ExperienciaEntity entity=data.get(0);
		experienciaPersistence.deleteExperiencia(entity.getId());
        ExperienciaEntity deleted=em.find(ExperienciaEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateExperienciaTest(){
		ExperienciaEntity entity=data.get(0);
		
		ExperienciaDTO dto=new ExperienciaDTO();
		dto.setId(entity.getId());
		dto.setTexto(generateRandom(String.class));
		dto.setName(generateRandom(String.class));
		dto.setTitulo(generateRandom(String.class));
dto.setFecha(generateRandomDate());
		
		
		experienciaPersistence.updateExperiencia(dto);
		
		
		ExperienciaEntity resp=em.find(ExperienciaEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getTexto(), resp.getTexto());	
		Assert.assertEquals(dto.getName(), resp.getName());	
		Assert.assertEquals(dto.getTitulo(), resp.getTitulo());	
Assert.assertEquals(parseDate(dto.getFecha()), resp.getFecha());
	}
	
	@Test
	public void getExperienciaPaginationTest(){
		//Page 1
		ExperienciaPageDTO dto1=experienciaPersistence.getExperiencias(1,2);
        Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
        //Page 2
        ExperienciaPageDTO dto2=experienciaPersistence.getExperiencias(2,2);
        Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
        
        for(ExperienciaDTO dto:dto1.getRecords()){
        	boolean found=false;	
            for(ExperienciaEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(ExperienciaDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(ExperienciaEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
}