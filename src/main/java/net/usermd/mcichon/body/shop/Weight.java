package net.usermd.mcichon.body.shop;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.functional.library.common.Function;

@Data
@NoArgsConstructor
public class Weight {

  public static final Weight ZERO = new Weight(0.0);

  public static Function<Weight, Function<OrderLine, Weight>> sum = x -> y -> x.add(y.getWeight());

  public double value;

  private Weight(double value) {
    this.value = value;
  }

  public static Weight weight(double value) {
    if (value <= 0) {
      throw new IllegalArgumentException("Waga musi być większa od 0");
    } else {
      return new Weight(value);
    }
  }

  public Weight add(Weight that) {
    return weight(this.value + that.value);
  }

  public Weight mult(int count) {
    return weight(this.value * count);
  }

  public String toString() {
    return Double.toString(this.value);
  }
}
