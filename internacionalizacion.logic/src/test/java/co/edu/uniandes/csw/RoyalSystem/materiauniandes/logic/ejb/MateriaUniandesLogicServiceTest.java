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

package co.edu.uniandes.csw.RoyalSystem.materiauniandes.logic.ejb;

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


import co.edu.uniandes.csw.RoyalSystem.materiauniandes.logic.dto.MateriaUniandesPageDTO;
import co.edu.uniandes.csw.RoyalSystem.materiauniandes.logic.dto.MateriaUniandesDTO;
import co.edu.uniandes.csw.RoyalSystem.materiauniandes.logic.api.IMateriaUniandesLogicService;
import co.edu.uniandes.csw.RoyalSystem.materiauniandes.persistence.MateriaUniandesPersistence;
import co.edu.uniandes.csw.RoyalSystem.materiauniandes.persistence.api.IMateriaUniandesPersistence;
import co.edu.uniandes.csw.RoyalSystem.materiauniandes.persistence.entity.MateriaUniandesEntity;
import co.edu.uniandes.csw.RoyalSystem.materiauniandes.persistence.converter.MateriaUniandesConverter;
import static co.edu.uniandes.csw.RoyalSystem.util._TestUtil.*;

@RunWith(Arquillian.class)
public class MateriaUniandesLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(MateriaUniandesLogicService.class.getPackage())
				.addPackage(IMateriaUniandesLogicService.class.getPackage())
				.addPackage(MateriaUniandesPersistence.class.getPackage())
				.addPackage(MateriaUniandesEntity.class.getPackage())
				.addPackage(IMateriaUniandesPersistence.class.getPackage())
                .addPackage(MateriaUniandesDTO.class.getPackage())
                .addPackage(MateriaUniandesConverter.class.getPackage())
                .addPackage(MateriaUniandesEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IMateriaUniandesLogicService materiaUniandesLogicService;
	
	@Inject
	private IMateriaUniandesPersistence materiaUniandesPersistence;	

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
		List<MateriaUniandesDTO> dtos=materiaUniandesPersistence.getMateriaUniandess();
		for(MateriaUniandesDTO dto:dtos){
			materiaUniandesPersistence.deleteMateriaUniandes(dto.getId());
		}
	}

	private List<MateriaUniandesDTO> data=new ArrayList<MateriaUniandesDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			MateriaUniandesDTO pdto=new MateriaUniandesDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setCodigo(generateRandom(String.class));
			pdto=materiaUniandesPersistence.createMateriaUniandes(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createMateriaUniandesTest(){
		MateriaUniandesDTO ldto=new MateriaUniandesDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setCodigo(generateRandom(String.class));
		
		
		MateriaUniandesDTO result=materiaUniandesLogicService.createMateriaUniandes(ldto);
		
		Assert.assertNotNull(result);
		
		MateriaUniandesDTO pdto=materiaUniandesPersistence.getMateriaUniandes(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getCodigo(), pdto.getCodigo());	
	}
	
	@Test
	public void getMateriaUniandessTest(){
		List<MateriaUniandesDTO> list=materiaUniandesLogicService.getMateriaUniandess();
		Assert.assertEquals(list.size(), data.size());
        for(MateriaUniandesDTO ldto:list){
        	boolean found=false;
            for(MateriaUniandesDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getMateriaUniandesTest(){
		MateriaUniandesDTO pdto=data.get(0);
		MateriaUniandesDTO ldto=materiaUniandesLogicService.getMateriaUniandes(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getCodigo(), ldto.getCodigo());
        
	}
	
	@Test
	public void deleteMateriaUniandesTest(){
		MateriaUniandesDTO pdto=data.get(0);
		materiaUniandesLogicService.deleteMateriaUniandes(pdto.getId());
        MateriaUniandesDTO deleted=materiaUniandesPersistence.getMateriaUniandes(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateMateriaUniandesTest(){
		MateriaUniandesDTO pdto=data.get(0);
		
		MateriaUniandesDTO ldto=new MateriaUniandesDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setCodigo(generateRandom(String.class));
		
		
		materiaUniandesLogicService.updateMateriaUniandes(ldto);
		
		
		MateriaUniandesDTO resp=materiaUniandesPersistence.getMateriaUniandes(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getCodigo(), resp.getCodigo());	
	}
	
	@Test
	public void getMateriaUniandesPaginationTest(){
		
		MateriaUniandesPageDTO dto1=materiaUniandesLogicService.getMateriaUniandess(1,2);
		Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
		
		
		MateriaUniandesPageDTO dto2=materiaUniandesLogicService.getMateriaUniandess(2,2);
		Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
		
		for(MateriaUniandesDTO dto:dto1.getRecords()){
        	boolean found=false;
            for(MateriaUniandesDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(MateriaUniandesDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(MateriaUniandesDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        MateriaUniandesPageDTO dto3=materiaUniandesLogicService.getMateriaUniandess(1,3);
		Assert.assertNotNull(dto3);
        Assert.assertEquals(dto3.getRecords().size(),data.size());
        Assert.assertEquals(dto3.getTotalRecords().longValue(),data.size());
		
		for(MateriaUniandesDTO dto:dto3.getRecords()){
        	boolean found=false;
            for(MateriaUniandesDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	
}