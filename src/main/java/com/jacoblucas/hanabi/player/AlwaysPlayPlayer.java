package com.jacoblucas.hanabi.player;

import com.jacoblucas.hanabi.model.Card;
import com.jacoblucas.hanabi.model.Suit;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class AlwaysPlayPlayer extends Player {
    public AlwaysPlayPlayer(String name) {
        this.name = name;
    }

    @Override
    public Action takeAction(Map<Suit, Stack<Card>> fireworks, Map<Player, List<Card>> playerHands) {
        return new Action(ActionType.PLAY, 0);
    }
}
