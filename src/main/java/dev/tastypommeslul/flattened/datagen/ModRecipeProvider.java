package dev.tastypommeslul.flattened.datagen;

import dev.tastypommeslul.flattened.block.ModBlocks;
import dev.tastypommeslul.flattened.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider provider, @NonNull RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {
                shapeless(RecipeCategory.MISC, Items.COBBLESTONE)
                        .unlockedBy(getHasName(ModItems.STONE_PEBBLE), has(ModItems.STONE_PEBBLE))
                        .requires(ModItems.STONE_PEBBLE, 4)
                        .save(recipeOutput);
                shapeless(RecipeCategory.MISC, Items.RAW_IRON)
                        .unlockedBy(getHasName(ModItems.IRON_CHUNKLET), has(ModItems.IRON_CHUNKLET))
                        .requires(ModItems.IRON_CHUNKLET, 3)
                        .save(recipeOutput);
                shaped(RecipeCategory.MISC, ModBlocks.CRAFTING_NET)
                        .unlockedBy(getHasName(Items.BAMBOO), has(Items.BAMBOO))
                        .pattern("SC")
                        .pattern("CS")
                        .define('C', Items.STRING)
                        .define('S', Items.STICK)
                        .save(recipeOutput);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "FlattenedRecipeProvider";
    }
}
