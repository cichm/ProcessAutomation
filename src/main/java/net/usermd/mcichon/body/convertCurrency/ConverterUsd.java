package net.usermd.mcichon.body.convertCurrency;

public class ConverterUsd implements IConverter {
    @Override
    public double convert(double euro) {
        return euro * 1.2;
    }
}
