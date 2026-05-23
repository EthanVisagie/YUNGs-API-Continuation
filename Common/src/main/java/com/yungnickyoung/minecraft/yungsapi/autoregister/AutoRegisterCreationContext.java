package com.yungnickyoung.minecraft.yungsapi.autoregister;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * Thread-local registry-name context used while AutoRegister entries are being constructed.
 * <p>
 * Minecraft 1.21.11 requires {@code setId(...)} to be called on block and item properties before the
 * corresponding object constructor runs. Existing AutoRegister call sites usually provide no-arg suppliers,
 * so this context lets the constructor mixins fill in the registry key without forcing every downstream
 * YUNG's mod to rewrite all of its registrations at once.
 */
public final class AutoRegisterCreationContext {
    private static final ThreadLocal<ResourceKey<Block>> CURRENT_BLOCK_KEY = new ThreadLocal<>();
    private static final ThreadLocal<ResourceKey<Item>> CURRENT_ITEM_KEY = new ThreadLocal<>();

    private AutoRegisterCreationContext() {
    }

    public static <T> T withBlockId(Identifier id, Supplier<T> supplier) {
        ResourceKey<Block> previous = CURRENT_BLOCK_KEY.get();
        CURRENT_BLOCK_KEY.set(ResourceKey.create(Registries.BLOCK, id));
        try {
            return supplier.get();
        } finally {
            restore(CURRENT_BLOCK_KEY, previous);
        }
    }

    public static <T> T withItemId(Identifier id, Supplier<T> supplier) {
        ResourceKey<Item> previous = CURRENT_ITEM_KEY.get();
        CURRENT_ITEM_KEY.set(ResourceKey.create(Registries.ITEM, id));
        try {
            return supplier.get();
        } finally {
            restore(CURRENT_ITEM_KEY, previous);
        }
    }

    public static @Nullable ResourceKey<Block> currentBlockKey() {
        return CURRENT_BLOCK_KEY.get();
    }

    public static @Nullable ResourceKey<Item> currentItemKey() {
        return CURRENT_ITEM_KEY.get();
    }

    private static <T> void restore(ThreadLocal<T> threadLocal, @Nullable T previous) {
        if (previous == null) {
            threadLocal.remove();
        } else {
            threadLocal.set(previous);
        }
    }
}
