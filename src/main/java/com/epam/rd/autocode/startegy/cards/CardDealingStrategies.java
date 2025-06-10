package com.epam.rd.autocode.startegy.cards;

import java.util.*;

public class CardDealingStrategies {
    public static CardDealingStrategy texasHoldemCardDealingStrategy() {
        return new CardDealingStrategyImpl(2, 5);
    }

    public static CardDealingStrategy classicPokerCardDealingStrategy() {
        return new CardDealingStrategyImpl(5, 0);
    }

    public static CardDealingStrategy bridgeCardDealingStrategy(){
        return new CardDealingStrategyImpl(13, 0);
    }

    public static CardDealingStrategy foolCardDealingStrategy(){
        return new CardDealingStrategyImpl(6, 1);
    }

    private static class CardDealingStrategyImpl implements CardDealingStrategy {
        final private int cardsPerPlayer;
        final private int additionalCards;

        public CardDealingStrategyImpl(int cardsPerPlayer, int additionalCards) {
            this.cardsPerPlayer = cardsPerPlayer;
            this.additionalCards = additionalCards;
        }


        @Override
        public Map<String, List<Card>> dealStacks(Deck deck, int players) {

            if (additionalCards + players * cardsPerPlayer > deck.size()) {
                throw new IllegalArgumentException("Wrong parameters for the game");
            }
            HashMap<String, List<Card>> stacksPerPlayer = new HashMap<>();
            for (int i = 1; i <= cardsPerPlayer; ++i) {
                for (int j = 1; j <= players; ++j) {
                    stacksPerPlayer.computeIfAbsent("Player " + j,
                            player -> new ArrayList<>()).add(deck.dealCard());
                }
            }
            if (additionalCards == 5) {
                stacksPerPlayer.put("Community", new ArrayList<>());
                for (int i = 1; i <= additionalCards; ++i) {
                    stacksPerPlayer.get("Community").add(deck.dealCard());
                }
            }
            else if (additionalCards == 1) {
                stacksPerPlayer.put("Trump card",
                        Collections.singletonList(deck.dealCard()));
            }
            List<Card> remaining = deck.restCards();
            if (!remaining.isEmpty()) {
                stacksPerPlayer.put("Remaining", remaining);
            }

            return stacksPerPlayer;
        }
    }
}
