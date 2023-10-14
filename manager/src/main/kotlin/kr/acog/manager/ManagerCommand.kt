package kr.acog.manager

import io.typecraft.command.Command
import io.typecraft.command.Command.pair
import io.typecraft.command.StandardArguments.longArg
import io.typecraft.command.bukkit.BukkitArguments.playerArg
import kr.acog.level.LevelUtils
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

val managerCommand: Command<ManagerCommand> =
    Command.mapping(
        pair("레벨", Command.mapping(
            pair("설정", Command.argument(ManagerCommand::LevelSetting, playerArg, longArg, longArg))
        )),
        pair("길드", Command.mapping(

        ))
    )

fun managerExecute(sender: CommandSender, command: ManagerCommand) {
    val plugin = ManagerPlugin.inst
    val player = sender as? Player ?: return

    if (!player.isOp) {
        player.sendMessage("§c해당 명령어를 사용 할 권한이 없습니다.")
        return
    }

    when (command) {
        is ManagerCommand.LevelSetting -> {
            val levelData = LevelUtils.getLevelData(command.player.uniqueId).copy(command.level, command.exp)
            LevelUtils.setLevelData(command.player.uniqueId, levelData)

            player.sendMessage("§f${command.player.name} 플레이어의 레벨을 설정 했습니다.")
            player.sendMessage("§7( Level : ${levelData.level}, Exp : ${levelData.exp}")
        }
    }
}

sealed interface ManagerCommand {
    data class LevelSetting(val player: Player, val level: Long, val exp: Long) : ManagerCommand
}