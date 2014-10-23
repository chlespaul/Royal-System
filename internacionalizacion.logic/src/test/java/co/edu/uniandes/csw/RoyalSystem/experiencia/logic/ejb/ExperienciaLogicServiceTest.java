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

package co.edu.uniandes.csw.RoyalSystem.experiencia.logic.ejb;

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
import co.edu.uniandes.csw.RoyalSystem.experiencia.logic.api.IExperienciaLogicService;
import co.edu.uniandes.csw.RoyalSystem.experiencia.persistence.ExperienciaPersistence;
import co.edu.uniandes.csw.RoyalSystem.experiencia.persistence.api.IExperienciaPersistence;
import co.edu.uniandes.csw.RoyalSystem.experiencia.persistence.entity.ExperienciaEntity;
import co.edu.uniandes.csw.RoyalSystem.experiencia.persistence.converter.ExperienciaConverter;
import static co.edu.uniandes.csw.RoyalSystem.util._TestUtil.*;

@RunWith(Arquillian.class)
public class ExperienciaLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ExperienciaLogicService.class.getPackage())
				.addPackage(IExperienciaLogicService.class.getPackage())
				.addPackage(ExperienciaPersistence.class.getPackage())
				.addPackage(ExperienciaEntity.class.getPackage())
				.addPackage(IExperienciaPersistence.class.getPackage())
                .addPackage(ExperienciaDTO.class.getPackage())
                .addPackage(ExperienciaConverter.class.getPackage())
                .addPackage(ExperienciaEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IExperienciaLogicService experienciaLogicService;
	
	@Inject
	private IExperienciaPersistence experienciaPersistence;	

	@Before
	public void configTest() {
		try {
			clearData();
			insertData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearData() {
		List<ExperienciaDTO> dtos=experienciaPersistence.getExperiencias();
		for(ExperienciaDTO dto:dtos){
			experienciaPersistence.deleteExperiencia(dto.getId());
		}
	}

	private List<ExperienciaDTO> data=new ArrayList<ExperienciaDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ExperienciaDTO pdto=new ExperienciaDTO();
			pdto.setTexto(generateRandom(String.class));
			pdto.setName(generateRandom(String.class));
			pdto.setTitulo(generateRandom(String.class));
			pdto.setFecha(generateRandomDate());
			pdto=experienciaPersistence.createExperiencia(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createExperienciaTest(){
		ExperienciaDTO ldto=new ExperienciaDTO();
		ldto.setTexto(generateRandom(String.class));
		ldto.setName(generateRandom(String.class));
		ldto.setTitulo(generateRandom(String.class));
		ldto.setFecha(generateRandomDate());
		
		
		ExperienciaDTO result=experienciaLogicService.createExperiencia(ldto);
		
		Assert.assertNotNull(result);
		
		ExperienciaDTO pdto=experienciaPersistence.getExperiencia(result.getId());
		
		Assert.assertEquals(ldto.getTexto(), pdto.getTexto());	
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getTitulo(), pdto.getTitulo());	
		Assert.assertEquals(ldto.getFecha(), pdto.getFecha());	
	}
	
	@Test
	public void getExperienciasTest(){
		List<ExperienciaDTO> list=experienciaLogicService.getExperiencias();
		Assert.assertEquals(list.size(), data.size());
        for(ExperienciaDTO ldto:list){
        	boolean found=false;
            for(ExperienciaDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getExperienciaTest(){
		ExperienciaDTO pdto=data.get(0);
		ExperienciaDTO ldto=experienciaLogicService.getExperiencia(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getTexto(), ldto.getTexto());
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getTitulo(), ldto.getTitulo());
		Assert.assertEquals(pdto.getFecha(), ldto.getFecha());
        
	}
	
	@Test
	public void deleteExperienciaTest(){
		ExperienciaDTO pdto=data.get(0);
		experienciaLogicService.deleteExperiencia(pdto.getId());
        ExperienciaDTO deleted=experienciaPersistence.getExperiencia(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateExperienciaTest(){
		ExperienciaDTO pdto=data.get(0);
		
		ExperienciaDTO ldto=new ExperienciaDTO();
		ldto.setId(pdto.getId());
		ldto.setTexto(generateRandom(String.class));
		ldto.setName(generateRandom(String.class));
		ldto.setTitulo(generateRandom(String.class));
		ldto.setFecha(generateRandomDate());
		
		
		experienciaLogicService.updateExperiencia(ldto);
		
		
		ExperienciaDTO resp=experienciaPersistence.getExperiencia(pdto.getId());
		
		Assert.assertEquals(ldto.getTexto(), resp.getTexto());	
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getTitulo(), resp.getTitulo());	
		Assert.assertEquals(ldto.getFecha(), resp.getFecha());	
	}
	
	@Test
	public void getExperienciaPaginationTest(){
		
		ExperienciaPageDTO dto1=experienciaLogicService.getExperiencias(1,2);
		Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
		
		
		ExperienciaPageDTO dto2=experienciaLogicService.getExperiencias(2,2);
		Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
		
		for(ExperienciaDTO dto:dto1.getRecords()){
        	boolean found=false;
            for(ExperienciaDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(ExperienciaDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(ExperienciaDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        ExperienciaPageDTO dto3=experienciaLogicService.getExperiencias(1,3);
		Assert.assertNotNull(dto3);
        Assert.assertEquals(dto3.getRecords().size(),data.size());
        Assert.assertEquals(dto3.getTotalRecords().longValue(),data.size());
		
		for(ExperienciaDTO dto:dto3.getRecords()){
        	boolean found=false;
            for(ExperienciaDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	
}