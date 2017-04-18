package com.jacoblucas.hanabi.model;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeckTest {
    @Test
    public void NewDeckCreatesCorrectNumberOfCards() {
        Deck d = new Deck();
        assertThat(d.size(), is(Suit.values().length * 5));
    }

    @Test
    public void NewDeckIsShuffled() {
        Queue<Card> inOrderDeck = Deck.newInOrderDeck();
        Queue<Card> shuffledDeck = new Deck().getCards();

        assertThat(inOrderDeck.size(), is(shuffledDeck.size()));

        Map<Boolean, Integer> equalCounts = new HashMap<>();
        while (!shuffledDeck.isEmpty()) {
            Card c1 = shuffledDeck.poll();
            Card c2 = inOrderDeck.poll();

            boolean eq = c1.equals(c2);
            if (equalCounts.containsKey(eq)) {
                equalCounts.put(eq, equalCounts.get(eq) + 1);
            } else {
                equalCounts.put(eq, 1);
            }
        }

        assertThat(equalCounts.get(true), notNullValue());
        assertThat(equalCounts.get(false), notNullValue());
    }

    @Test
    public void NewDeckContainsCorrectAmountsOfEachNumberCard() {
        Deck deck = new Deck();
        Object[] cards = deck.getCards().toArray();

        for (Suit s : Suit.values()) {
            // Count by suit
            Map<Integer, Integer> numberCount = new HashMap<>();

            for (Object obj : cards) {
                Card c = (Card) obj;
                if (c.getSuit() == s) {
                    int number = c.getNumber();
                    if (numberCount.containsKey(number)) {
                        numberCount.put(number, numberCount.get(number) + 1);
                    } else {
                        numberCount.put(number, 1);
                    }
                }
            }

            assertThat(numberCount.get(1), is(3));
            assertThat(numberCount.get(2), is(2));
            assertThat(numberCount.get(3), is(2));
            assertThat(numberCount.get(4), is(2));
            assertThat(numberCount.get(5), is(1));
        }
    }
}
