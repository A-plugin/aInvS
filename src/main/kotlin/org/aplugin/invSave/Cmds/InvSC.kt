package org.aplugin.invSave.Cmds

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import org.aplugin.invSave.Utils.InvSavePaper
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin


class InvSC : TabExecutor {
    fun InvSC(plugin: JavaPlugin) {
        plugin.getCommand("invS")?.setExecutor(this::onCommand)
        plugin.getCommand("invS")?.setTabCompleter(this::onTabComplete)
    }

    val iSP=InvSavePaper()

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false
        if (!sender.hasPermission("invs.cmds")) {
            sender.sendMessage(Component.text("[aInvS] 명령어를 실행할 권한이 없습니다")
                .color(TextColor.color(0xFF4C4C)).decorate(TextDecoration.ITALIC))
            return true
        }
        if (args==null || args.size<2) {
            sender.sendMessage(Component.text("[aInvS] /invS [<플레이어 닉네임>] [수량]")
                .color(TextColor.color(0xFF4C4C)).decorate(TextDecoration.ITALIC))
            return true
        }
        val nick=args[0]
        val amount=args[1].toIntOrNull() ?: run { sender.sendMessage(Component.text("[aInvS] 올바른 수량을 입력하세요")
            .color(TextColor.color(0xFF4C4C)).decorate(TextDecoration.ITALIC))
            return true }

        val Tplayer=Bukkit.getPlayer(nick) ?: run {
            sender.sendMessage(Component.text("[aInvS]해당 플레이어가 존재하지 않습니다!")
                .color(TextColor.color(0xFF4C4C)).decorate(TextDecoration.ITALIC))
            return true
        }

        val savePaper=iSP.getSP()
        savePaper.amount=amount

        Tplayer.inventory.addItem(savePaper)
        sender.sendMessage(Component.text("[aInvS] 플레이어 ${Tplayer.name}에게 인벤 세이브권 $amount 개를 지급했습니다")
            .color(TextColor.color(0xB4E380)).decorate(TextDecoration.ITALIC))

        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {
        val tab = mutableListOf<String>()
        if (args.isEmpty() || args.size == 1) {
            val PlayerList= Bukkit.getOnlinePlayers()
            for (p in PlayerList) {
                tab.add(p.name)
            }
        } else if (args.size == 2) {
            tab.add("1")
            tab.add("8")
            tab.add("16")

        }
        return tab
    }
}