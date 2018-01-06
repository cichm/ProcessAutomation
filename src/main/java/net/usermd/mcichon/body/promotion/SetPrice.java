package net.usermd.mcichon.body.promotion;

public final class SetPrice {
    public static double initialize(RoundBonus roundHole, double price) {
        SquareBonusAdapter squareBonusAdapter;
        squareBonusAdapter = new SquareBonusAdapter(price);

        return squareBonusAdapter.makeFit(roundHole);
    }
}
