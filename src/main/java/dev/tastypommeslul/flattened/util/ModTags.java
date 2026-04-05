package dev.tastypommeslul.flattened.util;

import dev.tastypommeslul.flattened.Flattened;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public interface ModTags {
    class Items {
        public static final TagKey<Item> REPAIRS_LOWER_STONE = register("repairs_low_stone");


        private static TagKey<Item> register(String name) {
            return TagKey.create(BuiltInRegistries.ITEM.key(), Flattened.id(name));
        }
    }
    class Blocks {

        private static TagKey<Block> register(String name) {
            return TagKey.create(BuiltInRegistries.BLOCK.key(), Flattened.id(name));
        }
    }
}
