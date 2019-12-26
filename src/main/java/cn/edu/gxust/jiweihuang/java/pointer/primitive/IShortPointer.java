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

/**
 * 接口 {@code IShortPointer} 用于表征一个指向{@code short} 型数据区域的指针。
 * <p>
 * Development status: Release    # Developing <p>
 * Completion date: 20191022 <p>
 * Test status: None    # Missing, Finished <p>
 * Doc status: Finished    # Missing <p>
 *
 * @author JiweiHuang
 * @see IDataPointer
 * @since 20191020
 */
public interface IShortPointer extends IDataPointer {
    /**
     * 获取指定索引处的数据。
     *
     * @param index 指定的索引。
     * @return 指定索引处的数据。
     */
    short get(int index);

    /**
     * 获取指针指向处的数据。
     * 注意：如果指针指向数据区域外，则将抛出异常。
     *
     * @return 指针指向处的数据。
     */
    default short get() {
        return get(0);
    }

    /**
     * 设置指定索引处的数据。
     *
     * @param index 指定的索引
     * @param value 需要设置的值。
     */
    void set(int index, short value);

    /**
     * 设置指针指向处的数据。
     * 注意：如果指针指向数据区域外，则将抛出异常。
     *
     * @param value 需要设置的值。
     */
    default void set(short value) {
        set(0, value);
    }
}
