package com.github.qq44920040.NetWork;

import com.github.qq44920040.sitgod;
import org.bukkit.entity.Player;

import java.nio.charset.Charset;

import static com.github.qq44920040.sitgod.sitgodinfo;

public class SendMsg {
    public static void SendPlayersSitInfo(Player player){
        StringBuilder stringBuilder =new StringBuilder();
        stringBuilder.append("{\"Sitinfo\":\"");
        for (String sitinfo:sitgodinfo){
                stringBuilder.append(sitinfo+",");
        }
        stringBuilder.append("\"}");
        player.sendPluginMessage(sitgod.plugin,"sitgod",stringBuilder.toString().getBytes(Charset.forName("GBK")));
    }

}
