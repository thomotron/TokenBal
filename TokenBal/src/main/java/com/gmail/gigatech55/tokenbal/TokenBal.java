package com.gmail.gigatech55.tokenbal;

import static java.lang.Double.parseDouble;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.economy.Economy;

public final class TokenBal extends JavaPlugin{
    @Override
    public void onEnable() {
        getLogger().info("TokenBal hasn't destroyed your server (yet)!");
    }
    
    @Override
    public void onDisable() {
        getLogger().info("Err... Why, may I ask?");
    }
    
    //START STATIC VAR DECS
    static Economy econ = null;
    
    public double getTokenAmt(String meta) {
        String stringMeta = (String) meta.replaceAll("§","");
        stringMeta = stringMeta.replace("v","");
        stringMeta = stringMeta.replace("7","");
        stringMeta = stringMeta.replace("$","");
        Double amount = parseDouble(stringMeta);
        return amount;
    }
    //END STATIC VAR DECS
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (cmd.getName().equalsIgnoreCase("tb")) {
                //<editor-fold defaultstate="collapsed" desc="Declarations">
                final Player player =  (Player) sender;
                Material item = player.getItemInHand().getType();
                int count = player.getItemInHand().getAmount();
                boolean hasLore = player.getItemInHand().getItemMeta().hasLore();
                boolean hasDisplayName = player.getItemInHand().getItemMeta().hasDisplayName();
                if (hasLore && hasDisplayName) {
                    List<String> rawLore = player.getItemInHand().getItemMeta().getLore();
                    String lore = (String) rawLore.get(0);
                    String rawDisplayName = player.getItemInHand().getItemMeta().getDisplayName();
                    String displayName = rawDisplayName.replaceAll("§","");
                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Debug">
                    getLogger().info(rawLore.get(0));
                    getLogger().info(lore);
                    getLogger().info(rawDisplayName);
                    getLogger().info(displayName);
                //</editor-fold>
                    if (item == Material.PAPER) {
                        if (hasLore && hasDisplayName) {
                            if ("IOU4IOU".equals(displayName)) {
                                for (int i = count; i < 1; i--) {
                                    econ.depositPlayer(player,getTokenAmt(lore));
                                }
                                player.sendMessage("§a$" + getTokenAmt(lore)*count + " §chas been added to your account automagically.");
                            } else {
                            }
                        }
                    }
                } else {
                    sender.sendMessage("This is not a token!");
                    return true;
                }
            }
            return true;
        } else {
            sender.sendMessage("Hold up there, bud! Way too many arguments for me to handle!");
        }
        return false;
    }
    
//OLD CODE THAT IM TOO AFRAID TO DELETE.
//IM A HOARDER LIKE THAT.
//
//    public final class handListener implements Listener {
//        @EventHandler
//        public void onInteract(PlayerInteractEvent event) {                 
//            //<editor-fold defaultstate="collapsed" desc="Declarations">
//            Material i = event.getMaterial();
//            boolean hasLore = event.getItem().getItemMeta().hasLore();
//            boolean hasDisplayName = event.getItem().getItemMeta().hasDisplayName();
//            List<String> lore = event.getItem().getItemMeta().getLore();
//            String displayName = event.getItem().getItemMeta().getDisplayName();
//            final Player player = (Player) event.getPlayer();
//            //</editor-fold>
//            getLogger().info("Action recognised");
//            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
//                getLogger().info("Action verified as RCLICK(Any type)");
//                
//                -->INSERT CODE FROM 'MATERIAL.PAPER' CHECK<--
//
//            }
//        }
//    }
}
