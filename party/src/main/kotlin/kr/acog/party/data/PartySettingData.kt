package kr.acog.party.data

import kotlinx.serialization.Serializable

@Serializable
data class PartySettingData(
    val inviteDisplay : Boolean
)