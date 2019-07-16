package com.github.qq44920040.NetWork;

import com.github.qq44920040.sitgod;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.nio.charset.StandardCharsets;

public class ReciveMsg implements PluginMessageListener {

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
        String recive=new String(bytes, StandardCharsets.UTF_8);
        JsonObject jsonObject= (JsonObject) new JsonParser().parse(recive);
        String type=jsonObject.get("sittype").getAsString();
        String name = player.getName();
        if (type.equalsIgnoreCase("true")){
            if (sitgod.sitgodinfo.contains(name)){
                sitgod.sitgodinfo.remove(name);
            }else {
                sitgod.sitgodinfo.add(name);
            }
        }
    }
}
