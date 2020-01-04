package cn.edu.gxust.jiweihuang.java.ndarray;

import java.util.Iterator;

public class DoubleNDArray implements INDArray<Double> {
    private final int[] shape;
    private final int dim;
    private final int len;
    private final double[] values;

    public DoubleNDArray(int... shape) {
        this.dim = shape.length;
        this.shape = shape.clone();
        int makeMul = 1;
        for (int i : shape) {
            makeMul = makeMul * i;
        }
        this.len = makeMul;
        this.values = new double[this.len];
    }

    @Override
    public Iterator<Double> iterator() {
        return null;
    }
}
