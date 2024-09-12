package me.fortibrine.regions;

import me.fortibrine.regions.api.RegionsApi;
import org.bukkit.plugin.java.JavaPlugin;

public class RegionsPlugin extends JavaPlugin {

    private RegionsApi regionsApi;

    @Override
    public void onEnable() {
        regionsApi = new RegionsApi(this);
    }

}
