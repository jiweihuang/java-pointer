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
 * 接口 {@code IArrayPointer}用于表征“指向一维数组的指针”。
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
     * @param offset 指针指向的移动量。
     */
    void move(int offset);

    /**
     * 将指针的指向重置为{@code 0}。
     */
    void reset();

}
