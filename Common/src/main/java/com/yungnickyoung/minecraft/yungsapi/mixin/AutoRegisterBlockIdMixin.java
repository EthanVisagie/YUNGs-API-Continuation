package com.yungnickyoung.minecraft.yungsapi.mixin;

import com.yungnickyoung.minecraft.yungsapi.autoregister.AutoRegisterCreationContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockBehaviour.class)
public abstract class AutoRegisterBlockIdMixin {
    @Inject(method = "<init>", at = @At("HEAD"))
    private void yungsapi_applyAutoRegisterBlockId(BlockBehaviour.Properties properties, CallbackInfo ci) {
        ResourceKey<Block> key = AutoRegisterCreationContext.currentBlockKey();
        if (key != null) {
            properties.setId(key);
        }
    }
}
