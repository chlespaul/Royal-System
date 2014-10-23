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

package co.edu.uniandes.csw.RoyalSystem.pais.logic.ejb;

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


import co.edu.uniandes.csw.RoyalSystem.pais.logic.dto.PaisPageDTO;
import co.edu.uniandes.csw.RoyalSystem.pais.logic.dto.PaisDTO;
import co.edu.uniandes.csw.RoyalSystem.pais.logic.api.IPaisLogicService;
import co.edu.uniandes.csw.RoyalSystem.pais.persistence.PaisPersistence;
import co.edu.uniandes.csw.RoyalSystem.pais.persistence.api.IPaisPersistence;
import co.edu.uniandes.csw.RoyalSystem.pais.persistence.entity.PaisEntity;
import co.edu.uniandes.csw.RoyalSystem.pais.persistence.converter.PaisConverter;
import static co.edu.uniandes.csw.RoyalSystem.util._TestUtil.*;

@RunWith(Arquillian.class)
public class PaisLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(PaisLogicService.class.getPackage())
				.addPackage(IPaisLogicService.class.getPackage())
				.addPackage(PaisPersistence.class.getPackage())
				.addPackage(PaisEntity.class.getPackage())
				.addPackage(IPaisPersistence.class.getPackage())
                .addPackage(PaisDTO.class.getPackage())
                .addPackage(PaisConverter.class.getPackage())
                .addPackage(PaisEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IPaisLogicService paisLogicService;
	
	@Inject
	private IPaisPersistence paisPersistence;	

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
		List<PaisDTO> dtos=paisPersistence.getPaiss();
		for(PaisDTO dto:dtos){
			paisPersistence.deletePais(dto.getId());
		}
	}

	private List<PaisDTO> data=new ArrayList<PaisDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			PaisDTO pdto=new PaisDTO();
			pdto.setName(generateRandom(String.class));
			pdto=paisPersistence.createPais(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createPaisTest(){
		PaisDTO ldto=new PaisDTO();
		ldto.setName(generateRandom(String.class));
		
		
		PaisDTO result=paisLogicService.createPais(ldto);
		
		Assert.assertNotNull(result);
		
		PaisDTO pdto=paisPersistence.getPais(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
	}
	
	@Test
	public void getPaissTest(){
		List<PaisDTO> list=paisLogicService.getPaiss();
		Assert.assertEquals(list.size(), data.size());
        for(PaisDTO ldto:list){
        	boolean found=false;
            for(PaisDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getPaisTest(){
		PaisDTO pdto=data.get(0);
		PaisDTO ldto=paisLogicService.getPais(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
        
	}
	
	@Test
	public void deletePaisTest(){
		PaisDTO pdto=data.get(0);
		paisLogicService.deletePais(pdto.getId());
        PaisDTO deleted=paisPersistence.getPais(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updatePaisTest(){
		PaisDTO pdto=data.get(0);
		
		PaisDTO ldto=new PaisDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		
		
		paisLogicService.updatePais(ldto);
		
		
		PaisDTO resp=paisPersistence.getPais(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
	}
	
	@Test
	public void getPaisPaginationTest(){
		
		PaisPageDTO dto1=paisLogicService.getPaiss(1,2);
		Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
		
		
		PaisPageDTO dto2=paisLogicService.getPaiss(2,2);
		Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
		
		for(PaisDTO dto:dto1.getRecords()){
        	boolean found=false;
            for(PaisDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(PaisDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(PaisDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        PaisPageDTO dto3=paisLogicService.getPaiss(1,3);
		Assert.assertNotNull(dto3);
        Assert.assertEquals(dto3.getRecords().size(),data.size());
        Assert.assertEquals(dto3.getTotalRecords().longValue(),data.size());
		
		for(PaisDTO dto:dto3.getRecords()){
        	boolean found=false;
            for(PaisDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	
}