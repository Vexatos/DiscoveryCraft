package mods.minecraft.darth.dc.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import mods.minecraft.darth.dc.DiscoveryCraft;
import mods.minecraft.darth.dc.entity.projectile.EntityKnifeProjectile;
import mods.minecraft.darth.dc.lib.Strings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemKnife extends ItemDC
{
	boolean melee;
    public ItemKnife(int id, boolean melee)
    {
        super(id);
        this.setCreativeTab(DiscoveryCraft.tabDC)
            .setUnlocalizedName(Strings.KNIFE_NAME);
        this.melee = melee;
        
        if(melee)
        {
        	this.setMaxStackSize(1)
    		.setMaxDamage(3);
        }else{
        	
        	this.setMaxStackSize(8);
        }
    }
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	if(!melee)
    	{
	        boolean flag = par3EntityPlayer.capabilities.isCreativeMode;
	
	        if (flag || par3EntityPlayer.inventory.hasItem(ModItems.knifeThrowing.itemID))
	        {
	
	            EntityKnifeProjectile entityknife = new EntityKnifeProjectile(par2World, par3EntityPlayer, 1.0F);
	            if(!flag){
	            	--par1ItemStack.stackSize;
	            	entityknife.canBePickedUp = 1;
	            }
	            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F);
	            
	            
	            
	            
	
	            entityknife.setDamage(1.5D);
	
	            if (!par2World.isRemote)
	            {
	                par2World.spawnEntityInWorld(entityknife);
	            }
	        }
	        return par1ItemStack;
    	}else{
    		return par1ItemStack;
    	}
    }
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    @SideOnly(Side.CLIENT) //Adds lore to screwdriver
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    	if(melee){
    		par3List.add("�cMelee");
    	}else{
    		par3List.add("�bThrowing");
    	}
        
    }
}
