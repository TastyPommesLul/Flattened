package dev.tastypommeslul.flattened.datagen;

import dev.tastypommeslul.flattened.item.ModItems;
import dev.tastypommeslul.flattened.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        valueLookupBuilder(ModTags.Items.REPAIRS_LOWER_STONE)
                .add(Items.COBBLESTONE)
                .add(Items.STONE)
                .add(ModItems.STONE_PEBBLE);
    }
}
