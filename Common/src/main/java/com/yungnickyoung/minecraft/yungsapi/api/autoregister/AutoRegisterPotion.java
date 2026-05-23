package com.yungnickyoung.minecraft.yungsapi.api.autoregister;

import com.yungnickyoung.minecraft.yungsapi.autoregister.AutoRegisterEntry;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

/**
 * Wrapper for registering {@link Potion}s with AutoRegister.
 * <br />
 * Example usage:
 * <pre>
 * {@code
 * @AutoRegister("frost")
 * public static final AutoRegisterPotion FROST_POTION = AutoRegisterPotion
 *        .of(() -> new Potion(new MobEffectInstance(MobEffectModule.FROZEN_EFFECT.getHolder(), 0, 0, false, true, false)));
 * }
 * </pre>
 */
public class AutoRegisterPotion extends AutoRegisterEntry<Potion> {
    private Holder<Potion> holder;

    public static AutoRegisterPotion of(Supplier<Potion> potionSupplier) {
        return new AutoRegisterPotion(potionSupplier);
    }

    /**
     * Compatibility helper for older YUNG's API call sites that supplied only an effect instance.
     */
    public static AutoRegisterPotion mobEffect(MobEffectInstance mobEffectInstance) {
        return mobEffect(() -> mobEffectInstance);
    }

    /**
     * Compatibility helper for lazy effect construction.
     */
    public static AutoRegisterPotion mobEffect(Supplier<MobEffectInstance> mobEffectSupplier) {
        return new AutoRegisterPotion(() -> new Potion("", mobEffectSupplier.get()));
    }

    private AutoRegisterPotion(Supplier<Potion> potionSupplier) {
        super(potionSupplier);
    }

    public Holder<Potion> getHolder() {
        if (holder == null) {
            throw new IllegalStateException("Potion holder is not set. Ensure the Potion is registered before accessing the holder.");
        }
        return holder;
    }

    @ApiStatus.Internal
    public void setHolder(Holder<Potion> holder) {
        this.holder = holder;
    }
}
