package net.director.jnb.item;

import net.director.jnb.JNBRenewed;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup JNB_RENEWED_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(JNBRenewed.MOD_ID,"jnb-renewed"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.jnb-renewed"))
                    .icon(() -> new ItemStack(ModItems.D_COIN)).entries((displayContext, entries) -> {
                        entries.add(ModItems.D_COIN);



                    }).build());


    public static void registerItemGroups() {
        JNBRenewed.LOGGER.info("Registering Item Groups for " + JNBRenewed.MOD_ID);
    }
}
