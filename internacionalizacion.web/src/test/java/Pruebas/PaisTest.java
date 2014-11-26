/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pruebas;
    
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
 

/**
 * @author RoyalSystem
 */

public class PaisTest {
 
    private static WebDriver driver;
    private static String baseUrl;
    private static boolean acceptNextAlert = true;
    private static StringBuffer verificationErrors = new StringBuffer();
 
    @BeforeClass
    public static void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 
    }
 
    @Before
    public void setUpUrl() {
        driver.get(baseUrl + "/internacionalizacion.web/pais.html");
    }
 
    @Test
    public void testCreatePais() throws Exception {
 
        /**
         * Comando que realiza click sobre el boton "create" del toolbar. 
         */
        //driver.findElement(By.xpath("//button[contains(@id,'createButton')]")).click();
        driver.findElement(By.xpath("//button[contains(@id,'create')]")).click();

        /**
         * Comando que duerme el Thread del test por 2 segundos para dejar que
         * el efecto 'slide down' de backbone abra el formulario de createSport.
         */
        Thread.sleep(2000);
 
        /**
         * Comando que busca el elemento del formulario 'name' en el html actual.
         * Posteriormente limpia su contenido (comando clean).
         */
        driver.findElement(By.id("name")).clear();
        /**
         * Comando que simula la escritura de un valor en el elemento(sendKeys)
         * con el String de parámetro sobre // el elemento encontrado.
         */
        driver.findElement(By.id("name")).sendKeys("Alemania");
 
        /*
        driver.findElement(By.id("minAge")).clear();
        driver.findElement(By.id("minAge")).sendKeys("123123");
        driver.findElement(By.id("maxAge")).clear();
        driver.findElement(By.id("maxAge")).sendKeys("123123");
        */

        /**
         * Comando que encuentra y hace clic sobre el boton "Save" del toolbar
         * (una vez mas encontrado por una expresión Xpath)
         */
        driver.findElement(By.xpath("//button[contains(@id,'save')]")).click();

       
        Thread.sleep(2000);
        /**
         * Comando que obtiene el div azul de creación exitosa. Si se obtiene,
         * la prueba va bien, si no, saldrá un error y la prueba quedará como
         * fállida.
         */
        WebElement dialog = driver.findElement(By.xpath("//div[contains(@style,'display: block;')]"));
        /**
         * Comando que obtiene la tabla con el elemento que se creó
         * anteriormente.
         */
        List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table striped')]/tbody/tr"));
        boolean sucess = false;
        /**
         * Se itera sobre los elementos de la tabla para ver buscando si el nuevo
         * elemento creado está en la lista
         */
        for (WebElement webElement : table) {
            List<WebElement> elems = webElement.findElements(By.xpath("td"));
 
            if (elems.get(0).getText().equals("Alemania")  )   //Primera columna
                 /*   && elems.get(1).getText().equals("123123")
                    && elems.get(2).getText().equals("123123")) */
                 {
                /**
                 * si se encuentra la fila, la variable 'success' pasa a true,
                 * indicando que el elemento creado esta en la lista.
                 */
                sucess = true;
            }
 
        }
        /**
         * la prueba es exitosa si se encontró el dialogo de creación exitosa y
         * el nuevo elemento está en la lista.
         */
        assertTrue(dialog != null && sucess);
    }
 
    //Test para Edicion de datos
    @Test
    public void testUpdatePais() throws Exception {
        /**
         * Se hace clic en el vinculo "Edit" del primer elemento de la lista de
         * paises
         */
        driver.findElement(By.linkText("Edit")).click();
        Thread.sleep(2000);
        /**
         * Se realiza el mismo proceso de diligenciamento de los campos con los
         * cambios
         */
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("Francia");
        /*driver.findElement(By.id("minAge")).clear();
        driver.findElement(By.id("minAge")).sendKeys("12");
        driver.findElement(By.id("maxAge")).clear();
        driver.findElement(By.id("maxAge")).sendKeys("16");*/
        driver.findElement(By.xpath("//button[contains(@id,'save')]")).click();
        Thread.sleep(2000);
        /**
         * Se verifica que en la lista de respuesta hallan aparecido los cambios
         * en el elemento y también el mensaje de edición exitosa.
         */
        WebElement dialog = driver.findElement(By.xpath("//div[contains(@style,'display: block;')]"));
        List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table striped')]/tbody/tr"));
        boolean fail = false;
        for (WebElement webElement : table) {
            List<WebElement> elems = webElement.findElements(By.xpath("td"));
 
            if (elems.get(0).getText().equals("Francia") 
                    /*&& elems.get(1).getText().equals("12") && elems.get(2).getText().equals("16")*/) {
                fail = true;
            }
 
        }
        assertTrue(dialog != null && fail);
    }
 
    //Test para ver si borro bien
    @Test
    public void testDeletePais() throws Exception {
        /**
         * Se hace clic en el vinculo "Delete" del primer elemento de la lista
         * de sports
         */
        driver.findElement(By.linkText("Delete")).click();
        Thread.sleep(2000);
        /**
         * Se verifica que en la lista el elemento halla desaparecido. Si
         * existe, hubo un error.
         */
        try {
            List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table striped')]/tbody/tr"));
            boolean fail = false;
            for (WebElement webElement : table) {
                List<WebElement> elems = webElement.findElements(By.xpath("td"));
 
                if (elems.get(0).getText().equals("Francia") /*&& elems.get(1).getText().equals("12") && elems.get(2).getText().equals("16")*/) {
                    fail = true;
                }
 
            }
 
            WebElement dialog = driver.findElement(By.xpath("//div[contains(@style,'display: block;')]"));
            assertTrue(dialog != null && !fail);
        } catch (Exception e) {
            assertTrue(true);
        }
 
    }
 
    //test para ver si lista bien
    @Test
    public void testListPaises() throws Exception {
        /**
         * Se crea un deporte con el test createSport
         */
        testCreatePais();
        /**
         * Se hace clic en el botón "refresh" del toolbar para obtener la lista.
         */
        driver.findElement(By.xpath("//button[contains(@id,'refresh')]")).click();

        Thread.sleep(2000);
        /**
         * Se verifica que el elemento creado anteriormente existe en la lista.
         */
        List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table striped')]/tbody/tr"));
        boolean fail = false;
        for (WebElement webElement : table) {
            List<WebElement> elems = webElement.findElements(By.xpath("td"));
 
            if (elems.get(0).getText().equals("123123") /*&& elems.get(1).getText().equals("123123") && elems.get(2).getText().equals("123123")*/) {
                fail = true;
            }
 
        }
        assertTrue(fail);
    }
 
    //test para saber si valida bien o no. En este caso deberia sacar un error por el campo vacio
    @Test
    public void testValidatePaises() throws Exception {
        //Se crea un nuevo sport
        driver.findElement(By.xpath("//button[contains(@id,'create')]")).click();
        /**
         * Se limpian los campos para disparar la validación de "campos nulos"
         */
        driver.findElement(By.id("name")).clear();
        /*
        driver.findElement(By.id("minAge")).clear();
 
        driver.findElement(By.id("maxAge")).clear();*/
        
        //Se hace clic en "Save" del toolbar
        driver.findElement(By.xpath("//button[contains(@id,'save')]")).click();
        Thread.sleep(2000);
        /**
         * Debe aparecer el mensaje de error, y adicional, se debe mantener la
         * vista de creación (la cual, si la creación del elemento pasara la
         * validación, cambiaría a la lista de sports) La prueba falla si estas
         * condiciones no se cumplen.
         */
        WebElement dialog = driver.findElement(By.xpath("//div[contains(@style,'display: block;')]"));
        List<WebElement> table = driver.findElements(By.xpath("//table[contains(@class,'table striped')]/tbody/tr"));
        boolean fail = false;
        try {
            for (WebElement webElement : table) {
                List<WebElement> elems = webElement.findElements(By.xpath("td"));
 
                if (elems.get(0).getText().equals("Alemania") /*&& elems.get(1).getText().equals("123123") && elems.get(2).getText().equals("123123")*/) {
                    fail = true;
                }
 
            }
        } catch (Exception e) {
        }
 
        assertTrue(dialog != null && !fail);
    }
 
    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
 
    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
 
    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
 
    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}    
    

