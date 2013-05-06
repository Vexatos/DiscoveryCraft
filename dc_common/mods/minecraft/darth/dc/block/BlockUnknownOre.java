package mods.minecraft.darth.dc.block;

import mods.minecraft.darth.dc.DiscoveryCraft;
import mods.minecraft.darth.dc.lib.Strings;

import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;

public class BlockUnknownOre extends BlockDC
{

    public BlockUnknownOre(int id)
    {
        super(id, Material.rock);
        this.setUnlocalizedName(Strings.UNKNOWN_ORE_NAME)
            .setCreativeTab(DiscoveryCraft.tabsDC)
            .setHardness(8F)
            .setResistance(80)
            .setLightValue(0.2F)
            .setLightOpacity(4)
            .setStepSound(soundStoneFootstep);
        
    }

}
