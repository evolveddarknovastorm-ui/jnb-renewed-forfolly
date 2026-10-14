package net.director.jnb.item;

import net.director.jnb.JNBRenewed;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {
    public static final Item D_COIN = registerItem( "d_coin", new Item(new FabricItemSettings().maxCount(1)));

    private static void addItemsToIngredientsItemGroup(FabricItemGroupEntries entries) {
        entries.add(D_COIN);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(JNBRenewed.MOD_ID, name), item);
    }

    public static void registerModItems() {
        JNBRenewed.LOGGER.info("Registering Mod Items for " + JNBRenewed.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModItems::addItemsToIngredientsItemGroup);
    }
}
