package net.usermd.mcichon.body.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weight {
    public double value;

    public static Weight weight(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Waga musi być większa od 0");
        }

        return new Weight(value);
    }
}

