package dev.tastypommeslul.flattened.datagen;

import dev.tastypommeslul.flattened.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import org.jspecify.annotations.NonNull;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators gen) {
    }

    @Override
    public void generateItemModels(@NonNull ItemModelGenerators gen) {
        gen.generateFlatItem(ModItems.STONE_PEBBLE, ModelTemplates.FLAT_ITEM);
        gen.generateFlatItem(ModItems.IRON_CHUNKLET, ModelTemplates.FLAT_ITEM);
    }
}
