package com.hiberus.university.alexyague.maven.first;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        ArrayList<String> pruebas = new ArrayList<>();
        pruebas.add("cosas");
        pruebas.add("random");
        pruebas.add("pelele");
        pruebas.add("adios");
        ArrayList<String> pruebas2 = new ArrayList<>(pruebas);

        System.out.println("Array Pruebas");
        iterarArray(pruebas);
        System.out.println("Array pruebas ordenado inversamente");
        pruebas = sortListInverseAlphabetical(pruebas);
        iterarArray(pruebas);
        System.out.println("Array pruebas 2 (copia de pruebas 1)");
        iterarArray(pruebas2);
        System.out.println();
        System.out.println("Comparación");
        System.out.println("Metodo equals: " + pruebas.equals(pruebas2));
        Boolean resul = CollectionUtils.isEqualCollection(pruebas, pruebas2);
        System.out.println("isEqualCollection: " + resul);
        System.out.println("isEqualList: " + ListUtils.isEqualList(pruebas, pruebas2));

    }

    public static ArrayList<String> sortListInverseAlphabetical(ArrayList<String> lista){
        ArrayList<String> nuevaLista = (ArrayList<String>) lista.clone();
        nuevaLista.replaceAll(String::toLowerCase);
        Collections.sort(nuevaLista, Collections.reverseOrder());
        return nuevaLista;
    }

//    public static ArrayList<Integer>

    public static void iterarArray(ArrayList<String> lista){
        for (String elem :
             lista) {
            System.out.println(elem);
        }
    }
}
