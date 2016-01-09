package org.k2.model.generator;

import org.k2.model.IGenerateNumberStrategy;

import java.util.Random;

public class ProbabilityGenerator implements IGenerateNumberStrategy {
    @Override
    public int getNextNumber() {
        return new Random().nextInt(99) < 80 ? 2 : 4;
    }
}
