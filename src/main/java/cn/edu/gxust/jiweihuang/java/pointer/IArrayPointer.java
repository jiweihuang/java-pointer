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

/**
 * 接口 {@code IArrayPointer}用于表征“指向一维数组的指针”。<p>
 * 注意：方法 {@code move}、{@code copy}和{@code reset}的差异：<p>
 * (1)方法{@code move}是移动指针的指向，假如一个指针当前的指向索引为2，
 * 向右移动1个索引，则其新指向为3.<p>
 * (2)方法 {@code copy}是拷贝指针，即通过拷贝创建了一个新的指针，
 * 拷贝的指针的指向与原指针的指向相同，
 * 但之后该指针的指向移动将与原指针无关。<p>
 * (3)方法 {@code reset}是只是重置指针，并不创建新指针，
 * 重置是指将指针的指向设置为0。<p>
 * 上述三种方法可以组合起来使用，以达到创建、修改和重置等目的。
 * <p>
 * Development status：Finished     # Developing, Finished  <p>
 * Javadoc status: Finished         # Missing, Developing, Finished  <p>
 * Test status: None                # None, Missing, Developing, Finished  <p>
 * Last revision date: 2019-12-25 <p>
 *
 * @author JiweiHuang
 * @since 20191205
 */
public interface IArrayPointer extends IPointer {
    /**
     * 获取指针的指向索引 (point index)。
     * 注意：指向索引并不要求必须指向数组索引范围之内，
     * 换句话说，有可能指向数组索引范围外。<p>
     *
     * @return 指针的指向索引 (point index)。
     */
    int getPoint();

    /**
     * 获取指针所指向数组所能容纳数据的数量。
     *
     * @return 指针所指向数组所能容纳数据的数量。
     */
    int getCapacity();

    /**
     * 检查指针是否指向数组索引范围之外，
     * 如果“是”返回 {@code true}，否则返回 {@code false}。
     *
     * @return {@code true} 如果指针指向数组索引范围之外。
     */
    default boolean isOutOfBounds() {
        return (getPoint() < 0 || getPoint() >= getCapacity());
    }

    /**
     * 检查指针的指向是否超越了数组索引范围的左边界，
     * 如果“是”返回 {@code true}，否则返回 {@code false}。
     *
     * @return {@code true} 如果指针指向超越了数组索引范围的左边界。
     */
    default boolean isOutOfLeftBounds() {
        return getPoint() < 0;
    }

    /**
     * 检查指针的指向是否超越了数组索引范围的右边界，
     * 如果“是”返回 {@code true}，否则返回 {@code false}。
     *
     * @return {@code true} 如果指针指向超越了数组索引范围的右边界。
     */
    default boolean isOutOfRightBounds() {
        return getPoint() >= getCapacity();
    }

    /**
     * 检查指针的指向是否指向数组索引范围的起始位置，
     * 如果“是”返回 {@code true}，否则返回 {@code false}。
     *
     * @return {@code true} 如果指针指向数组索引范围的起始位置。
     */
    default boolean isPointZero() {
        return getPoint() == 0;
    }
    /**
     * 检查指针的指向是否指向数组索引范围的右边界，
     * 如果“是”返回 {@code true}，否则返回 {@code false}。
     *
     * @return {@code true} 如果指针指向数组索引范围的右边界。
     */
    default boolean isPointRightBounds() {
        return getPoint() == (getCapacity() - 1);
    }

    /**
     * 移动指针的指向后返回该指针，
     * 内部实现为 {@code point = getPoint() + offset}。
     *
     * @param offset 指向索引移动量。
     * @param <T>    通过泛型参数指定返回具体类型的数据指针。
     * @return 指向移动后的指针。
     */
    <T extends IArrayPointer> T move(int offset);

    /**
     * 通过拷贝的方式创建新指针，拷贝后的指向与原指针的指向相同，
     * 但两者随后的移动将彼此分离，互补相关。
     *
     * @param <T> 通过泛型参数指定返回具体类型的数据指针。
     * @return 一个新的数据指针。
     */
    <T extends IArrayPointer> T copy();

    /**
     * 通过拷贝的方式创建新指针，并移动该指针指向新的索引。
     *
     * @param offset 移动量。
     * @param <T>    通过泛型参数指定返回具体类型的数据指针。
     * @return 一个新的数据指针。
     */
    default <T extends IArrayPointer> T copy(int offset) {
        return copy().move(offset);
    }

    /**
     * 将指针的指向重置为 {@code 0}。
     *
     * @param <T> 通过泛型参数指定返回具体类型的数据指针。
     * @return 指向重置后的指针。
     */
    <T extends IArrayPointer> T reset();

    /**
     * 将指针的指向重置为 {@code offset}，因为是先重置为 {@code 0}，
     * 然后移动至 {@code offset}，所以该指针的最终指向为
     * {@code offset}。
     *
     * @param offset 重置的索引
     * @param <T>    通过泛型参数指定返回具体类型的数据指针。
     * @return 指针重置至指定索引后的指针。
     */
    default <T extends IArrayPointer> T reset(int offset) {
        return reset().move(offset);
    }
}
