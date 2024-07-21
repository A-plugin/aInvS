package org.aplugin.invSave.Utils

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.title.Title
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemRarity
import org.bukkit.inventory.ItemStack
import java.time.Duration

class InvSavePaper {
    fun getSP():ItemStack {
        val item= ItemStack(Material.PAPER)
        val meta=item.itemMeta

        meta.displayName(Component.text("${ChatColor.GOLD}인벤 세이브권")
            .decorate(TextDecoration.BOLD))
        meta.setRarity(ItemRarity.RARE)
        meta.setCustomModelData(256)
        meta.setMaxStackSize(16)
        item.itemMeta=meta

        return item
    }

    fun RemoveInvSave(p: Player) {
        if (!p.inventory.contains(Material.PAPER)) return
        p.inventory.contents
            .filterNotNull()
            .find { it.hasItemMeta()
                    && it.itemMeta.customModelData==256
            }
            ?.let {
                it.amount -= 1
                p.showTitle(Title.title(Component.text(""),
                    Component.text("인벤 세이브권을 사용했습니다")
                    .color(TextColor.color(0x96C9F4))
                    .decorate(TextDecoration.BOLD),
                    Title.Times.times(Duration.ofSeconds(1)
                        , Duration.ofSeconds(3)
                        , Duration.ofSeconds(1))
                ))
            }
    }
}