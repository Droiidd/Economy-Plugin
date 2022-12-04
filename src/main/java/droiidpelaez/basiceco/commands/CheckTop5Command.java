package droiidpelaez.basiceco.commands;

import droiidpelaez.basiceco.utils.BankUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CheckTop5Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            HashMap<UUID,Double> playerBank = BankUtils.listAllBanks();
            //Think of some sort of sorting algorithm for this. Considered merge sort?

        }






        return true;
    }
}
