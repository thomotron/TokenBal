package com.gmail.gigatech55.tokenbal;

import java.security.Permission;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

public final class TokenBal extends JavaPlugin{
    @Override
    public void onEnable() {
        getLogger().info("itemToBal is doing a thing!");
    }
    
    @Override
    public void onDisable() {
        getLogger().info("itemToBal is no longer doing a thing!");
    }
    
    Economy econ = null;
    RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
    if (economyProvider != null) {
        econ = economyProvider.getProvider();
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (cmd.getName().equalsIgnoreCase("tb")) {
                //<editor-fold defaultstate="collapsed" desc="Declarations">
                final Player player =  (Player) sender;
                final OfflinePlayer ecoPlayer = (OfflinePlayer) player;
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
                        getLogger().info("Material is PAPER");
                        if (hasLore && hasDisplayName) {
                            getLogger().info("Has LORE and DISPNAME");
                            if ("IOU4IOU".equals(displayName)) {
                                getLogger().info("Display Name is VALID");
                                if (lore.contains("t1")) {
                                    getLogger().info("Got Loretype TYPE 1");
                                    player.setItemInHand(null);
                                    getLogger().info("Item DELETED");
                                    EconomyResponse r = econ.bankDeposit(player.getName(), 50);
                                    getLogger().info("Variable SET");
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
    
//    public Object[] getLore(ItemStack is){
//        ItemMeta m = is.getItemMeta();
//        if(m.getLore() != null){
//            return m.getLore().toArray();
//        }else{
//            return null;
//        }
//    }
    
    
    
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
//                getLogger().info("Action verified as RCLICK");
//                if (i == Material.PAPER) {
//                    getLogger().info("Material is PAPER");
//                    if (hasLore && hasDisplayName) {
//                        getLogger().info("Has LORE and DISPNAME");
//                        if (displayName == "§4IOU") {
//                            getLogger().info("Display Name is VALID");
//                            if (lore.get(0) == "§7$50") {
//                                event.getItem().setType(null);
//                                econ.depositPlayer(player, 50);
//                            } else if (lore.get(0) == "§7$206.25") {
//                                event.getItem().setType(null);
//                                econ.depositPlayer(player, 206.25);
//                            } else if (lore.get(0) == "§7$506.25") {
//                                event.getItem().setType(null);
//                                econ.depositPlayer(player, 506.25);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
    
//    @EventHandler
//    public void onPlayerInvClick(InventoryClickEvent event){
//        final Player player = (Player)event.getWhoClicked();
//        final Inventory inv = player.getInventory();
//        new BukkitRunnable(){
//            public void run(){
//                for(int i = 0; i <= inv.getSize(); i++){
//                    if(inv.getItem(i) != null){
//                        ItemStack itemStack = inv.getItem(i);
//                        ItemStack is = inv.getItem(i);
//                        Material item = inv.getItem(i).getType();
//                        if (item == Material.PAPER) {
//                            if (getLore(itemStack) != null) {
//                                Object[] lore = getLore(itemStack);
//                                if (lore[1] == "§7$50") {
//                                    inv.removeItem(itemStack);
//                                    econ.depositPlayer(player, 50);  
//                                } else if (lore[1] == "§7$206.25") {
//                                    inv.removeItem(itemStack);
//                                    econ.depositPlayer(player, 206.25);
//                                } else if (lore[1] == "§7$506.25") {
//                                    inv.removeItem(itemStack);
//                                    econ.depositPlayer(player, 506.25);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }.runTaskLater(this, 5);
//    }
}