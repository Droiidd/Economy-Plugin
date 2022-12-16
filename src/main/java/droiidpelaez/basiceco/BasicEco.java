package droiidpelaez.basiceco;

import droiidpelaez.basiceco.commands.CheckBalanceCommand;
import droiidpelaez.basiceco.commands.EcoAdminCommands;
import droiidpelaez.basiceco.commands.GivePlayerGold;
import droiidpelaez.basiceco.commands.ReloadBanks;
import droiidpelaez.basiceco.listeners.OnPlayerDeath;
import droiidpelaez.basiceco.listeners.OnPlayerPickUp;
import droiidpelaez.basiceco.utils.BankUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class BasicEco extends JavaPlugin {
    public static BasicEco getPlugin() {
        return plugin;
    }

    private static BasicEco plugin;
    private static HashMap<String, Double> map = BankUtils.listAllBanks();


    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        getCommand("balance").setExecutor(new CheckBalanceCommand());
        getCommand("eco").setExecutor(new EcoAdminCommands());
        getCommand("giveGold").setExecutor(new GivePlayerGold());
        getCommand("rebank").setExecutor(new ReloadBanks());
        getServer().getPluginManager().registerEvents(new OnPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerPickUp(), this);

        //BankUtils.load2();
        this.saveDefaultConfig();
        if(this.getConfig().contains("data")){
            this.restoreFile();
        }
    }

    public void saveFile(){
        for(Map.Entry<String, Double> entry : map.entrySet()){
            this.getConfig().set("data."+entry.getKey(), entry.getValue());
        }
        this.saveConfig();

    }
    public void restoreFile(){
        this.getConfig().getConfigurationSection("data").getKeys(false).forEach(key ->{
            Double account = (Double) this.getConfig().get("data."+key);
            map.put(key, account);
        });

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if(!map.isEmpty()){
            this.saveFile();
        }
    }
}
