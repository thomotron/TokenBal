package com.gmail.gigatech55.tokenbal;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

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
    //END STATIC VAR DECS
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (cmd.getName().equalsIgnoreCase("tb")) {
                //<editor-fold defaultstate="collapsed" desc="Declarations">
                final Player player =  (Player) sender;
                Material i = player.getItemInHand().getType();
                int count = player.getItemInHand().getAmount();
                boolean hasLore = player.getItemInHand().getItemMeta().hasLore();
                boolean hasDisplayName = player.getItemInHand().getItemMeta().hasDisplayName();
                if (hasLore && hasDisplayName) {
                    List<String> rawLore = player.getItemInHand().getItemMeta().getLore();
                    String stringLore = (String) rawLore.get(0);
                    String lore = stringLore.replaceAll("§","");
                    String rawDisplayName = player.getItemInHand().getItemMeta().getDisplayName();
                    String displayName = rawDisplayName.replaceAll("§","");

                    getLogger().info(rawLore.get(0));
                    getLogger().info(stringLore);
                    getLogger().info(lore);
                    getLogger().info(rawDisplayName);
                    getLogger().info(displayName);
                    //</editor-fold>
                    if (i == Material.PAPER) {
                        getLogger().info("Material is PAPER"); //Debug
                        if (hasLore && hasDisplayName) {
                            getLogger().info("Has LORE and DISPNAME"); //Debug
                            if ("IOU4IOU".equals(displayName)) {
                                getLogger().info("Display Name is VALID"); //Debug
                                if (lore.contains("t1")) {
                                    getLogger().info("Got Loretype TYPE 1"); //Debug
                                    player.setItemInHand(null);
                                    getLogger().info("Item DELETED"); //Debug
                                    EconomyResponse r = econ.depositPlayer(player, 50*count);
                                    getLogger().info("Variable SET"); //Debug
                                    if (r.transactionSuccess()) {
                                        player.sendMessage("You have redeemed $" + 50*count);
                                    }
                                        return true;
                                }
                                if (lore.contains("t2")) {
                                    getLogger().info("Got Loretype TYPE 2");
                                    player.setItemInHand(null);
                                    EconomyResponse r = econ.depositPlayer(player, 206.25*count);
                                    if (r.transactionSuccess()) {
                                        player.sendMessage("You have redeemed $" + 206.25*count);
                                    }
                                    return true;
                                }
                                if (lore.contains("t3")) {
                                    getLogger().info("Got Loretype TYPE 3");
                                    player.setItemInHand(null);
                                    EconomyResponse r = econ.depositPlayer(player, 506.25*count);
                                    if (r.transactionSuccess()) {
                                        player.sendMessage("You have redeemed $" + 506.25*count);
                                    }
                                    return true;
                                }
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