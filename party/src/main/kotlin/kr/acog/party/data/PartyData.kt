package kr.acog.party.data

import java.util.UUID

data class PartyData(
    val owner: String,
    val players: MutableSet<UUID>
)