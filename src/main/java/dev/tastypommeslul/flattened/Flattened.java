package dev.tastypommeslul.flattened;

import dev.tastypommeslul.flattened.block.ModBlocks;
import dev.tastypommeslul.flattened.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.advancements.criterion.DataComponentMatchers;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.core.component.predicates.DataComponentPredicates;
import net.minecraft.core.component.predicates.EnchantmentsPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Flattened implements ModInitializer {
    public static final String MOD_ID = "flattened";
    @Override
    public void onInitialize() {
        ModItems.init();
        ModBlocks.init();

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
            entries.accept(ModItems.STONE_PEBBLE);
            entries.accept(ModItems.IRON_CHUNKLET);
        });

        LootTableEvents.MODIFY.register((resourceKey, builder, lootTableSource, provider) -> {
            if (Blocks.DIRT.getLootTable().isEmpty()) return;
            if (Blocks.SHORT_GRASS.getLootTable().isEmpty()) return;
            if (Blocks.TALL_GRASS.getLootTable().isEmpty()) return;
            if (Blocks.DIRT.getLootTable().get().equals(resourceKey)) {
                LootPool.Builder lootPool = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.25f))
                        .with(LootItem.lootTableItem(ModItems.STONE_PEBBLE).setWeight(3).build())
                        .with(LootItem.lootTableItem(ModItems.IRON_CHUNKLET).setWeight(1).build());
                builder.withPool(lootPool);
            }
            if (Blocks.SHORT_GRASS.getLootTable().get().equals(resourceKey) || Blocks.TALL_GRASS.getLootTable().get().equals(resourceKey)) {
                LootPool.Builder lootPool = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .when(LootItemRandomChanceCondition.randomChance(0.1075f))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(BuiltInRegistries.ITEM, Items.AIR)))
                        .when(InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().withComponents(DataComponentMatchers.Builder
                                .components().partial(DataComponentPredicates.ENCHANTMENTS,
                                        EnchantmentsPredicate.enchantments(
                                                List.of(new EnchantmentPredicate(provider.lookupOrThrow(Registries.ENCHANTMENT)
                                                        .getOrThrow(Enchantments.SILK_TOUCH), MinMaxBounds.Ints.atLeast(1)))))
                                                .build()
                                        )
                                )
                        ))
                        .with(LootItem.lootTableItem(Items.BAMBOO).setWeight(20).build())
                        .with(LootItem.lootTableItem(Items.SWEET_BERRIES).setWeight(10).build())
                        .with(LootItem.lootTableItem(Items.CARROT).setWeight(7).build())
                        .with(LootItem.lootTableItem(Items.OAK_SAPLING).setWeight(5).build())
                        .with(LootItem.lootTableItem(Items.SUGAR_CANE).setWeight(1).build());
                builder.withPool(lootPool);
            }
        });
        AtomicInteger count = new AtomicInteger();
        PlayerBlockBreakEvents.AFTER.register((level, player, blockPos, blockState, blockEntity) -> {
            if (blockState.getBlock().equals(Blocks.SHORT_GRASS) ||  blockState.getBlock().equals(Blocks.TALL_GRASS)) {
                count.getAndIncrement();
                player.displayClientMessage(Component.literal("Broken: " + count.get()), true);
            }
        });
    }

    public static Identifier id(String name) {
        return Identifier.fromNamespaceAndPath(MOD_ID, name);
    }
}
