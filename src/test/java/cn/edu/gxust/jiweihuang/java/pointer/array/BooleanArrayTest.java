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

import cn.edu.gxust.jiweihuang.java.pointer.primitive.IBooleanPointer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BooleanArrayTest {

    //测试三种主构造函数。
    BooleanArray booleanArray1;
    BooleanArray booleanArray2;
    BooleanArray booleanArray3;

    //测试静态创建对象的函数。
    BooleanArray booleanArray4;

    //创建指针对象
    IBooleanPointer booleanPointer1;
    IBooleanPointer booleanPointer2;
    IBooleanPointer booleanPointer3;
    IBooleanPointer booleanPointer4;

    @BeforeEach
    void setUp() {
        System.out.println("Test Start: ===============================================");
        //测试三种主构造函数。
        booleanArray1 = new BooleanArray(10);
        booleanArray2 = new BooleanArray(10, true);
        booleanArray3 = new BooleanArray(10, i -> {
            return i % 2 != 0;
        });

        //测试静态创建对象的函数。
        booleanArray4 = BooleanArray.of(true, false, true, false, true,
                false, true, false, true, false);
        booleanPointer1 = booleanArray1.createPointer();
        booleanPointer2 = booleanArray2.createPointer();
        booleanPointer3 = booleanArray3.createPointer();
        booleanPointer4 = booleanArray4.createPointer();

        System.out.println("booleanArray1: " + booleanArray1);
        System.out.println("booleanArray2: " + booleanArray2);
        System.out.println("booleanArray3: " + booleanArray3);
        System.out.println("booleanArray4: " + booleanArray4);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test over: ===============================================");
    }

    @Test
    void getCapacity() {
        assertEquals(10, booleanArray1.getCapacity());
        assertEquals(10, booleanArray2.getCapacity());
        assertEquals(10, booleanArray3.getCapacity());
        assertEquals(10, booleanArray4.getCapacity());

        assertEquals(10, booleanPointer1.getCapacity());
        assertEquals(10, booleanPointer2.getCapacity());
        assertEquals(10, booleanPointer3.getCapacity());
        assertEquals(10, booleanPointer4.getCapacity());
    }

    @Test
    void reset() {
        assertEquals(new BooleanArray(10, false), booleanArray1.reset());
        assertEquals(new BooleanArray(10, false), booleanArray2.reset());
        assertEquals(new BooleanArray(10, false), booleanArray3.reset());
        assertEquals(new BooleanArray(10, false), booleanArray4.reset());

        assertEquals(new BooleanArray(10, true), booleanArray1.reset(true));
        assertEquals(new BooleanArray(10, true), booleanArray2.reset(true));
        assertEquals(new BooleanArray(10, true), booleanArray3.reset(true));
        assertEquals(new BooleanArray(10, true), booleanArray4.reset(true));

        booleanArray1.reset(index -> index % 2 == 0);
        System.out.println("booleanArray.reset: " + booleanArray1);
    }

    @Test
    void copy() {

    }

    @Test
    void createPointer() {
    }

    @Test
    void of() {
    }

}