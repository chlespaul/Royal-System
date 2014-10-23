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

package co.edu.uniandes.csw.RoyalSystem.estadointercambio.persistence;

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


import co.edu.uniandes.csw.RoyalSystem.estadointercambio.logic.dto.EstadoIntercambioPageDTO;
import co.edu.uniandes.csw.RoyalSystem.estadointercambio.logic.dto.EstadoIntercambioDTO;
import co.edu.uniandes.csw.RoyalSystem.estadointercambio.persistence.api.IEstadoIntercambioPersistence;
import co.edu.uniandes.csw.RoyalSystem.estadointercambio.persistence.entity.EstadoIntercambioEntity;
import co.edu.uniandes.csw.RoyalSystem.estadointercambio.persistence.converter.EstadoIntercambioConverter;
import static co.edu.uniandes.csw.RoyalSystem.util._TestUtil.*;

@RunWith(Arquillian.class)
public class EstadoIntercambioPersistenceTest {

	public static final String DEPLOY = "Prueba";

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
				.addPackage(EstadoIntercambioPersistence.class.getPackage())
				.addPackage(EstadoIntercambioEntity.class.getPackage())
				.addPackage(IEstadoIntercambioPersistence.class.getPackage())
                .addPackage(EstadoIntercambioDTO.class.getPackage())
                .addPackage(EstadoIntercambioConverter.class.getPackage())
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
	}

	@Inject
	private IEstadoIntercambioPersistence estadoIntercambioPersistence;

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
		em.createQuery("delete from EstadoIntercambioEntity").executeUpdate();
	}

	private List<EstadoIntercambioEntity> data=new ArrayList<EstadoIntercambioEntity>();

	private void insertData() {
		for(int i=0;i<3;i++){
			EstadoIntercambioEntity entity=new EstadoIntercambioEntity();
			entity.setName(generateRandom(String.class));
			em.persist(entity);
			data.add(entity);
		}
	}
	
	@Test
	public void createEstadoIntercambioTest(){
		EstadoIntercambioDTO dto=new EstadoIntercambioDTO();
		dto.setName(generateRandom(String.class));
		
		EstadoIntercambioDTO result=estadoIntercambioPersistence.createEstadoIntercambio(dto);
		
		Assert.assertNotNull(result);
		
		EstadoIntercambioEntity entity=em.find(EstadoIntercambioEntity.class, result.getId());
		
		Assert.assertEquals(dto.getName(), entity.getName());
	}
	
	@Test
	public void getEstadoIntercambiosTest(){
		List<EstadoIntercambioDTO> list=estadoIntercambioPersistence.getEstadoIntercambios();
		Assert.assertEquals(list.size(), data.size());
        for(EstadoIntercambioDTO dto:list){
        	boolean found=false;
            for(EstadoIntercambioEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
	@Test
	public void getEstadoIntercambioTest(){
		EstadoIntercambioEntity entity=data.get(0);
		EstadoIntercambioDTO dto=estadoIntercambioPersistence.getEstadoIntercambio(entity.getId());
        Assert.assertNotNull(dto);
		Assert.assertEquals(entity.getName(), dto.getName());
        
	}
	
	@Test
	public void deleteEstadoIntercambioTest(){
		EstadoIntercambioEntity entity=data.get(0);
		estadoIntercambioPersistence.deleteEstadoIntercambio(entity.getId());
        EstadoIntercambioEntity deleted=em.find(EstadoIntercambioEntity.class, entity.getId());
        Assert.assertNull(deleted);
	}
	
	@Test
	public void updateEstadoIntercambioTest(){
		EstadoIntercambioEntity entity=data.get(0);
		
		EstadoIntercambioDTO dto=new EstadoIntercambioDTO();
		dto.setId(entity.getId());
		dto.setName(generateRandom(String.class));
		
		
		estadoIntercambioPersistence.updateEstadoIntercambio(dto);
		
		
		EstadoIntercambioEntity resp=em.find(EstadoIntercambioEntity.class, entity.getId());
		
		Assert.assertEquals(dto.getName(), resp.getName());	
	}
	
	@Test
	public void getEstadoIntercambioPaginationTest(){
		//Page 1
		EstadoIntercambioPageDTO dto1=estadoIntercambioPersistence.getEstadoIntercambios(1,2);
        Assert.assertNotNull(dto1);
        Assert.assertEquals(dto1.getRecords().size(),2);
        Assert.assertEquals(dto1.getTotalRecords().longValue(),3L);
        //Page 2
        EstadoIntercambioPageDTO dto2=estadoIntercambioPersistence.getEstadoIntercambios(2,2);
        Assert.assertNotNull(dto2);
        Assert.assertEquals(dto2.getRecords().size(),1);
        Assert.assertEquals(dto2.getTotalRecords().longValue(),3L);
        
        for(EstadoIntercambioDTO dto:dto1.getRecords()){
        	boolean found=false;	
            for(EstadoIntercambioEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
        
        for(EstadoIntercambioDTO dto:dto2.getRecords()){
        	boolean found=false;
            for(EstadoIntercambioEntity entity:data){
            	if(dto.getId().equals(entity.getId())){
                	found=true;
                }
            }
            Assert.assertTrue(found);
        }
	}
	
}