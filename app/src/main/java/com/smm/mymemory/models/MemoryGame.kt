package com.smm.mymemory.models

import com.smm.mymemory.Utils.DEFAULT_ICONS

//its gona take in the boardsize and it has the responsibility of creating the cards into the game
class MemoryGame (private val boardSize: BoardSize) {
    val cards: List<MemoryCard>
    val numPairsFound = 0

    init {
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizedImages = (chosenImages + chosenImages).shuffled()
        //for every element it going to do an operation and create new list
        cards = randomizedImages.map { MemoryCard(it) }
    }
}