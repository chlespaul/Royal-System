/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pruebas;

/**
 * @author Royal System
 */
//El único import que cambia es el de la clase que va a probar.
import co.edu.uniandes.csw.RoyalSystem.materiauniandes.logic.dto.MateriaUniandesDTO;
import co.edu.uniandes.csw.RoyalSystem.materiauniandes.service.MateriaUniandesService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import javax.inject.Inject;

@RunWith(Arquillian.class)
public class MateriaUniandesServiceTest {
 
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addClass(MateriaUniandesService.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    // "constructor" de la clase que va a probar
    @Inject
    MateriaUniandesService materia;

 //Puede poner tantos métodos como quiera, con tal de que empieze con @Test
    @Test
    public void deberiaNoEncontrar_getIntercambios() {
        Assert.assertTrue(materia.getIntercambios(999999,"materia Ficticia","estado").getRecords().isEmpty());
    }
    
    @Test
    public void deberiaEncontrar_getIntercambios() {
        
        MateriaUniandesDTO nuevaMateria = new MateriaUniandesDTO();
        nuevaMateria.setCodigo("987654");
        nuevaMateria.setName("materia de verdad");
        materia.createMateriaUniandes(nuevaMateria);
        Assert.assertTrue(materia.getIntercambios(987654,"materia de verdad","estado").getRecords().contains(nuevaMateria));
    }

}
