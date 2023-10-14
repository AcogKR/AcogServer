package kr.acog.setting

import java.util.UUID

class SettingUtils {

    companion object {

        fun getSettingData(uuid: UUID) : SettingData {
            val plugin = SettingPlugin.inst
            val settingData = plugin.settings[uuid] ?: SettingData(true)

            return settingData
        }

        fun putSettingData(uuid: UUID, settingData: SettingData) {
            SettingPlugin.inst.settings[uuid] = settingData
        }

    }

}
