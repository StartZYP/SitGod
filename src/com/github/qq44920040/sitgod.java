package com.github.qq44920040;

import com.github.qq44920040.NetWork.ReciveMsg;
import com.github.qq44920040.NetWork.SendMsg;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


import java.util.HashSet;
import java.util.Set;

public class sitgod extends JavaPlugin implements Listener {
    public static Set<String> sitgodinfo = new HashSet<>();
    public static Plugin plugin;
    @Override
    public void onEnable() {
        getServer().getMessenger().registerIncomingPluginChannel(this,"sitgod", new ReciveMsg());
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "sitgod");
        getServer().getPluginManager().registerEvents(this,this);
        plugin=this;

        new BukkitRunnable(){
            @Override
            public void run() {
                for (String PlayerNmae:sitgodinfo){
                    Player player = Bukkit.getServer().getPlayer(PlayerNmae);
                    if (player.getHealth()!=player.getMaxHealth()){
                        double v = player.getMaxHealth() / 10;
                        player.setHealth(player.getHealth()+v);
                    }
                }
            }
        }.runTaskTimer(this,20L,20L*3);
        new BukkitRunnable(){
            @Override
            public void run() {
                for (Player player:Bukkit.getOnlinePlayers()){
                    SendMsg.SendPlayersSitInfo(player);
                }
            }
        }.runTaskTimer(this,20L,20L);
        super.onEnable();
    }

    @EventHandler
    public void PlayerQuitGameevent(PlayerQuitEvent event){
        String name = event.getPlayer().getName();
        sitgodinfo.remove(name);
    }

    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event){
            Location from = event.getFrom();
            if (from.getZ() != event.getTo().getZ() && from.getX() != event.getTo().getX()&&sitgodinfo.contains(event.getPlayer().getName())) {
                event.getPlayer().sendMessage("§e§l打坐取消");
                String name = event.getPlayer().getName();
                sitgodinfo.remove(name);
            }
    }
}
