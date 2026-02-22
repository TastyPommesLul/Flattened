package dev.tastypommeslul.flattened.client;

import dev.tastypommeslul.flattened.block.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public class FlattenedClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlock(ModBlocks.CRAFTING_NET, ChunkSectionLayer.TRANSLUCENT);
    }
}
