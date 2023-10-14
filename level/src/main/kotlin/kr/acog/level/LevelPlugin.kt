package kr.acog.level

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.*

class LevelPlugin : JavaPlugin() {

    val levels : MutableMap<UUID, LevelData> = mutableMapOf()

    override fun onEnable() {
        inst = this
        if (!dataFolder.exists()) {
            dataFolder.mkdirs()
        }

        dataFolder.walk()
            .filter { it.isFile && it.endsWith(".json") }
            .forEach { file ->
                val uuid = UUID.fromString(file.name.removeSuffix(".json"))
                val levelData = Json.decodeFromString<LevelData>(file.readText())
                levels[uuid] = levelData
            }

        server.scheduler.runTaskTimer(this, Runnable {
            Bukkit.getOnlinePlayers().forEach { player ->
                val levelData = LevelUtils.getLevelData(player.uniqueId)
                val message = "§6레벨 : ${levelData.level} §b경험치 : ( ${levelData.exp} / ${levelData.maxExp} )"
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent(message))
            }}, 1L, 20L)
        server.pluginManager.registerEvents(LevelListener(), this)
    }

    override fun onDisable() {
        levels.forEach { (uuid, levelData) ->
            File(dataFolder, "${uuid}.json").writeText(Json.encodeToString(levelData))
        }
    }

    companion object {
        lateinit var inst : LevelPlugin
    }

}
