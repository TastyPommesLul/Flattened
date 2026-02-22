package dev.tastypommeslul.flattened.datagen;

import dev.tastypommeslul.flattened.block.ModBlocks;
import dev.tastypommeslul.flattened.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class EnglishProvider extends FabricLanguageProvider {
    public EnglishProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.@NonNull Provider provider, @NonNull TranslationBuilder builder) {
        builder.add(ModItems.IRON_CHUNKLET, "Iron Chunklet");
        builder.add(ModItems.STONE_PEBBLE, "Stone Pebble");
        builder.add(ModBlocks.CRAFTING_NET, "Crafting Net");
    }
}
