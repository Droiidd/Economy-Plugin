package droiidpelaez.basiceco.commands;

import droiidpelaez.basiceco.utils.BankUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class CheckBalanceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            HashMap<String,Double> playerBank = BankUtils.listAllBanks();
            if(playerBank.containsKey(p.getUniqueId().toString()) != true){ BankUtils.createBankAccount(p);}
            else

            p.sendMessage(p.getDisplayName()+"'s balance  :  $"+ playerBank.get(p.getUniqueId().toString()));
        }
        return true;
    }
}
