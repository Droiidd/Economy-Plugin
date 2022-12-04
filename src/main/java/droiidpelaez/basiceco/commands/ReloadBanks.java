package droiidpelaez.basiceco.commands;

import droiidpelaez.basiceco.utils.BankUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadBanks implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        BankUtils.load2();

        return true;
    }
}
