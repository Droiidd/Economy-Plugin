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

public final class BasicEco extends JavaPlugin {
    public static BasicEco getPlugin() {
        return plugin;
    }

    private static BasicEco plugin;

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

        BankUtils.load2();



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        try {
            BankUtils.saveBank();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
