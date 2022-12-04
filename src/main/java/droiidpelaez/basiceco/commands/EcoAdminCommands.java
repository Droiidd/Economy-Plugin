package droiidpelaez.basiceco.commands;


import droiidpelaez.basiceco.utils.BankUtils;
import droiidpelaez.basiceco.utils.GlobalMethods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class EcoAdminCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            //If the entered /eco -> Show the usage
            if(args.length == 0){
                p.sendMessage("========================================");
                p.sendMessage("Usage:\n/eco add {player} {amount}  :  Add funds to a player\n/eco remove {player} {amount}  :  removes funds from a player\n/eco reset  :  resets the entire economy.");
                p.sendMessage("========================================");
                return true;
            }
            String add = "add";
            String remove = "remove";
            String reset = "reset";
            // CASE : add
            if(args[0].toLowerCase().compareTo(add) == 0){
                //Handles the /eco add ____ case : null player input
                if(args.length < 3 || args.length > 3){
                    p.sendMessage(ChatColor.RED+ "Incorrect usage, please try: "+ ChatColor.GRAY+"/eco add {Player} {Amount}");
                    return true;
                }
                //This saves a player object of the target.
                Player target = Bukkit.getServer().getPlayer(args[1]);
                //This handles offline players, or misspelled players
                if(target == null){ p.sendMessage(ChatColor.GRAY+"This player is not online."); return true;}

                Double deposit = GlobalMethods.checkPlayerStrToD(args[2], target);
                BankUtils.updateBalance(target, deposit);

            }
            // CASE : remove
            else if(args[0].toLowerCase().compareTo(remove) == 0){
                if(args.length < 3 || args.length > 3){
                    p.sendMessage(ChatColor.RED+ "Incorrect usage, please try: "+ ChatColor.GRAY+"/eco remove {Player} {Amount}");
                    return true;
                }
                Player target = Bukkit.getServer().getPlayerExact(args[1]);
                HashMap<UUID, Double> bankList = BankUtils.listAllBanks();

                if(target == null){ p.sendMessage(ChatColor.GRAY+"This player is not online."); return true;  }

                if(bankList.containsKey(target.getUniqueId()) == false){ BankUtils.createBankAccount(target); }
                Double withdrawal = GlobalMethods.checkPlayerStrToD(args[2], target);
                Double original = bankList.get(target.getUniqueId());
                p.sendMessage("Attempting to withdrawal : $"+(withdrawal)+"...");

                if(original - withdrawal < 0){ p.sendMessage(ChatColor.RED+"Attempted to remove too many funds!"); }
                else{
                    BankUtils.removeMoney(target, withdrawal);
                }
            }
            // CASE : reset
            else if(args[0].toLowerCase().compareTo(reset) == 0){
                if(args.length == 1){ p.sendMessage("tes pret"); BankUtils.deleteAllAct();  p.sendMessage("test after"); return true;}
//                Player target = Bukkit.getServer().getPlayerExact(args[1]);
//                if(target == null){ p.sendMessage(ChatColor.GRAY+"This player is not online."); return true;}
//                BankUtils.deleteAccount(target);
            }

        }

        return true;
    }


}
