package net.usermd.mcichon.body.convertCurrency;

public class ConverterPln implements IConverter {
    @Override
    public double convert(double euro) {
        return euro * 3.4;
    }
}
