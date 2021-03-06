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

package co.edu.uniandes.csw.RoyalSystem.tipoestudiante.logic.ejb;

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


import co.edu.uniandes.csw.RoyalSystem.tipoestudiante.logic.dto.TipoEstudiantePageDTO;
import co.edu.uniandes.csw.RoyalSystem.tipoestudiante.logic.dto.TipoEstudianteDTO;
import co.edu.uniandes.csw.RoyalSystem.tipoestudiante.logic.api.ITipoEstudianteLogicService;
import co.edu.uniandes.csw.RoyalSystem.tipoestudiante.persistence.TipoEstudiantePersistence;
import co.edu.uniandes.csw.RoyalSystem.tipoestudiante.persistence.api.ITipoEstudiantePersistence;
import co.edu.uniandes.csw.RoyalSystem.tipoestudiante.persistence.entity.TipoEstudianteEntity;
import co.edu.uniandes.csw.RoyalSystem.tipoestudiante.persistence.converter.TipoEstudianteConverter;
import static co.edu.uniandes.csw.RoyalSystem.util._TestUtil.*;

@RunWith(Arquillian.class)
public class TipoEstudianteLogicServiceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(TipoEstudianteLogicService.class.getPackage())
				.addPackage(ITipoEstudianteLogicService.class.getPackage())
				.addPackage(TipoEstudiantePersistence.class.getPackage())
				.addPackage(TipoEstudianteEntity.class.getPackage())
				.addPackage(ITipoEstudiantePersistence.class.getPackage())
                .addPackage(TipoEstudianteDTO.class.getPackage())
                .addPackage(TipoEstudianteConverter.class.getPackage())
                .addPackage(TipoEstudianteEntity.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private ITipoEstudianteLogicService tipoEstudianteLogicService;
	
	@Inject
	private ITipoEstudiantePersistence tipoEstudiantePersistence;	

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
		List<TipoEstudianteDTO> dtos=tipoEstudiantePersistence.getTipoEstudiantes();
		for(TipoEstudianteDTO dto:dtos){
			tipoEstudiantePersistence.deleteTipoEstudiante(dto.getId());
		}
	}

	private List<TipoEstudianteDTO> data=new ArrayList<TipoEstudianteDTO>();

	private void insertData() {
		for(int i=0;i<3;i++){
			TipoEstudianteDTO pdto=new TipoEstudianteDTO();
			pdto.setName(generateRandom(String.class));
			pdto=tipoEstudiantePersistence.createTipoEstudiante(pdto);
			data.add(pdto);
		}
	}
	
	@Test
	public void createTipoEstudianteTest(){
		TipoEstudianteDTO ldto=new TipoEstudianteDTO();
		ldto.setName(generateRandom(String.class));
		
		
		TipoEstudianteDTO result=tipoEstudianteLogicService.createTipoEstudiante(ldto);
		
		Assert.assertNotNull(result);
		
		TipoEstudianteDTO pdto=tipoEstudiantePersistence.getTipoEstudiante(result.getId());
		
		Assert.assertEquals(ldto.getName(), pdto.getName());	
	}
	
	@Test
	public void getTipoEstudiantesTest(){
		List<TipoEstudianteDTO> list=tipoEstudianteLogicService.getTipoEstudiantes();
		Assert.assertEquals(list.size(), data.size());
        for(TipoEstudianteDTO ldto:list){
        	boolean found=false;
            for(TipoEstudianteDTO pdto:data){
            	if(ldto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getTipoEstudianteTest(){
		TipoEstudianteDTO pdto=data.get(0);
		TipoEstudianteDTO ldto=tipoEstudianteLogicService.getTipoEstudiante(pdto.getId());
        Assert.assertNotNull(ldto);
		Assert.assertEquals(pdto.getName(), ldto.getName());
        
	}
	
	@Test
	public void deleteTipoEstudianteTest(){
		TipoEstudianteDTO pdto=data.get(0);
		tipoEstudianteLogicService.deleteTipoEstudiante(pdto.getId());
        TipoEstudianteDTO deleted=tipoEstudiantePersistence.getTipoEstudiante(pdto.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateTipoEstudianteTest(){
		TipoEstudianteDTO pdto=data.get(0);
		
		TipoEstudianteDTO ldto=new TipoEstudianteDTO();
		ldto.setId(pdto.getId());
		ldto.setName(generateRandom(String.class));
		
		
		tipoEstudianteLogicService.updateTipoEstudiante(ldto);
		
		
		TipoEstudianteDTO resp=tipoEstudiantePersistence.getTipoEstudiante(pdto.getId());
		
		Assert.assertEquals(ldto.getName(), resp.getName());	
	}
	
	@Test
	public void getTipoEstudiantePaginationTest(){
		
		TipoEstudiantePageDTO dto1=tipoEstudianteLogicService.getTipoEstudiantes(1,2);
		Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
		
		
		TipoEstudiantePageDTO dto2=tipoEstudianteLogicService.getTipoEstudiantes(2,2);
		Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
		
		for(TipoEstudianteDTO dto:dto1.getRecords()){
        	boolean found=false;
            for(TipoEstudianteDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(TipoEstudianteDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(TipoEstudianteDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        TipoEstudiantePageDTO dto3=tipoEstudianteLogicService.getTipoEstudiantes(1,3);
		Assert.assertNotNull(dto3);
        Assert.assertEquals(dto3.getRecords().size(),data.size());
        Assert.assertEquals(dto3.getTotalRecords().longValue(),data.size());
		
		for(TipoEstudianteDTO dto:dto3.getRecords()){
        	boolean found=false;
            for(TipoEstudianteDTO pdto:data){
            	if(dto.getId()==pdto.getId()){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	
}