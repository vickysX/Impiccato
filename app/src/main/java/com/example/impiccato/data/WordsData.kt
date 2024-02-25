package com.example.impiccato.data

import com.example.impiccato.model.Word

const val MAX_NO_OF_WORDS = 4
const val MAX_NO_OF_WRONG_GUESSES = 9
const val INCREASE_DECREASE_SCORE = 10

val words : Set<Word> = setOf(
    Word(
        entry = "unbelievable",
        definition = "Non credibile, difficile a credersi; " +
                "è per lo più riferito, spesso con valore iperbolico, " +
                "a cosa che, per essere straordinaria, eccessiva, " +
                "singolare, quasi non può essere creduta."
    ),
    Word(
        entry = "weather",
        definition = "L’insieme delle condizioni fisiche atmosferiche " +
                "caratterizzato dalle condizioni meteorologiche " +
                "(temperatura, stato del cielo, umidità, pressione atmosferica, vento, ecc.)."
    ),
    Word(
        entry = "death",
        definition = "La cessazione delle funzioni vitali nell’uomo, " +
                "negli animali e in ogni altro organismo vivente o elemento costitutivo di esso."
    ),
    Word(
        entry = "delicious",
        definition = "Che procura delizia, che dà un intenso piacere " +
                "e godimento spirituale o estetico, o anche fisico. " +
                "Di sapore o di odore gradevole, squisito."
    )
)