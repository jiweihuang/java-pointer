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
package cn.edu.gxust.jiweihuang.java.pointer.primitive;

import cn.edu.gxust.jiweihuang.java.pointer.IArrayPointer;

public interface IDoubleConstPointer extends IArrayPointer {
    /**
     * 获取指定索引处的数据。
     *
     * @param index 指定的索引。
     * @return 指定索引处的数据。
     */
    double get(int index);

    /**
     * 获取指针当前指向处的数据。
     * 注意：如果指针当前指向数组索引范围之外，则将抛出异常。
     *
     * @return 指针指向处的数据。
     */
    default double get() {
        return get(0);
    }

    /**
     * 移动指针的指向后返回该指针，
     * 内部实现为 {@code point = getPoint() + offset}。
     *
     * @param offset 指针指向的移动量。
     * @return 指针指向移动后的指针。
     */
    IDoublePointer move(int offset);

    /**
     * 通过拷贝的方式创建新指针，拷贝后的指向与原指针的指向相同，
     * 但两者随后的移动将彼此分离，互补相关。
     *
     * @return 拷贝后得到的新指针。
     */
    IDoublePointer copy();

    /**
     * 通过拷贝的方式创建新指针，并移动该指针指向新的索引。
     *
     * @param offset 指针指向的偏移量。
     * @return 拷贝并移动后的新指针。
     */
    default IDoublePointer copy(int offset) {
        return copy().move(offset);
    }

    /**
     * 将指针的指向重置为{@code 0}。
     *
     * @return 指针指向重置后的指针。
     */
    IDoublePointer reset();

    /**
     * 将指针的指向重置为{@code offset}。<p>
     * 内部实现为先将指针指向重置为{@code 0}，
     * 然后移动至 {@code offset}。
     *
     * @param offset 重置后指针指向。
     * @return 指针指向重置至指定索引后的指针。
     */
    default IDoublePointer reset(int offset) {
        return reset().move(offset);
    }
}