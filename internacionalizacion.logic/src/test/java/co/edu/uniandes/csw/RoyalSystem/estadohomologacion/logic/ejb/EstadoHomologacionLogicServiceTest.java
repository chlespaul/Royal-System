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

package co.edu.uniandes.csw.RoyalSystem.estadohomologacion.logic.ejb;

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


import co.edu.uniandes.csw.RoyalSystem.estadohomologacion.logic.dto.EstadoHomologacionPageDTO;
import co.edu.uniandes.csw.RoyalSystem.estadohomologacion.logic.dto.EstadoHomologacionDTO;
import co.edu.uniandes.csw.RoyalSystem.estadohomologacion.logic.api.IEstadoHomologacionLogicService;
import co.edu.uniandes.csw.RoyalSystem.estadohomologacion.persistence.EstadoHomologacionPersistence;
import co.edu.uniandes.csw.RoyalSystem.estadohomologacion.persistence.api.IEstadoHomologacionPersistence;
import co.edu.uniandes.csw.RoyalSystem.estadohomologacion.persistence.entity.EstadoHomologacionEntity;
import co.edu.uniandes.csw.RoyalSystem.estadohomologacion.persistence.converter.EstadoHomologacionConverter;
import static co.edu.uniandes.csw.RoyalSystem.util._TestUtil.*;

@RunWith(Arquillian.class)
public class EstadoHomologacionLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(EstadoHomologacionLogicService.class.getPackage())
				.addPackage(IEstadoHomologacionLogicService.class.getPackage())
				.addPackage(EstadoHomologacionPersistence.class.getPackage())
				.addPackage(EstadoHomologacionEntity.class.getPackage())
				.addPackage(IEstadoHomologacionPersistence.class.getPackage())
                .addPackage(EstadoHomologacionDTO.class.getPackage())
                .addPackage(EstadoHomologacionConverter.class.getPackage())
                .addPackage(EstadoHomologacionEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IEstadoHomologacionLogicService estadoHomologacionLogicService;
	
	@Inject
	private IEstadoHomologacionPersistence estadoHomologacionPersistence;	

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
		List<EstadoHomologacionDTO> dtos=estadoHomologacionPersistence.getEstadoHomologacions();
		for(EstadoHomologacionDTO dto:dtos){
			estadoHomologacionPersistence.deleteEstadoHomologacion(dto.getId());
		}
	}

	private List<EstadoHomologacionDTO> data=new ArrayList<EstadoHomologacionDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			EstadoHomologacionDTO pdto=new EstadoHomologacionDTO();
			pdto.setName(generateRandom(String.class));
			pdto=estadoHomologacionPersistence.createEstadoHomologacion(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createEstadoHomologacionTest(){
		EstadoHomologacionDTO ldto=new EstadoHomologacionDTO();
		ldto.setName(generateRandom(String.class));
		
		
		EstadoHomologacionDTO result=estadoHomologacionLogicService.createEstadoHomologacion(ldto);
		
		Assert.assertNotNull(result);
		
		EstadoHomologacionDTO pdto=estadoHomologacionPersistence.getEstadoHomologacion(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
	}
	
	@Test
	public void getEstadoHomologacionsTest(){
		List<EstadoHomologacionDTO> list=estadoHomologacionLogicService.getEstadoHomologacions();
		Assert.assertEquals(list.size(), data.size());
        for(EstadoHomologacionDTO ldto:list){
        	boolean found=false;
            for(EstadoHomologacionDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getEstadoHomologacionTest(){
		EstadoHomologacionDTO pdto=data.get(0);
		EstadoHomologacionDTO ldto=estadoHomologacionLogicService.getEstadoHomologacion(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
        
	}
	
	@Test
	public void deleteEstadoHomologacionTest(){
		EstadoHomologacionDTO pdto=data.get(0);
		estadoHomologacionLogicService.deleteEstadoHomologacion(pdto.getId());
        EstadoHomologacionDTO deleted=estadoHomologacionPersistence.getEstadoHomologacion(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateEstadoHomologacionTest(){
		EstadoHomologacionDTO pdto=data.get(0);
		
		EstadoHomologacionDTO ldto=new EstadoHomologacionDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		
		
		estadoHomologacionLogicService.updateEstadoHomologacion(ldto);
		
		
		EstadoHomologacionDTO resp=estadoHomologacionPersistence.getEstadoHomologacion(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
	}
	
	@Test
	public void getEstadoHomologacionPaginationTest(){
		
		EstadoHomologacionPageDTO dto1=estadoHomologacionLogicService.getEstadoHomologacions(1,2);
		Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
		
		
		EstadoHomologacionPageDTO dto2=estadoHomologacionLogicService.getEstadoHomologacions(2,2);
		Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
		
		for(EstadoHomologacionDTO dto:dto1.getRecords()){
        	boolean found=false;
            for(EstadoHomologacionDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(EstadoHomologacionDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(EstadoHomologacionDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        EstadoHomologacionPageDTO dto3=estadoHomologacionLogicService.getEstadoHomologacions(1,3);
		Assert.assertNotNull(dto3);
        Assert.assertEquals(dto3.getRecords().size(),data.size());
        Assert.assertEquals(dto3.getTotalRecords().longValue(),data.size());
		
		for(EstadoHomologacionDTO dto:dto3.getRecords()){
        	boolean found=false;
            for(EstadoHomologacionDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	
}