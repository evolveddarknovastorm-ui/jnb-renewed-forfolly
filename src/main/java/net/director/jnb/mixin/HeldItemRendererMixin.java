package net.director.jnb.mixin;

import net.director.jnb.item.ModItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GameRenderer.class)
public class HeldItemRendererMixin {

    @ModifyVariable(
            method = "showFloatingItem",
            at = @At("HEAD"),
            argsOnly = true
    )
    private ItemStack swapFloatingTotemTexture(ItemStack stack) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (player != null) {
            for (Hand hand : Hand.values()) {
                if (player.getStackInHand(hand).isOf(ModItems.D_COIN)) {
                    return new ItemStack(ModItems.D_COIN);
                }
            }
        }

        return stack;
    }
}
