/*
 *                           MIT License
 *
 * Copyright (c) 2019-2020, Jiwei Huang. All Rights Reserved.
 *
 * This file is a part of projects for textiles (https://github.com/jiweihuang/textiles)
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
 * 接口 {@code IObjectPointer} 是一个标记接口 (marker interface)，用于表征'对象指针'。
 * 这里"对象指针"的实质就是实现了该空接口(empty interface)的Java类对象。
 * 使Java类实现该空接口的目的是统一类型，这样做的好处是可以指定{@code IPointer}
 * 作为一个函数的参数类型或返回值类型，而{@code IPointer}是任意指针类型的父类，
 * 包括Java中任意实现了此接口的对象。
 * <p>
 * Development status：Finished  # Developing, Finished  <p>
 * Javadoc status: Finished  # Missing, Developing, Finished  <p>
 * Test status: None  # None, Missing, Developing, Finished  <p>
 * Last revision date: 2019-11-30  <p>
 *
 * @author JiweiHuang
 * @see IPointer
 * @since 20191130
 */
public interface IObjectPointer extends IPointer {
    //用于表征'对象指针'。
    /*
     * 对象指针的目的是将Java类对象归入至指针类型的体系中。
     *
     * 用法举例： IPointer doSomeThing(IPointer ...pointerArgs);
     *
     * 如上函数的参数为IPointer类型，意味着可以传入 IObjectPointer、IDataPointer或IFunctionPointer。
     * 同理，也可以返回任意指针类型。
     *
     */
}
