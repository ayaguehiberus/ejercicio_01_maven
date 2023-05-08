package com.hiberus.university.alexyague.maven.first.pageobjectmodel.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {

    public static List<Integer> randomNumbers(int min, int max, int cant){

        if (max - min + 1 > cant || min > max){
            throw new IllegalArgumentException("Invalid range");
        }

        Set<Integer> set = new Random().ints(min, max)
                .distinct()
                .limit(cant)
                .boxed()
                .collect(Collectors.toSet());
        List<Integer> listaRandom = new ArrayList<>(set);
        return listaRandom;
    }
}
