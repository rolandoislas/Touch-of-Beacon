package com.rolandoislas.touchofbeacon;

import com.rolandoislas.touchofbeacon.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TouchOfBacon.MODID, version = TouchOfBacon.VERSION, name = TouchOfBacon.NAME)
public class TouchOfBacon {
    public static final String MODID = "touchofbeacon";
    static final String VERSION = "1.0";
    static final String NAME = "Touch of Beacon";
    @Mod.Instance(MODID)
    public static TouchOfBacon instance;
    @SidedProxy(clientSide = "com.rolandoislas.touchofbeacon.proxy.ClientProxy",
            serverSide = "com.rolandoislas.touchofbeacon.proxy.CommonProxy")
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
