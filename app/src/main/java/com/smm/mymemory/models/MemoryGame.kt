package com.smm.mymemory.models

import com.smm.mymemory.Utils.DEFAULT_ICONS

//its gona take in the boardsize and it has the responsibility of creating the cards into the game
class MemoryGame (private val boardSize: BoardSize) {

    val cards: List<MemoryCard>
    var numPairsFound = 0
    private var numCardFlipped = 0

    private var indexOfSingleSelectedCard: Int? = null

    init {
        val chosenImages = DEFAULT_ICONS.shuffled().take(boardSize.getNumPairs())
        val randomizedImages = (chosenImages + chosenImages).shuffled()
        //for every element it going to do an operation and create new list
        cards = randomizedImages.map { MemoryCard(it) }
    }

    fun FlipCard(position: Int): Boolean {
        numCardFlipped++
        // here"s is where the cards gonna flip the Game logic
        val card = cards[position]
        var foundMatch = false
        //we have three states
        //1: 0 card previously flipped over => flip over selected card
        //2: 1 card previously flipped over => flip over selected card and check if the images match
        //3: 2 cards previously flipped over => restore the cards and flip over the selected card
        // num 1 and 3 can be the same after the first round of the game
        //so i reduce the num of the states to 2
        // that means the only info we need is if there was one card previously flipped over
        //and the position of that flipped over card
        if(indexOfSingleSelectedCard == null){
            //it means 0 or 2 cards previously flipped over
            //first restore the cards
            restoreCards()
            // and set the index to the position of new selected card
            indexOfSingleSelectedCard = position
        }
        else{
            // it means 1 card previously flipped over
            foundMatch = checkForMatch(indexOfSingleSelectedCard!!, position)
            indexOfSingleSelectedCard = null
        }
        card.isFaceUp = !card.isFaceUp
        return foundMatch
    }

    private fun checkForMatch(position1: Int, position2: Int): Boolean {
        if (cards[position1].identifier != cards[position2].identifier){
            return false
        }
        cards[position1].isMatch = true
        cards[position2].isMatch = true
        numPairsFound++
        return true
    }


    private fun restoreCards() {
        for (card in cards){
            if (!card.isMatch) card.isFaceUp = false
        }
    }

    fun haveWonGame(): Boolean {
        return numPairsFound == boardSize.getNumPairs()
    }

    fun isCardFaceUp(position: Int): Boolean {
        return cards[position].isFaceUp
    }

    fun getNumMoves(): Int {
        return numCardFlipped / 2
    }
}
