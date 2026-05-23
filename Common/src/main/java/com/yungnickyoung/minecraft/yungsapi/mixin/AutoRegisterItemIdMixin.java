package com.yungnickyoung.minecraft.yungsapi.mixin;

import com.yungnickyoung.minecraft.yungsapi.autoregister.AutoRegisterCreationContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public abstract class AutoRegisterItemIdMixin {
    @Inject(method = "<init>", at = @At("HEAD"))
    private void yungsapi_applyAutoRegisterItemId(Item.Properties properties, CallbackInfo ci) {
        ResourceKey<Item> key = AutoRegisterCreationContext.currentItemKey();
        if (key != null) {
            properties.setId(key);
        }
    }
}
