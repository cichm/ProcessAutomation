package net.usermd.mcichon.body.promotion;

public class SquareBonusAdapter {
    private final Bonus bonus;

    SquareBonusAdapter(double value) {
        bonus = new Bonus(value);
    }

    public double makeFit(RoundBonus roundHole) {
        double amount = bonus.getValue() - roundHole.getRadius() * 2;

        if (amount > 0) {
            bonus.setValue(bonus.getValue() - amount);

            return bonus.getValue();
        }

        return 0;
    }
}
