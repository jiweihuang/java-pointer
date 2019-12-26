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
 * 接口 {@code IDataArea} 是用于表征一块数据区域的基接口。
 * <p>
 * Development status: Release    # Developing <p>
 * Completion date: 20191020 <p>
 * Test status: None    # Missing, Finished <p>
 * Doc status: Finished    # Missing <p>
 *
 * @author JiweiHuang
 * @since 20191020
 */
public interface IArray {
    /**
     * 获取数据区域可容纳数据元素的数量。
     *
     * @return 数据区域可容纳数据元素的数量。
     */
    int getCapacity();

    /**
     * 重置数据区域的所有数据为0值（类型零值）。
     *
     * @param <T> 通过泛型参数指定返回具体类型的数据区域。
     * @return 重置0值后的数据区域。
     */
    <T extends IArray> T reset();

    /**
     * 通过拷贝创建一个新的数据区域。
     * 注意：参数from和to应在{@code [ 0, getCapacity() )}范围内，
     * 且必须满足 {@code to >= from}，如果 {@code to == from}，
     * 将会得到一个容量为0的数据区域。
     *
     * @param from 所需要拷贝数据区域的起始索引，（包含该点）。
     * @param to   所需要拷贝数据区域的终点索引，（不包含该点）。
     * @param <T>  通过泛型参数指定返回具体类型的数据区域。
     * @return 一个新的数据区域。
     */
    <T extends IArray> T copy(int from, int to);

    /**
     * 通过拷贝创建一个新的数据区域。
     *
     * @param from 所需要拷贝数据区域的起始索引，（包含该点）。
     * @param <T>  通过泛型参数指定返回具体类型的数据区域。
     * @return 一个新的数据区域。
     */
    default <T extends IArray> T copy(int from) {
        return copy(from, getCapacity());
    }

    /**
     * 通过拷贝创建一个新的数据区域。
     *
     * @param <T> 通过泛型参数指定返回具体类型的数据区域。
     * @return 一个新的数据区域。
     */
    default <T extends IArray> T copy() {
        return copy(0, getCapacity());
    }

    /**
     * 创建一个指向该数据区域的指针。
     *
     * @param <T> 通过泛型参数指定返回具体类型的数据指针。
     * @return 一个指向该数据区域的指针。
     */
    <T extends IArrayPointer> T createPointer();

    /**
     * 创建一个指向该数据区域的指针，并使指针指向 {@code offset}。
     *
     * @param offset 指针指向的移动量。
     * @param <T>    通过泛型参数指定返回具体类型的数据指针。
     * @return 一个指向该数据区域的指针。
     */
    default <T extends IArrayPointer> T createPointer(int offset) {
        return createPointer().move(offset);
    }
}
