package dev.tastypommeslul.flattened.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Block;

public class ModToolMaterials {
    public static final ToolMaterial HATCHET_MATERIAL = toolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL,
            64,
            6.0f,
            1.0f,
            5,
            ItemTags.STONE_TOOL_MATERIALS
    );


    //region method
    public static ToolMaterial toolMaterial(
            TagKey<Block> incorrectBlocksForDrops,
            int durability,
            float speed,
            float attackDamageBonus,
            int enchantmentValue,
            TagKey<Item> repairItems
    ) {
        return new ToolMaterial(
                incorrectBlocksForDrops,
                durability,
                speed,
                attackDamageBonus,
                enchantmentValue,
                repairItems
        );
    }
    //endregion

    public static void init() {}
}
