package net.usermd.mcichon.body.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    public double value;

    public static Price price(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Cena musi być większa od 0");
        }

        return new Price(value);
    }
}
