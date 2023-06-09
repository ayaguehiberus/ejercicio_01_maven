package com.hiberus.university.alexyague.maven.first.pageobjectmodel.utils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
public class Utils {

    public static List<Integer> randomNumbers(int min, int max, int cant){

        if (max - min + 1 < cant || min > max){
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
//    public static float priceStringToFloat(String precio){
//        String precioString = precio.replace("Item total: ", "");
//        precioString = precioString.replace("$","");
//        return Float.parseFloat(precioString);
//    }
    public static float priceStringToFloat(String price){
        final String regex = "([0-9]+)(\\.|,)?([0-9]+)";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(price);

        if (matcher.find()) {
            String precioFinded = matcher.group().replace(",",".");
            log.info("Precio encontrado, extraido, y parseado");
            return Float.parseFloat(precioFinded);
        }
        log.info("No se pudo encontrar un valor que coincida con un precio");
        return -1;
    }
    public static List<Float> stringListToFloat(List<String> list){
        List<Float> newList = new ArrayList<>();
        for (String elem:
             list) {
            newList.add(priceStringToFloat(elem));
        }
        return newList;
    }
    public static List<String> floatListToString(List<Float> list){
        List<String> newList = new ArrayList<>();
        for (float elem:
                list) {
            newList.add(String.valueOf(elem));
        }
        return newList;
    }
    public static float sumarFloatList(List<Float> lista){
        float total = 0;
        for (Float elem:
             lista) {
            total += elem;
        }
        return total;
    }
    public static int getListItemPosition(String name, List<WebElement> lista){
        int cont = 0;
        if (lista.size() < 1){
            return -1;
        }
        log.info("Recorriendo lista...");
        for (WebElement elem:
                lista) {
            if (elem.getText().equalsIgnoreCase(name)){
                return cont;
            }
            cont++;
        }
        return -1;
    }
    public static List<String> getTextOfWebElements(List<WebElement> lista){
        ArrayList<String> nuevaLista = new ArrayList<>();
        try {
            for (WebElement elem : lista) {
                nuevaLista.add(elem.getText());
            }
            nuevaLista.replaceAll(String::toLowerCase);
        } catch (NullPointerException npe){
            System.out.println("ERROR: La lista de WebElements proporcionada está vacía");
        }

        return nuevaLista;
    }
    public static List<WebElement> getCopyOfWebElemList(List<WebElement> lista){
//        ArrayList<WebElement> list = new ArrayList<>(lista);
//        return list.clone();
        return lista.stream().collect(Collectors.toList());
    }
    public static List<String> getCopyOfStringList(List<String> lista){
        return lista.stream().collect(Collectors.toList());
    }
    public static List<Float> getCopyOfFloatList(List<Float> lista){
        return lista.stream().collect(Collectors.toList());
    }

    public static List<String> sortListAlphabetical(List<String> lista){
        List<String> newList = getCopyOfStringList(lista);
        newList.replaceAll(String::toLowerCase);
        Collections.sort(newList);
        return newList;
    }

    public static List<String> sortListInverseAlphabetical(List<String> lista){
        List<String> newList = getCopyOfStringList(lista);
        newList.replaceAll(String::toLowerCase);
        Collections.sort(newList, Collections.reverseOrder());
        return newList;
    }

    public static List<Float> sortListLoHi(List<Float> lista){
        List<Float> newList = getCopyOfFloatList(lista);
        Collections.sort(newList);
        return newList;
    }

    public static List<Float> sortListHiLo(List<Float> lista){
        List<Float> newList = getCopyOfFloatList(lista);
        Collections.sort(newList, Collections.reverseOrder());
        return newList;
    }

}
