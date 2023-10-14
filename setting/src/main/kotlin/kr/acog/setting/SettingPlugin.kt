package kr.acog.setting

import io.typecraft.bukkit.view.BukkitView
import io.typecraft.command.bukkit.BukkitCommands
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.*

class SettingPlugin : JavaPlugin() {

    val settings : MutableMap<UUID, SettingData> = mutableMapOf()

    override fun onEnable() {
        inst = this
        BukkitView.register(this)

        dataFolder.walk()
            .filter { it.isFile && it.endsWith(".json") }
            .forEach { file ->
                val uuid = UUID.fromString(file.name.removeSuffix(".json"))
                val settingData = Json.decodeFromString<SettingData>(file.readText())
                settings[uuid] = settingData
            }
        server.pluginManager.registerEvents(SettingListener(), this)
        BukkitCommands.register("설정", SettingCommand.settingCommand, SettingCommand::execute, this)
    }

    override fun onDisable() {
        settings.forEach { (uuid, settingData) ->
            File(dataFolder, "${uuid}.json").writeText(Json.encodeToString(settingData))
        }
    }

    companion object {
        lateinit var inst : SettingPlugin
    }

}