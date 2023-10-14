package kr.acog.party

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kr.acog.party.data.PartyData
import kr.acog.party.data.PartySettingData
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.UUID

class PartyPlugin : JavaPlugin() {

    val settings : MutableMap<UUID, PartySettingData> = mutableMapOf()
    val parties : MutableMap<UUID, PartyData> = mutableMapOf()
    val invites : MutableMap<UUID, UUID> = mutableMapOf()

    override fun onEnable() {
        inst = this

        dataFolder.walk()
            .filter { it.isFile && it.endsWith(".json") }
            .forEach { file ->
                val uuid = UUID.fromString(file.name.removeSuffix(".json"))
                val partySettingData = Json.decodeFromString<PartySettingData>(file.readText())
                settings[uuid] = partySettingData
            }
    }

    override fun onDisable() {
        settings.forEach { (uuid, partySettingData) ->
            File(dataFolder, "${uuid}.json").writeText(Json.encodeToString(partySettingData))
        }
    }

    companion object {
        lateinit var inst : PartyPlugin
    }

}