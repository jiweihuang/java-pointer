/*
 *                           MIT License
 *
 * Copyright (c) 2019-2020, Jiwei Huang. All Rights Reserved.
 *
 * This file is a part of projects for java-pointer
 *  (https://github.com/jiweihuang/java-pointer)
 *
 *  -------------------------Contact Author--------------------------------
 *  Author: Jiwei Huang
 *  E-mail: jiweihuang@yeah.net, huangjiwei@gxust.edu.cn
 *  Organization: Guangxi University of Science and Technology
 *  Postcode：545006
 *  Contact number：0772-2687033(School Office), 0772-2687033（Fax）
 *  Address：#268 Avenue Donghuan, Chengzhong District, Liuzhou, Guangxi, China
 *  -----------------------------------------------------------------------
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package cn.edu.gxust.jiweihuang.java.pointer;

import java.util.Arrays;
import java.util.Objects;

/**
 * 类 {@code LongDataArea} 用于表征一块 {@code long} 型数据区域。
 * <p>
 * Development status: Release    # Developing <p>
 * Completion date: 20191020 <p>
 * Test status: Missing    # None, Finished <p>
 * Doc status: Finished    # Missing <p>
 *
 * @author JiweiHuang
 * @since 20191020
 */
public class LongArray implements IArray {

    //数据区域的容量
    private final int capacity;

    //数据区域的存储
    private final long[] values;

    /**
     * 主构造函数，通过指定数据区域的容量创建数据区域对象，
     * 并将数据区域内所有元素的值设置为 {@code 0}。
     * <p>
     * 注意：参数 {@code capacity}必须大于等于{@code 0}，否则，抛出
     * {@code java.lang.NegativeArraySizeException} 异常。
     *
     * @param capacity 数据区域的容量
     */
    public LongArray(final int capacity) {
        this.capacity = capacity; //必须大于等于0
        this.values = new long[capacity];
    }

    /**
     * 次级构造函数，通过指定数据区域的容量创建数据区域对象，
     * 并将数据区域内所有元素的值设置为 {@code value}。
     * <p>
     * 注意：参数 {@code capacity}必须大于等于{@code 0}，否则，抛出
     * {@code java.lang.NegativeArraySizeException} 异常。
     *
     * @param capacity 数据区域的容量。
     * @param value    初始化的数据区域的值。
     */
    public LongArray(final int capacity, long value) {
        this(capacity);
        for (int i = 0; i < capacity; i++) {
            this.values[i] = value;
        }
    }

    /**
     * 次级构造函数，通过指定数据区域的容量创建数据区域对象，
     * 并利用基于索引的函数指针初始化数据区域内元素的值。
     *
     * @param capacity     数据区域的容量。
     * @param initFunction 用于初始化数据区域内元素值的函数指针。
     */
    public LongArray(final int capacity, ILongDataInitFunction initFunction) {
        this(capacity);
        for (int i = 0; i < capacity; i++) {
            this.values[i] = initFunction.call(i);
        }
    }

    /**
     * 一个函数指针，用于初始化数据区域内元素值。
     * 根据预定，函数指针所指向的函数名为 {@code call},
     * 函数的参数 {@code index} 表示数据区域的索引，
     * 函数返回值为数据区域内相应索引的初始化值。
     */
    public interface ILongDataInitFunction extends IFunctionPointer {
        /**
         * 用于初始化数据区域内元素值的函数。
         *
         * @param index 数据区域的索引
         * @return 数据区域的初始化值
         */
        long call(int index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCapacity() {
        return capacity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongArray reset() {
        for (int i = 0; i < capacity; i++) {
            this.values[i] = 0;
        }
        return this;
    }

    /**
     * 将数据区域的值均重置为参数 {@code value}。
     *
     * @param value 用于重置数据区域的值。
     * @return 值被重置后的数据区域对象
     */
    public LongArray reset(long value) {
        for (int i = 0; i < capacity; i++) {
            this.values[i] = value;
        }
        return this;
    }

    /**
     * 用一个函数指针重置数据区域的值。
     *
     * @param initFunction 用于重置数据区域值的函数指针。
     * @return 值被重置后的数据区域对象
     */
    public LongArray reset(ILongDataInitFunction initFunction) {
        for (int i = 0; i < capacity; i++) {
            this.values[i] = initFunction.call(i);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LongArray copy(int from, int to) {
        return longDataOf(Arrays.copyOfRange(this.values, from, to));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ILongPointer createPointer() {
        return new LongDataPointer();
    }

    /**
     * 通过指定值的方式创建数据区域，该方法可能并不高效，因为先创建了对象，
     * 接着创建了对象的指针，最后利用该指针初始化了该对象内的值。但考虑到
     * 数据封装的原则，牺牲了性能。
     *
     * @param values 用于创建数据区域的值组。
     * @return 一个新的数据区域。
     */
    public static LongArray longDataOf(long... values) {
        Objects.requireNonNull(values, "Expected the parameter {values != null}.");
        int len = values.length;
        LongArray data = new LongArray(len);
        ILongPointer pointer = data.createPointer();
        for (int i = 0; i < len; i++) {
            pointer.set(i, values[i]);
        }
        return data;
    }

    /**
     * 类 {@code LongDataPointer} 是 {@code ILongPointer}的实现，
     * 用于表征一个指向 {@code long} 型数据区域的指针。
     */
    private class LongDataPointer implements ILongPointer {

        //指向
        private int point;

        /**
         * 主构造器，也是唯一的构造器。
         * 该构造器将指针的指向设置为0，
         * 构造器是私有的，意味着该类不能被外部初始化。
         */
        private LongDataPointer() {
            this.point = 0;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public long get(int index) {
            int i = index + getPoint();
            if (i >= 0 && i < getCapacity()) {
                return values[i];
            } else {
                throw new ArrayIndexOutOfBoundsException(String.format(
                        "Expected parameters {%d <= index < %d}", -getPoint(),
                        getCapacity() - getPoint()));
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void set(int index, long value) {
            int i = index + getPoint();
            if (i >= 0 && i < getCapacity()) {
                values[i] = value;
            } else {
                throw new ArrayIndexOutOfBoundsException(String.format(
                        "Expected parameters {%d <= index < %d}", -getPoint(),
                        getCapacity() - getPoint()));
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getPoint() {
            return point;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getCapacity() {
            return LongArray.this.getCapacity();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LongDataPointer move(int offset) {
            this.point = this.point + offset;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LongDataPointer copy() {
            return new LongDataPointer().move(getPoint());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public LongDataPointer reset() {
            this.point = 0;
            return this;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LongArray)) return false;
        LongArray that = (LongArray) obj;
        if (getCapacity() != that.getCapacity()) {
            return false;
        }
        ILongPointer thatPointer = that.createPointer();
        for (int i = 0; i < getCapacity(); i++) {
            if (values[i] != thatPointer.get(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(getCapacity());
        result = 31 * result + Arrays.hashCode(values);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "LongDataArea{" +
                "capacity=" + capacity +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
