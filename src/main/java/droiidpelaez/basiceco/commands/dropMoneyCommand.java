package droiidpelaez.basiceco.commands;

import droiidpelaez.basiceco.utils.GlobalMethods;
import droiidpelaez.basiceco.utils.GoldUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class dropMoneyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            Double dropAmount = GlobalMethods.checkPlayerStrToD(args[0], p);
            ItemStack goldAmount = GoldUtils.getGoldItem(dropAmount);
            Bukkit.getServer().getWorld("Midland").dropItem(p.getLocation(), goldAmount);



        }





        return true;
    }
}
