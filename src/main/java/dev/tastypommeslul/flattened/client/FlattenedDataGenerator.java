package dev.tastypommeslul.flattened.client;

import dev.tastypommeslul.flattened.datagen.EnglishProvider;
import dev.tastypommeslul.flattened.datagen.ModBlockLootTableProvider;
import dev.tastypommeslul.flattened.datagen.ModModelProvider;
import dev.tastypommeslul.flattened.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class FlattenedDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModBlockLootTableProvider::new);
        pack.addProvider(EnglishProvider::new);
    }
}
