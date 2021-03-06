package mods.minecraft.darth.dc;


import java.io.File;

import mods.minecraft.darth.dc.block.ModBlocks;
import mods.minecraft.darth.dc.command.CommandHandler;
import mods.minecraft.darth.dc.configuration.ConfigHandler;
import mods.minecraft.darth.dc.core.handlers.AddonHandler;
import mods.minecraft.darth.dc.core.handlers.FuelHandler;
import mods.minecraft.darth.dc.core.handlers.LocalizationHandler;
import mods.minecraft.darth.dc.core.proxy.CommonProxy;
import mods.minecraft.darth.dc.core.util.LogUtil;
import mods.minecraft.darth.dc.creativetabs.CreativeTabDC;
import mods.minecraft.darth.dc.event.EventRegistry;
import mods.minecraft.darth.dc.item.ModItems;
import mods.minecraft.darth.dc.lib.Reference;
import mods.minecraft.darth.dc.lib.Strings;
import mods.minecraft.darth.dc.network.PacketHandler;
import mods.minecraft.darth.dc.recipe.ModRecipes;
import mods.minecraft.darth.dc.world.WorldInit;

import net.minecraft.creativetab.CreativeTabs;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLFingerprintViolationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;


/**
 * DiscoveryCraft
 * 
 * @author Darth_Feder
 * 
 * 
 */


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, acceptedMinecraftVersions = Reference.MC_VERSIONS, modExclusionList = Reference.EXCLUSION_MODS)
@NetworkMod(channels = {Reference.CHANNEL_NAME}, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class DiscoveryCraft
{

        @Instance(Reference.MOD_ID)
        public static DiscoveryCraft instance;
        
        @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
        public static CommonProxy proxy;
        
        
        public static CreativeTabs tabDC = new CreativeTabDC(Reference.MOD_ID);

        
        @EventHandler
        public void invalidFingerprint(FMLFingerprintViolationEvent event)
        {
            LogUtil.severe(Strings.INVALID_FINGERPRINT_WARNING);
        }
        
        
        @EventHandler
        public void serverStarting(FMLServerStartingEvent event)
        {
            
            //Initialize Commands
            CommandHandler.initCommands(event);
            
        }
        
        
        @EventHandler
        public void preInit(FMLPreInitializationEvent event)
        {
        	
            //Register Logger
            LogUtil.init();
            
            //Load Language Localization Files
            LocalizationHandler.loadLanguages();
            
            //Load the Configuration File
            ConfigHandler.init(new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.MOD_NAME + ".cfg"));
            
            //Register Key Bindings (Client-Side)
            //proxy.registerKeyBindingHandler();
            
            //Register Sound Handler (Client-Side)
            proxy.registerSoundHandler();
            
            //Initialize Blocks
            ModBlocks.init();
            
            //Initialize Items
            ModItems.init();
            
            //Initialize Mod Recipes
            ModRecipes.init();
            
        }
    
    
        @EventHandler
        public void load(FMLInitializationEvent event)
        {
            
            //Register World Additions
            WorldInit.init();
            
            //Register GUIs
        	NetworkRegistry.instance().registerGuiHandler(instance, proxy);
            
            //Register Events
            EventRegistry.init(event);
            
            //Register Tile Entities
            proxy.registerTileEntities();
            
            //Init Rendering (Client-Side)
            proxy.initRenderingAndTextures();
            
            //Client guis (Client-Side)
            proxy.registerGuiEvent();
            
            //Register Fuel Handler
            GameRegistry.registerFuelHandler(new FuelHandler());
            
        }
        
        
        @EventHandler
        public void postInit(FMLPostInitializationEvent event)
        {

            //Initialize Mod Addons
            AddonHandler.init();
            
        }
    
}
