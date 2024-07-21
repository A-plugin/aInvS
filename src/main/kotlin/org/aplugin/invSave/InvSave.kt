package org.aplugin.invSave

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.aplugin.invSave.Cmds.InvSC
import org.aplugin.invSave.Listeners.onPlayerDeathEvent
import org.bukkit.plugin.java.JavaPlugin

class InvSave : JavaPlugin() {

    override fun onEnable() {
        if (Instance!=null) return
        Instance=this

        logger.info(
            Component.text("인벤 세이브권 By.아포칼립스").color(TextColor.color(0xB9AAFF)).decorate(
                TextDecoration.BOLD).content())

        InvSC().InvSC(this)
        server.pluginManager.registerEvents(onPlayerDeathEvent(), this)
    }

    fun getInstance(): InvSave {
        return Instance!!
    }

    companion object  {
        var Instance:InvSave?=null
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
