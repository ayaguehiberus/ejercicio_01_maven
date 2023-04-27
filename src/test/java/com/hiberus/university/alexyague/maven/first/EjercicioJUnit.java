package com.hiberus.university.alexyague.maven.first;

import org.junit.*;

public class EjercicioJUnit {

    @BeforeClass
    public static  void setUpClass() {
        System.out.println("BEFORE CLASS");
    }

    @Before
    public void setUp(){
        System.out.println("BEFORE");
    }

    @Test
    public void test1(){
        System.out.println("TEST 1");

        String[] nombresEsperados = {"java", "junit", "jboss"};
        String[] nombresActuales = {"java", "junit", "jboss"};

        Assert.assertEquals("Los objetos no son iguales", nombresEsperados, nombresActuales);

    }

    @Test
    public void test2(){
        System.out.println("TEST 2");
        Assert.assertEquals("El resultado no es correcto", 1, (1+1-1));
    }

    @Test
    public void test3(){
        System.out.println("TEST 3");
        Assert.assertFalse("Fallo de validación", true);
    }

    @After
    public void tearDown(){
        System.out.println("AFTER");
    }

    @AfterClass
    public static void tearDownClass(){
        System.out.println("AFTER CLASS");
    }
}
