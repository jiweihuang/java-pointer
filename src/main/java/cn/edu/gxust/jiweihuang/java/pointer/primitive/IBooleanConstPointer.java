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
import cn.edu.gxust.jiweihuang.java.pointer.array.BooleanArray;

/**
 * 接口{@code IBooleanConstPointer}用于表征一个指向{@code boolean}型数组的指针，
 * 属于常量指针，即该类型的指针不可改变所指向数组的值。
 * <p>
 * Development status：Finished     # Developing, Finished  <p>
 * Javadoc status: Finished         # Missing, Developing, Finished  <p>
 * Test status: None                # None, Missing, Developing, Finished  <p>
 * Last revision date: 2019-12-31 <p>
 *
 * @author JiweiHuang
 * @since 20191205
 */
public interface IBooleanConstPointer extends IArrayPointer {
    /**
     * 获取指定索引处的数据。<p>
     * 注意：这里的索引并不是数组的索引，
     * {@code getPoint + index}才是数组的索引。
     *
     * @param index 指定的索引。
     * @return 指定索引处的数据。
     */
    boolean get(int index);

    /**
     * 获取指针当前指向处的数据。
     * 注意：如果指针当前指向数组索引范围之外，则将抛出异常。
     *
     * @return 指针指向处的数据。
     */
    default boolean get() {
        return get(0);
    }

    /**
     * 获取指针关联的数组对象。
     *
     * @return 指针关联的数组对象。
     */
    BooleanArray getBase();

}
