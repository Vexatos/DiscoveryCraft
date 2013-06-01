package mods.minecraft.darth.dc.core.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

import mods.minecraft.darth.dc.lib.Reference;

//Note for self - levels: SEVERE, WARNING, INFO, FINE, FINER, FINEST, OFF

public class LogUtil
{

    //DiscoveryCraft's own log instance
    private static Logger dcLogger = Logger.getLogger(Reference.MOD_NAME);
    
    public static void init()
    {
        dcLogger.setParent(FMLLog.getLogger());
    }
    
    public static void log(Level logLevel, String msg)
    {
        dcLogger.log(logLevel, msg);
    }
}