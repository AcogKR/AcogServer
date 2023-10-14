package kr.acog.setting

import io.typecraft.bukkit.view.BukkitView
import io.typecraft.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SettingCommand {

    companion object {
        val settingCommand: Command<Unit> = Command.present(null)

        fun execute(sender: CommandSender, command: Unit) {
            val plugin = SettingPlugin.inst
            val player = sender as? Player ?: return
            val settingData = SettingUtils.getSettingData(player.uniqueId)
            val view = SettingView.getSettingView(player.name, settingData)
            BukkitView.openView(view, player, plugin)
        }
    }

}