package kr.acog.setting

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class SettingListener : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val plugin = SettingPlugin.inst
        val uuid = event.player.uniqueId

        if (!plugin.settings.containsKey(uuid)) {
            SettingUtils.putSettingData(uuid, SettingUtils.getSettingData(uuid))
        }
    }

}