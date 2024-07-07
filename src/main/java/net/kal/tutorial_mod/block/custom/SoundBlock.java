package net.kal.tutorial_mod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class SoundBlock extends Block {
    public SoundBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState pState, @NotNull Level pLevel,
                                                        @NotNull BlockPos pPos, @NotNull Player pPlayer,
                                                        @NotNull BlockHitResult pHitResult) {
        pLevel.playSound(pPlayer, pPos, SoundEvents.ALLAY_DEATH, SoundSource.BLOCKS, 1, 1);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level pLevel, @NotNull BlockPos pPos, @NotNull BlockState pState, @NotNull Entity pEntity) {
        pLevel.playSound(pEntity, pPos, SoundEvents.ALLAY_DEATH, SoundSource.BLOCKS, 1, 1);
    }
}
