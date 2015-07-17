package f0H3pl4Vgg6;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;


class Arena
{
    // There's got to be a better way to tell what arena a player is in than iterating through every one!
    private Map<Player, Boolean> players = new HashMap<>();
    boolean hasPlayer(Player pl)
    {
        return players.keySet().contains(pl);
    }
    synchronized void addPlayer(Player p)
    {
        players.put(p, true);
    }
    synchronized void removePlayer(Player p)
    {
        players.remove(p);
    }


    void sendChatMessage(Player pl, String msg)
    {
        // Grey out the name if they're dead
        String msgFormatted = (players.get(pl) ? ChatColor.WHITE : ChatColor.GRAY) + pl.getName() + ChatColor.WHITE + ": " + msg;
        // This has to be a clusterfuck of a line because we need to synchronise the sendMessage command, else threading errors will pop up
        players.forEach((p, d) -> Plugin.pl.getServer().getScheduler().runTask(Plugin.pl, () -> p.sendMessage(msgFormatted)));
    }


}
