package kr.acog.level

import java.util.*
import java.util.logging.Level

class LevelUtils {

    companion object {

        fun setLevelData(uuid: UUID, levelData: LevelData) {
            LevelPlugin.inst.levels[uuid] = levelData.copy(maxExp = levelData.getMaxExp(levelData.level))
        }

        fun plusLevel(uuid: UUID, index: Long) {
            val data = getLevelData(uuid)
            val level = data.level + index
            val newData = data.copy(level = level, exp = 0)
            setLevelData(uuid, newData)
        }

        fun minusLevel(uuid: UUID, index: Long) {
            val data = getLevelData(uuid)
            val level = if (data.level - index <= 0) 0 else data.level
            val newData = data.copy(level = level)
            setLevelData(uuid, newData)
        }

        fun plusExp(uuid: UUID, index: Long) {
            val data = getLevelData(uuid)
            val totalExp = data.exp + index

            val (level, exp) = if (totalExp >= data.maxExp) {
                val overflowExp = totalExp - data.maxExp
                data.level + 1 to overflowExp
            } else {
                data.level to totalExp
            }

            val newData = data.copy(level = level, exp = exp, maxExp = data.getMaxExp(level))
            setLevelData(uuid, newData)
        }

        fun minusExp(uuid: UUID, index: Long) {
            val data = getLevelData(uuid)
            val totalExp = data.exp - index

            val (level, exp) = if (totalExp < 0) {
                val underLevel = data.level - 1
                val underflowExp = data.getMaxExp(underLevel) - (index - data.exp)
                underLevel to underflowExp
            } else {
                data.level to totalExp
            }

            val newData = data.copy(level = level, exp = exp, maxExp = data.getMaxExp(level))
            setLevelData(uuid, newData)
        }

        fun getLevelData(uuid: UUID) : LevelData = LevelPlugin.inst.levels[uuid] ?: LevelData(0,0,0)

    }

}