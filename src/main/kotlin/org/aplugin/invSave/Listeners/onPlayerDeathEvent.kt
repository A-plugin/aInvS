package org.aplugin.invSave.Listeners

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import org.aplugin.invSave.Utils.InvSavePaper
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.inventory.ItemRarity
import org.bukkit.inventory.ItemStack

class onPlayerDeathEvent:Listener {

    val iSP=InvSavePaper()

    @EventHandler
    fun onPlayerDeathEvent(e: PlayerDeathEvent) {
        e.player.inventory.contents
            .filterNotNull()
            .find { it.hasItemMeta()
                    && it.itemMeta.customModelData==256
            }
            ?.let {
                e.drops.clear()
                e.keepInventory=true
                iSP.RemoveInvSave(e.player)
            }
    }
}