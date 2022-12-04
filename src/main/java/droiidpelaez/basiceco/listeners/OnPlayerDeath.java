package droiidpelaez.basiceco.listeners;

import droiidpelaez.basiceco.commands.EcoAdminCommands;
import droiidpelaez.basiceco.utils.BankUtils;
import droiidpelaez.basiceco.utils.GoldUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

public class OnPlayerDeath implements Listener {




    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        //Important location: where we'll spawn the gold coin
        Location deathPoint = p.getLocation();
        HashMap<UUID, Double> tempList = BankUtils.listAllBanks();
        //Checking to see if the player has money or not. If not: create an account
        if(!tempList.containsKey(p.getUniqueId())){BankUtils.createBankAccount(p); }
            //Double of your bank, create a gold coin of that value and drop it at deathpoint
            Double lostMoney = tempList.get(p.getUniqueId());

            BankUtils.removeMoney(p, lostMoney);

            ItemStack gold = GoldUtils.getGoldAmount(lostMoney);
            deathPoint.getBlock().getWorld().dropItem(deathPoint, gold);



    }
}
