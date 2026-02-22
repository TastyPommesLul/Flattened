package dev.tastypommeslul.flattened.block.custom;

import com.mojang.serialization.MapCodec;
import dev.tastypommeslul.flattened.container.CraftingContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

public class CraftingNetBlock extends CraftingTableBlock {
    public static final VoxelShape SHAPE = Block.column(16, 0, 4);
    public static final MapCodec<CraftingNetBlock> CODEC = simpleCodec(CraftingNetBlock::new);
    public CraftingNetBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NonNull InteractionResult useWithoutItem(@NonNull BlockState state, Level level, @NonNull BlockPos pos, @NonNull Player player, @NonNull BlockHitResult hitResult) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(level, pos));
            player.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            return InteractionResult.CONSUME;
        }
    }

    @Override
    protected @NonNull MenuProvider getMenuProvider(@NonNull BlockState state, @NonNull Level level, @NonNull BlockPos pos) {
        return new SimpleMenuProvider((id, inventory, player) ->
                new CraftingContainer(id, inventory, ContainerLevelAccess.create(level, pos), this),
                Component.translatable("block.flattened.crafting_net")
        );
    }

    @Override
    protected @NonNull VoxelShape getShape(@NonNull BlockState state, @NonNull BlockGetter level, @NonNull BlockPos pos, @NonNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    public @NonNull MapCodec<? extends CraftingTableBlock> codec() {
        return CODEC;
    }
}
