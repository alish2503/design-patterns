package com.epam.rd.autocode.factory.plot;

import java.util.Arrays;
import java.util.stream.Collectors;

class PlotFactories {

    public PlotFactory classicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
        return new ClassicDisneyPlotFactory(hero.name(), beloved.name(), villain.name());
    }

    public PlotFactory contemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        return new ContemporaryDisneyPlotFactory(hero.name(), epicCrisis.name(), funnyFriend.name());
    }

    public PlotFactory marvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        String heroesNames = Arrays.stream(heroes)
                .map(Character::name)
                .map(name -> "brave " + name)
                .collect(Collectors.joining(", "));

        return new MarvelPlotFactory(heroesNames, epicCrisis.name(), villain.name());
    }

    private static class ClassicDisneyPlotFactory implements PlotFactory {
        String heroName;
        String belovedName;
        String villainName;

        public ClassicDisneyPlotFactory(String heroName, String belovedName, String villainName) {
            this.heroName = heroName;
            this.belovedName = belovedName;
            this.villainName = villainName;
        }

        @Override
        public Plot plot() {
            return new Plot() {
                @Override
                public String toString() {
                    return heroName + " is great. " + heroName + " and " +
                            belovedName + " love each other. " +
                            villainName + " interferes with their happiness but loses in the end.";
                }
            };
        }
    }
    private static class ContemporaryDisneyPlotFactory implements PlotFactory {
        String heroName;
        String epicCrisisName;
        String funnyFriendName;

        public ContemporaryDisneyPlotFactory(String heroName, String epicCrisisName, String funnyFriendName) {
            this.heroName = heroName;
            this.epicCrisisName = epicCrisisName;
            this.funnyFriendName = funnyFriendName;
        }
        @Override
        public Plot plot() {
            return new Plot() {
                @Override
                public String toString() {
                    return heroName + " feels a bit awkward and uncomfortable. " +
                            "But personal issues fades, when a big trouble comes - " +
                            epicCrisisName + ". " + heroName +
                            " stands up against it, but with no success at first." +
                            "But putting self together and help of friends, including spectacular funny " +
                            funnyFriendName + " restore the spirit and " + heroName +
                            " overcomes the crisis and gains gratitude and respect";
                }
            };
        }
    }
    private static class MarvelPlotFactory implements PlotFactory {
        String braveHeroNames;
        String epicCrisisName;
        String villainName;

        public MarvelPlotFactory(String braveHeroNames, String epicCrisisName, String villainName) {
            this.braveHeroNames = braveHeroNames;
            this.epicCrisisName = epicCrisisName;
            this.villainName = villainName;
        }


        @Override
        public Plot plot() {
            return new Plot() {
                @Override
                public String toString() {
                    return epicCrisisName + " threatens the world. But " + braveHeroNames +
                            " on guard. So, no way that intrigues of " + villainName +
                            " overcome the willpower of inflexible heroes";

                }
            };
        }
    }
}
