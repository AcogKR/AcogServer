package kr.acog.party

import io.typecraft.command.Command
import io.typecraft.command.Command.pair
import org.bukkit.command.CommandSender

val partyCommand: Command<PartyCommand> =
    Command.mapping(
        pair("수락", Command.present(PartyCommand.Accept)),
        pair("거절", Command.present(PartyCommand.Deny))
    ).withFallback(Command.present(PartyCommand.Open))

fun executePartyCommand(sender: CommandSender, command: PartyCommand) {
    val plugin = PartyPlugin.inst

    when(command) {
        PartyCommand.Accept -> TODO()
        PartyCommand.Deny -> TODO()
        PartyCommand.Open -> TODO()
        else -> return
    }

}

sealed interface PartyCommand {
    data object Open : PartyCommand
    data object Accept : PartyCommand
    data object Deny : PartyCommand
}