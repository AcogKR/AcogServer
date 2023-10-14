package kr.acog.setting

import io.typecraft.bukkit.view.ChestView
import io.typecraft.bukkit.view.ViewContents
import io.typecraft.bukkit.view.ViewControl
import io.typecraft.bukkit.view.item.BukkitItem
import org.bukkit.Material

class SettingView {

    companion object {

        fun getSettingView(nickName: String, settingData: SettingData) : ChestView {
            val items : MutableMap<Int, ViewControl> = mutableMapOf()

//            items.put(0, ViewControl.of(settingItem) {
//                ViewAction.Update()
//            })
            val contents = ViewContents.of(mutableMapOf(), mapOf())
            val view = ChestView.just("${nickName} 님의 설정 페이지", 3, contents)
            return view
        }

        private fun settingItem(setingName: String, settingValue: Boolean) : BukkitItem {
            val state = if (settingValue) "§a활성화" else "§c비활성화"
            val name = "[${state}] $setingName"
            val lore = listOf("", "§f해당 아이템을 클릭하면 설정을 변경합니다!", "")
            val item = if (settingValue)
                BukkitItem.of(Material.GREEN_CONCRETE, 1, 0, name, lore, 0, mapOf()) else
                BukkitItem.of(Material.RED_CONCRETE, 1, 0, name, lore, 0, mapOf())
            return item
        }

        private fun settingEvent() {

        }

    }

}