package com.hiberus.university.alexyague.maven.first.pageobjectmodel.support;

import com.hiberus.university.alexyague.maven.first.pageobjectmodel.model.InventoryItem;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TestDataContext {

    private static final List<InventoryItem> inventoryItemListInCart = new ArrayList<>();

    public static void addItem(InventoryItem item) {
        inventoryItemListInCart.add(item);
        log.info("Añadido {} al contexto(carrito)", item);
    }
    public static void removeItem(InventoryItem item) {
        inventoryItemListInCart.remove(item);
        log.info("Eliminado {} del contexto(carrito)", item);
    }

    public static List<InventoryItem> getInventoryItemListInCart(){
        return inventoryItemListInCart;
    }
}
