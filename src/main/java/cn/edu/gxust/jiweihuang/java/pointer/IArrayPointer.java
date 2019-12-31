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
 * 接口 {@code IArrayPointer}用于表征“指向一个一维数组的指针”。<p>
 * 注意：方法 {@code move}和{@code reset}的差异：<p>
 * (1)方法{@code move}是移动指针的指向，假如一个指针当前的指向索引为2，
 * 向右移动1个索引，则其新指向为3，正值为向右移动，负值为向左移动.<p>
 * (2)方法 {@code reset}是重置指针指向，即将指针指向的索引重置为0。<p>
 * 上述两种方法可以组合起来使用，以起到修改指针的作用。
 * <p>
 * 在c/cpp语言中，指针分为常量指针和指针常量两个概念：
 * 常量指针：指针指向的内容是常量，不能通过这个指针改变变量的值，
 * 当然，如果有其他非指针常量的指针也指向该变量，则其他指针可改变该变量的值。<p>
 * 指针常量有两种写法：<p>
 * （1）const int *p <p>
 * （2）int const *p <p>
 * 虽然常量指针指向的的值不能改变，但是该指针却可以指向其他的变量地址。
 * <p>
 * 指针常量：是指指针本身是个常量，不能再指向其他的地址。<p>
 * 指针常量的写法如下：int *const p <p>
 * 指针常量指向的地址不能改变，但是地址中保存的数值是可以改变的。
 * <p>
 * 区分常量指针和指针常量的关键就在于星号的位置，我们以星号为分界线，
 * 如果const在星号的左边，则为常量指针，如果const在星号的右边则为指针常量。
 * 如果我们将星号读作‘指针’，将const读作‘常量’的话，内容正好符合。
 * int const *p；是常量指针，int *const p；是指针常量。
 * <p>
 * 还有上述常量指针和指针常量的结合：const int* const p
 * <p>
 * 对于本系统，指针常量可以通关final关键字实现，而常量指针则使用相应类型的
 * ConstPointer实现。
 * <p>
 * Development status：Finished     # Developing, Finished  <p>
 * Javadoc status: Finished         # Missing, Developing, Finished  <p>
 * Test status: None                # None, Missing, Developing, Finished  <p>
 * Last revision date: 2019-12-31 <p>
 *
 * @author JiweiHuang
 * @since 20191205
 */
public interface IArrayPointer extends IPointer {
    /**
     * 获取指针的指向索引 (point index)。<p>
     * 注意：指向索引并不要求必须指向数组索引范围之内，
     * 换句话说，有可能指向数组索引范围外。<p>
     *
     * @return 指针的指向索引 (point index)。
     */
    int getPoint();

    /**
     * 获取指针所指向数组能容纳数据的数量。
     *
     * @return 指针所指向数组能容纳数据的数量。
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
     * 移动指针的指向，内部实现为 {@code point = getPoint() + offset}。
     *
     * @param offset 指针指向的移动量。
     */
    void move(int offset);

    /**
     * 将指针的指向重置为{@code 0}，即数组的起始位置。
     */
    void reset();

}
