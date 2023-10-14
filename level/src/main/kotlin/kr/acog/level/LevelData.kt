package kr.acog.level

import kotlinx.serialization.Serializable
import kotlin.math.ln

@Serializable
data class LevelData(
    val level: Long,
    val exp: Long,
    val maxExp: Long
) {
    fun getMaxExp(level: Long = this.level) : Long = (500 * (ln(level.toDouble() + 1) / ln(1.5))).toLong()
}