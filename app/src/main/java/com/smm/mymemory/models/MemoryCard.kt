package com.smm.mymemory.models

//we wana list out every atrebiute of a memoryCard
//and capture the idea of an identifire for a card
data class MemoryCard(
    val identifier: Int,
    var isFaceUp: Boolean = false,
    var isMatch: Boolean = false
)