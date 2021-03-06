package fredboat.command.maintenance;

import fredboat.commandmeta.abs.Command;
import net.dv8tion.jda.JDAInfo;
import net.dv8tion.jda.entities.Guild;
import net.dv8tion.jda.entities.Message;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class VersionCommand extends Command {

    @Override
    public void onInvoke(Guild guild, TextChannel channel, User invoker, Message message, String[] args) {
        channel.sendMessage("JDA Version: " + JDAInfo.VERSION);
    }
    
}
