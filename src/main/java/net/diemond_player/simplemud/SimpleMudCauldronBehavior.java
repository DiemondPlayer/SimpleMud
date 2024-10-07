package net.diemond_player.simplemud;

import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ItemActionResult;


import static net.minecraft.block.cauldron.CauldronBehavior.WATER_CAULDRON_BEHAVIOR;

public class SimpleMudCauldronBehavior {
    public static void registerCauldronBehaviors(){
        WATER_CAULDRON_BEHAVIOR.map().put(Items.DIRT, DAMPEN_DIRT);
        WATER_CAULDRON_BEHAVIOR.map().put(Items.ROOTED_DIRT, DAMPEN_DIRT);
        WATER_CAULDRON_BEHAVIOR.map().put(Items.COARSE_DIRT, DAMPEN_DIRT);
    }

    public static final CauldronBehavior DAMPEN_DIRT = (state, world, pos, player, hand, stack) -> {
        if (!world.isClient) {
            ItemStack itemStack = new ItemStack(Items.MUD);
            itemStack.setCount(stack.getCount());
            player.setStackInHand(hand, itemStack);
            player.incrementStat(Stats.USE_CAULDRON);
            LeveledCauldronBlock.decrementFluidLevel(state, world, pos);
        }else{
            world.playSound(player, pos, SoundEvents.BLOCK_MUD_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
        return ItemActionResult.success(world.isClient);
    };
}
