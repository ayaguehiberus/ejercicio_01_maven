package com.hiberus.university.alexyague.maven.first.JUnit_Ejercicios;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Login.class,
        Inventario.class
})

public class RunTest {


}