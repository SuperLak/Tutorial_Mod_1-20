package net.kal.tutorial_mod.item.custom;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {
        // code inside only runs server-side
        if (!pContext.getLevel().isClientSide()) {
            Player player = pContext.getPlayer();

            if (player != null) {
                BlockPos blockClicked = pContext.getClickedPos();
                // used to damage item
                ItemStack itemstack = player.getItemInHand(pContext.getHand());
                boolean foundBlock = false;

                for (int i = 0; i <= blockClicked.getY() + 64; i++) {
                    BlockState state = pContext.getLevel().getBlockState(blockClicked.below(i));

                    if (isValuableBlock(state)) {
                        outputValuableCoordinates(blockClicked.below(i), player, state.getBlock());
                        foundBlock = true;

                        break;
                    }
                }

                // makes sure the player always receives feedback
                if (!foundBlock) {
                    player.sendSystemMessage(Component.literal("No valuable blocks were found"));
                }

                // damages item for using it
                itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(pContext.getHand()));
            }

        }

        return InteractionResult.SUCCESS;
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(Blocks.IRON_ORE) || state.is(Blocks.DIAMOND_ORE);
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at (" +
                blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"));
    }
}
