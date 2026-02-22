package dev.tastypommeslul.flattened.item;

import dev.tastypommeslul.flattened.Flattened;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

import java.util.function.Function;

public class ModItems {
    public static final Item STONE_PEBBLE = register("stone_pebble", Item::new, new Item.Properties().stacksTo(16));
    public static final Item IRON_CHUNKLET = register("iron_chunklet", Item::new, new Item.Properties());

    private static <GenericItem extends Item> GenericItem register(String name, @NonNull Function<Item.Properties, GenericItem> itemFunction, Item.@NonNull Properties properties) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Flattened.id(name));

        GenericItem item = itemFunction.apply(properties.setId(itemKey));

        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static void init() {}
}
