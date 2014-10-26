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

package co.edu.uniandes.csw.RoyalSystem.archivo.logic.ejb;

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


import co.edu.uniandes.csw.RoyalSystem.archivo.logic.dto.ArchivoPageDTO;
import co.edu.uniandes.csw.RoyalSystem.archivo.logic.dto.ArchivoDTO;
import co.edu.uniandes.csw.RoyalSystem.archivo.logic.api.IArchivoLogicService;
import co.edu.uniandes.csw.RoyalSystem.archivo.persistence.ArchivoPersistence;
import co.edu.uniandes.csw.RoyalSystem.archivo.persistence.api.IArchivoPersistence;
import co.edu.uniandes.csw.RoyalSystem.archivo.persistence.entity.ArchivoEntity;
import co.edu.uniandes.csw.RoyalSystem.archivo.persistence.converter.ArchivoConverter;
import static co.edu.uniandes.csw.RoyalSystem.util._TestUtil.*;

@RunWith(Arquillian.class)
public class ArchivoLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(ArchivoLogicService.class.getPackage())
				.addPackage(IArchivoLogicService.class.getPackage())
				.addPackage(ArchivoPersistence.class.getPackage())
				.addPackage(ArchivoEntity.class.getPackage())
				.addPackage(IArchivoPersistence.class.getPackage())
                .addPackage(ArchivoDTO.class.getPackage())
                .addPackage(ArchivoConverter.class.getPackage())
                .addPackage(ArchivoEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IArchivoLogicService archivoLogicService;
	
	@Inject
	private IArchivoPersistence archivoPersistence;	

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
		List<ArchivoDTO> dtos=archivoPersistence.getArchivos();
		for(ArchivoDTO dto:dtos){
			archivoPersistence.deleteArchivo(dto.getId());
		}
	}

	private List<ArchivoDTO> data=new ArrayList<ArchivoDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			ArchivoDTO pdto=new ArchivoDTO();
			pdto.setName(generateRandom(String.class));
			pdto.setLink(generateRandom(String.class));
			pdto=archivoPersistence.createArchivo(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createArchivoTest(){
		ArchivoDTO ldto=new ArchivoDTO();
		ldto.setName(generateRandom(String.class));
		ldto.setLink(generateRandom(String.class));
		
		
		ArchivoDTO result=archivoLogicService.createArchivo(ldto);
		
		Assert.assertNotNull(result);
		
		ArchivoDTO pdto=archivoPersistence.getArchivo(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
		Assert.assertEquals(ldto.getLink(), pdto.getLink());	
	}
	
	@Test
	public void getArchivosTest(){
		List<ArchivoDTO> list=archivoLogicService.getArchivos();
		Assert.assertEquals(list.size(), data.size());
        for(ArchivoDTO ldto:list){
        	boolean found=false;
            for(ArchivoDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getArchivoTest(){
		ArchivoDTO pdto=data.get(0);
		ArchivoDTO ldto=archivoLogicService.getArchivo(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
		Assert.assertEquals(pdto.getLink(), ldto.getLink());
        
	}
	
	@Test
	public void deleteArchivoTest(){
		ArchivoDTO pdto=data.get(0);
		archivoLogicService.deleteArchivo(pdto.getId());
        ArchivoDTO deleted=archivoPersistence.getArchivo(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateArchivoTest(){
		ArchivoDTO pdto=data.get(0);
		
		ArchivoDTO ldto=new ArchivoDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		ldto.setLink(generateRandom(String.class));
		
		
		archivoLogicService.updateArchivo(ldto);
		
		
		ArchivoDTO resp=archivoPersistence.getArchivo(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
		Assert.assertEquals(ldto.getLink(), resp.getLink());	
	}
	
	@Test
	public void getArchivoPaginationTest(){
		
		ArchivoPageDTO dto1=archivoLogicService.getArchivos(1,2);
		Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
		
		
		ArchivoPageDTO dto2=archivoLogicService.getArchivos(2,2);
		Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
		
		for(ArchivoDTO dto:dto1.getRecords()){
        	boolean found=false;
            for(ArchivoDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(ArchivoDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(ArchivoDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        ArchivoPageDTO dto3=archivoLogicService.getArchivos(1,3);
		Assert.assertNotNull(dto3);
        Assert.assertEquals(dto3.getRecords().size(),data.size());
        Assert.assertEquals(dto3.getTotalRecords().longValue(),data.size());
		
		for(ArchivoDTO dto:dto3.getRecords()){
        	boolean found=false;
            for(ArchivoDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	
}