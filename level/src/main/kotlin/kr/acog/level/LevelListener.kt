package kr.acog.level

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.io.File

class LevelListener : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val uuid = event.player.uniqueId
        val levelData = LevelUtils.getLevelData(uuid)

        if (levelData.level == 0L) {
            LevelUtils.plusLevel(uuid, 1)
        }
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        val uuid = event.player.uniqueId
        val levelData = LevelUtils.getLevelData(uuid)

        File(LevelPlugin.inst.dataFolder, "${uuid}.json").writeText(Json.encodeToString(levelData))
    }

}