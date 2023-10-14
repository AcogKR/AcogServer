package kr.acog.manager

import io.typecraft.command.bukkit.BukkitCommands
import kr.entree.spigradle.annotations.SpigotPlugin
import org.bukkit.plugin.java.JavaPlugin

@SpigotPlugin
class ManagerPlugin : JavaPlugin() {

    override fun onEnable() {
        inst = this
        BukkitCommands.register("관리", managerCommand, ::managerExecute, this)
    }

    override fun onDisable() {
        super.onDisable()
    }

    companion object {
        lateinit var inst: ManagerPlugin
    }
}