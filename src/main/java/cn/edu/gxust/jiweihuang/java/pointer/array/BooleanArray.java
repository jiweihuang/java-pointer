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
package cn.edu.gxust.jiweihuang.java.pointer.array;

import cn.edu.gxust.jiweihuang.java.pointer.IFunctionPointer;
import cn.edu.gxust.jiweihuang.java.pointer.primitive.IBooleanConstPointer;
import cn.edu.gxust.jiweihuang.java.pointer.primitive.IBooleanPointer;

import java.util.Arrays;
import java.util.Objects;

/**
 * 类{@code BooleanArray}用于表征{@code boolean}型数组。
 * <p>
 * Development status：Finished     # Developing, Finished  <p>
 * Javadoc status: Finished         # Missing, Developing, Finished  <p>
 * Test status: None                # None, Missing, Developing, Finished  <p>
 * Last revision date: 2019-12-28 <p>
 *
 * @author JiweiHuang
 * @since 20191205
 */
public class BooleanArray {

    /**
     * 数组的容量。
     */
    private final int capacity;

    /**
     * 数组的存储。
     */
    private final boolean[] values;

    /**
     * 类{@code BooleanArray}的主构造函数。<p>
     * 通过指定数组的容量创建数组对象，
     * 并将数组内所有元素的值设置为 {@code false}。
     * <p>
     * 注意：参数 {@code capacity}必须大于等于{@code 0}，
     * 否则，抛出{@code java.lang.NegativeArraySizeException}异常。
     *
     * @param capacity 数组的容量。
     */
    public BooleanArray(final int capacity) {
        //必须大于等于0
        this.capacity = capacity;
        this.values = new boolean[capacity];
    }

    /**
     * 类{@code BooleanArray}次级构造函数。<p>
     * 首先，通过指定数组的容量创建数组对象，
     * 然后，将数组内所有元素的值设置为参数{@code value}的值。
     * <p>
     * 注意：参数 {@code capacity}必须大于等于{@code 0}，
     * 否则，抛出{@code java.lang.NegativeArraySizeException}异常。
     *
     * @param capacity 数组的容量。
     * @param value    用于初始化数组的值。
     */
    public BooleanArray(final int capacity, boolean value) {
        this(capacity);
        for (int i = 0; i < capacity; i++) {
            this.values[i] = value;
        }
    }

    /**
     * 类{@code BooleanArray}次级构造函数，
     * 首先，通过指定数组的容量创建数组对象，
     * 然后，并利用基于索引的函数指针初始化数组内元素的值。
     *
     * @param capacity     数组的容量。
     * @param initFunction 用于初始化数组内元素值的函数指针。
     */
    public BooleanArray(final int capacity, IBooleanArrayInitFunction initFunction) {
        this(capacity);
        for (int i = 0; i < capacity; i++) {
            this.values[i] = initFunction.call(i);
        }
    }

    /**
     * 接口{@code IBooleanArrayInitFunction}一个函数指针，
     * 用于初始化数组元素值。<p>
     * 根据约定，函数指针所指向的函数名为 {@code call},
     * 函数的参数 {@code index} 表示数组的索引，
     * 函数返回值为数组内相应索引的初始化值。
     */
    public interface IBooleanArrayInitFunction extends IFunctionPointer {
        /**
         * 用于初始化数组元素值的函数。
         *
         * @param index 数组索引。
         * @return 数组的初始化值。
         */
        boolean call(int index);
    }

    /**
     * 获取数组的容量。
     *
     * @return 数组的容量。
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * 重置数组的值至其缺省值{@code false}。
     *
     * @return 元素值重置后的数组。
     */
    public BooleanArray reset() {
        for (int i = 0; i < capacity; i++) {
            this.values[i] = false;
        }
        return this;
    }

    /**
     * 将数组元素的值均重置为参数{@code value}。
     *
     * @param value 用于重置数组的值。
     * @return 值被重置后的数组对象。
     */
    public BooleanArray reset(boolean value) {
        for (int i = 0; i < capacity; i++) {
            this.values[i] = value;
        }
        return this;
    }

    /**
     * 用一个boolean数组初始化函数指针重置数组的值。
     *
     * @param initFunction 用于重置数组值的boolean数组初始化函数指针。
     * @return 值被重置后的数组对象。
     */
    public BooleanArray reset(IBooleanArrayInitFunction initFunction) {
        for (int i = 0; i < capacity; i++) {
            this.values[i] = initFunction.call(i);
        }
        return this;
    }

    /**
     * 通过拷贝创建一个新的数组对象。<p>
     * 注意：参数from和to应在{@code [0, getCapacity()]}范围内，
     * 且必须满足 {@code to >= from}，如果 {@code to == from}，
     * 将会得到一个容量为0的数组。
     *
     * @param from 所需要拷贝数组的起始索引，（包含该点）。
     * @param to   所需要拷贝数组的终点索引，（不包含该点）。
     * @return 一个新的数组对象。
     */
    public BooleanArray copy(int from, int to) {
        return of(Arrays.copyOfRange(this.values, from, to));
    }

    /**
     * 通过拷贝创建一个新的数组对象。<p>
     * 注意：参数from应在{@code [0, getCapacity()]}范围内，
     * 拷贝数据的索引范围为[from,getCapacity()]。
     *
     * @param from 所需要拷贝数组对象的起始索引，（包含该点）。
     * @return 一个新的数组对象。
     */
    public BooleanArray copy(int from) {
        return copy(from, getCapacity());
    }

    /**
     * 通过拷贝创建一个新的数组对象。<p>
     * 拷贝数据的索引范围为[0,getCapacity()]。
     *
     * @return 一个新的数组对象。
     */
    public BooleanArray copy() {
        return copy(0, getCapacity());
    }

    /**
     * 创建一个指向该数组的指针。
     * 指针的初始指向为0。
     *
     * @return 一个指向该数据区域的指针。
     */
    public IBooleanConstPointer createConstPointer() {
        return new BooleanConstPointer();
    }

    public IBooleanPointer createPointer() {
        return new BooleanPointer();
    }

    /**
     * 通过指定值组的方式创建数组对象，该方法可能并不高效，因为先创建了数组对象，
     * 接着创建了该数组对象的指针，最后利用该指针初始化了数组对象内元素的值，
     * 但考虑到数据封装的原则，牺牲了性能。
     *
     * @param values 用于创建数组的值。
     * @return 一个新的数组对象。
     */
    public static BooleanArray of(boolean... values) {
        Objects.requireNonNull(values, "Expected the parameter {values != null}.");
        int len = values.length;
        BooleanArray data = new BooleanArray(len);
        IBooleanPointer pointer = data.createPointer();
        for (int i = 0; i < len; i++) {
            pointer.set(i, values[i]);
        }
        return data;
    }

    /**
     * 类{@code BooleanConstPointer}是{@code IBooleanConstPointer}的实现，
     * 用于表征一个指向{@code boolean}型数组的指针。<p>
     * 因为是私有类，所以此类的外部无法访问该类，
     * 因为是内部类，故其拥有对其外部类数据的引用。
     */
    private class BooleanConstPointer implements IBooleanConstPointer {

        /**
         * 指针的指向。
         */
        private int point;

        /**
         * 主构造器，也是唯一的构造器。
         * 该构造器将指针的指向设置为0，
         * 构造器是私有的，意味着该类不能被外部实例化。
         */
        private BooleanConstPointer() {
            this.point = 0;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean get(int index) {
            int i = index + getPoint();
            if (i >= 0 && i < capacity) {
                return values[i];
            } else {
                throw new ArrayIndexOutOfBoundsException(String.format(
                        "Expected parameters {%d <= index < %d}.", -getPoint(),
                        capacity - getPoint()));
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BooleanArray getBase() {
            return BooleanArray.this;
        }

        @Override
        public IBooleanConstPointer copy() {
            return null;
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
            return capacity;
        }

        @Override
        public int getLength() {
            return 0;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void move(int offset) {
            this.point = this.point + offset;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void reset() {
            this.point = 0;
        }
    }

    private class BooleanPointer extends BooleanConstPointer implements IBooleanPointer {
        private BooleanPointer() {
            super();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void set(int index, boolean value) {
            int i = index + getPoint();
            if (i >= 0 && i < capacity) {
                values[i] = value;
            } else {
                throw new ArrayIndexOutOfBoundsException(String.format(
                        "Expected parameters {%d <= index < %d}.", -getPoint(),
                        capacity - getPoint()));
            }
        }

        @Override
        public IBooleanPointer copy() {
            return new BooleanPointer();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BooleanArray)) {
            return false;
        }
        BooleanArray that = (BooleanArray) obj;
        if (getCapacity() != that.getCapacity()) {
            return false;
        }
        IBooleanConstPointer thatPointer = that.createPointer();
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
        return "BooleanArray{" +
                "capacity=" + capacity +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
