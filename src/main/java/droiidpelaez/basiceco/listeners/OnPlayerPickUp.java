package droiidpelaez.basiceco.listeners;

import droiidpelaez.basiceco.utils.BankUtils;
import droiidpelaez.basiceco.utils.GoldUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;



public class OnPlayerPickUp implements Listener {

    //MAKE A GLOBAL UTILS CLASS FOR ALL OF THESE METHODS
    public Double checkInput(String s, Player p){
        try{
            Double amount = Double.parseDouble(s);
            return amount;
        }catch(NumberFormatException e){
            p.sendMessage(ChatColor.RED+ "Incorrect usage "+ ChatColor.GRAY+"please enter a valid amount.");
        }
        return 0.0;
    }

    @EventHandler
    public void onPlayerPickUp(PlayerPickupItemEvent e){
        Player p = e.getPlayer();
        //Creating a gold Item to compare th epicked up item to
        ItemStack testGold = GoldUtils.getGoldAmount(0.0);
        //Ensuring its a gold coin and not something else
        if(e.getItem().getItemStack().getType().equals(testGold.getType())){
            //remove the coin to avoid duping money
            e.getItem().remove();
            e.setCancelled(true);

            //Clones the coin to avoid damage, then converts its name to a readable double. ()
            ItemStack checkIfGold = e.getItem().getItemStack();
            String amount = ChatColor.stripColor(checkIfGold.getItemMeta().getDisplayName());
            String numberOnly= amount.replaceAll("[^0-9]", "");
            Double depositGold  = (checkInput(numberOnly,p))/10;

            //Confirm pick up and give the player their money
            p.sendMessage(ChatColor.GRAY+ "You picked up "+ChatColor.GOLD +depositGold+"g");
            BankUtils.updateBalance(p, depositGold);

        }

    }



}
