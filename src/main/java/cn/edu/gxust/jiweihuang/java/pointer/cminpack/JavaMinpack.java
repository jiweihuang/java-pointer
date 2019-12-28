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
package cn.edu.gxust.jiweihuang.java.pointer.cminpack;

import cn.edu.gxust.jiweihuang.java.pointer.primitive.IDoublePointer;
import cn.edu.gxust.jiweihuang.java.pointer.IFunctionPointer;
import cn.edu.gxust.jiweihuang.java.pointer.primitive.IIntConstPointer;
import cn.edu.gxust.jiweihuang.java.pointer.IPointer;

import static java.lang.Math.*;

/**
 * 类{@code JavaMinpack}是<a href=
 * "https://github.com/devernay/cminpack">cminpack</a>库的Java语言重写，
 * 而cminpack库是一种源于Fortran编写的<a href=
 * "http://www.netlib.org/minpack/">minpack</a>库的C语言实现。
 * <p>
 * Development status：Finished    # Developing, Finished  <p>
 * Javadoc status: Finished        # Missing, Developing, Finished  <p>
 * Test status: None               # None, Missing, Developing, Finished  <p>
 * Last revision date: 2019-12-15  <p>
 *
 * @author JiweiHuang
 * @since 20191020
 */
public class JavaMinpack {

    //========================== Function interface (base)==============================

    /**
     * 接口{@code INonlinearFunctions}是所有用于提供“非线性问题计算式”函数接口的父接口，
     * 此接口提供了三个属性，其中：<p>
     * （1）属性{@code getN()}用于表征“非线性问题计算式”中变量的数量。<p>
     * （2）属性{@code getM()}用于表征“非线性问题计算式”中方程的数量。<p>
     * (3) 属性{@code getPointer()}用于表征“非线性问题计算式”中所需的额外参数或数据。<p>
     * 就类{@code JavaMinpack}中所能求解的“非线性问题”，可分为两类：<p>
     * （1）对于“非线性方程组”，要求{@code getN() == getM()}，即“N个方程组N元非线性方程组”。<p>
     * （2）对于“非线性最小二乘问题”，要求{@code getN() <= getM()},即变量的数量小于方程的数量。<p>
     */
    public interface INonlinearFunctions extends IFunctionPointer {
        /**
         * 获取“非线性问题计算式”中变量的数量。
         *
         * @return “非线性问题计算式”中变量的数量。
         */
        int getN();

        /**
         * 获取“非线性问题计算式”中方程的数量。
         *
         * @return “非线性问题计算式”中方程的数量。
         */
        int getM();

        /**
         * 获取“非线性问题计算式”中所需的额外参数或数据。<p>
         * 默认返回一个空的指针，如果需要提供额外的数据，
         * 可以通过重载该方法来达到目的。
         *
         * @return “非线性问题计算式”中所需的额外参数或数据。
         */
        default IPointer getPointer() {
            return new IPointer() {
            };
        }
    }
    //========================== function interface (5 concrete) ==========================

    /**
     * 接口{@code INNonlinearEquations}是一个函数式接口，
     * 包装了“用户提供函数（user-supplied function）”，
     * 定义了“N个方程组N元非线性方程组”的计算式。
     * <p>
     * 此接口被{@code fdjac1}，{@code hybrd}和{@code hybrd1}等函数作为参数使用，
     * 用于求解非线性方程组，其中，“非线性方程组”计算式的雅可比矩阵采用“向前差分法”近似求解。<p>
     */
    public interface INNonlinearEquations extends INonlinearFunctions {
        /**
         * 被包装的用户提供函数（user-supplied function）。<p>
         * 函数签名参考了<a href ="http://bytedeco.org/">JavaCPP</a>的API。<p>
         *
         * @param n     “非线性方程组”计算式中变量的数量，该值应与{@code getN()}的返回值一致。
         * @param x     “非线性方程组”计算式中变量的值，该指针指向一个长度为n的数组,
         *              方程组求解前，用于指定“非线性方程组”解的初始值，
         *              方程组求解后，用于保存最终的待求解变量的值。
         * @param fvec  用于保存“非线性方程组”计算式的方程计算值，即变量为{@code x}时方程计算值。
         * @param iflag 一个标记值，要求此值在{@code call}调用期间不应该被改变。
         * @param p     用于提供其他额外需要的参数。
         * @return 如果为负值，则可中止“非线性方程组”的求解。
         */
        int call(int n, IDoublePointer x, IDoublePointer fvec, int iflag, IPointer... p);

        /**
         * 获取“非线性方程组计算式”中方程的数量。<p>
         * 这里缺省设置了{@code getM() == getN()}，
         * 以便于表征“N个方程组N元非线性方程组”。
         *
         * @return “非线性问题计算式”中方程的数量。
         */
        @Override
        default int getM() {
            return getN();
        }
    }

    /**
     * 接口{@code INNonlinearEquationsJacobi}是一个函数式接口，
     * 包装了一种“用户提供函数（user-supplied function）”，
     * 定义了“N个方程组N元非线性方程组”的计算式，与{@code INNonlinearEquations}不同的是，
     * 此接口所包装的计算式还包含了“雅可比矩阵的计算式”。
     * <p>
     * 该接口被{@code hybrj}和{@code hybrj1}等函数作为参数使用，
     * 用于求解非线性方程组，其中，“非线性方程组”计算式的雅可比矩阵由用户提供。<p>
     */
    public interface INNonlinearEquationsJacobi extends INonlinearFunctions {
        /**
         * 用户提供函数（user-supplied function）。<p>
         * 函数签名参考了<a href ="http://bytedeco.org/">JavaCPP</a>的API.
         *
         * @param n      “非线性方程组”计算式中变量的数量，该值应与{@code getN()}的返回值一致。
         * @param x      “非线性方程组”计算式中变量的值，长度为n,
         *               用于指定“非线性方程组”解的初始值。
         * @param fvec   用于保存“非线性方程组”计算式的方程计算值，即变量为{@code x}时方程计算值。
         * @param fjac   用于保存“计算式”的雅可比矩阵。注意：雅可比矩阵按列排列，求解会更快。
         * @param ldfjac 雅可比矩阵的主维数。实际就是“非线性方程组”计算式中方程的个数。
         * @param iflag  一个标记值，要求此值在{@code call}调用期间不应该被改变。
         *               如果{@code iflag=1}，则将各方程的计算值更新至fvec，而fjac不变。
         *               如果{@code iflag=2}，则将方程的雅可比矩阵更新至fjac，而fvec不变。
         * @param p      用于提供其他额外需要的参数。
         * @return 如果为负值，则可中止“非线性方程组”的求解。
         */
        int call(int n, IDoublePointer x, IDoublePointer fvec, IDoublePointer fjac,
                 int ldfjac, int iflag, IPointer... p);

        /**
         * 获取“非线性方程组计算式”中方程的数量。<p>
         * 这里缺省设置了{@code getM() == getN()}，
         * 以便于表征“N个方程组N元非线性方程组”。
         *
         * @return “非线性问题计算式”中方程的数量。
         */
        @Override
        default int getM() {
            return getN();
        }
    }

    /**
     * 接口{@code IMNNonlinearLeastSquares}是一个函数式接口，
     * 包装了一种“用户提供函数（user-supplied function）”，
     * 定义了“非线性最小二乘问题”的计算式。
     * <p>
     * 此接口被{@code fdjac2}，{@code lmdif}和{@code lmdif1}等函数作为参数使用，
     * 用于求解“非线性最小二乘问题”，雅可比矩阵采用“向前差分法”近似计算而得。<p>
     */
    public interface IMNNonlinearLeastSquares extends INonlinearFunctions {
        /**
         * 用户提供函数（user-supplied function）。<p>
         * 函数签名参考了<a href ="http://bytedeco.org/">JavaCPP</a>的API.
         *
         * @param m     “非线性最小二乘问题”计算式中方程的数量，该值应与{@code getM()}的返回值一致。
         * @param n     “非线性最小二乘问题”计算式中变量的数量，该值应与{@code getN()}的返回值一致,
         *              且要求{@code n <= m}。
         * @param x     “非线性最小二乘问题”计算式的变量值组，用于指定“非线性最小二乘问题”解的初始值。
         * @param fvec  “非线性最小二乘问题”计算式的方程计算值组，即变量为{@code x}时方程计算值。
         * @param iflag 一个标记值，要求此值在{@code call}调用期间不应该被改变。
         * @param p     用于提供其他额外需要的参数。
         * @return 如果为负值，则可中止“非线性最小二乘问题”的求解。
         */
        int call(int m, int n, IDoublePointer x, IDoublePointer fvec,
                 int iflag, IPointer... p);
    }

    /**
     * 接口{@code IMNNonlinearLeastSquaresJacobi}是一个函数式接口，
     * 包装了一种“用户提供函数（user-supplied function）”，
     * 定义了“非线性最小二乘问题”的计算式。
     * <p>
     * 此接口被{@code lmder}和{@code lmder1}等函数作为参数使用，
     * 用于求解非线性最小二乘问题，但需要提供计算式的雅可比矩阵。<p>
     */
    public interface IMNNonlinearLeastSquaresJacobi extends INonlinearFunctions {
        /**
         * 用户提供函数（user-supplied function）。<p>
         * 函数签名参考了<a href ="http://bytedeco.org/">JavaCPP</a>的API。
         *
         * @param m      “非线性最小二乘问题”计算式中方程的数量，该值应与{@code getM()}的返回值一致。
         * @param n      “非线性最小二乘问题”计算式中变量的数量，该值应与{@code getN()}的返回值一致,
         *               且要求{@code n <= m}。
         * @param x      “非线性最小二乘问题”计算式的变量值组，包含“非线性最小二乘问题”解的初始值或经求解后则为最终的解值。
         * @param fvec   “非线性最小二乘问题”计算式的方程计算值组，即变量为{@code x}时方程计算值。
         * @param fjac   方程的雅可比矩阵。雅可比矩阵按列排列更快。
         * @param ldfjac 雅可比矩阵的主维数。实际就是“非线性最小二乘问题”计算式中方程的个数。
         * @param iflag  一个标记值，要求此值在{@code call}调用期间不应该被改变。
         *               如果iflag=1，则将方程计算值更新至fvec，而fjac不变。
         *               如果iflag=2，则将方程的雅可比矩阵更新至fjac，而fvec不变。
         * @param p      用于提供其他额外需要的参数。
         * @return 如果为负值，则可中止“非线性最小二乘问题”的求解。
         */
        int call(int m, int n, IDoublePointer x, IDoublePointer fvec, IDoublePointer fjac,
                 int ldfjac, int iflag, IPointer... p);
    }

    /**
     * 接口{@code IMNNonlinearLeastSquaresConserving}是一个函数式接口，
     * 包装了一种“用户提供函数（user-supplied function）”，
     * 定义了“非线性最小二乘问题”计算式。<p>
     * 此接口被{@code lmstr}和{@code lmstr1}等函数作为参数使用，
     * 用于求解非线性最小二乘问题，但一次只计算一行雅可比矩阵，所以比较节约内存。<p>
     */
    public interface IMNNonlinearLeastSquaresConserving extends INonlinearFunctions {
        /**
         * 用户提供函数（user-supplied function）。<p>
         * 函数签名参考了<a href ="http://bytedeco.org/">JavaCPP</a>的API。
         *
         * @param m     “非线性最小二乘问题”计算式中方程的数量，该值应与{@code getM()}的返回值一致。
         * @param n     “非线性最小二乘问题”计算式中变量的数量，该值应与{@code getN()}的返回值一致,
         *              且要求{@code n<=m}。
         * @param x     “非线性最小二乘问题”计算式的变量值组，包含“非线性最小二乘问题”解的初始值或经求解后则为最终的解值。
         * @param fvec  “非线性最小二乘问题”计算式的方程计算值组，即变量为{@code x}时方程计算值。
         * @param fjrow 方程的雅可比矩阵。雅可比矩阵中的一行。
         * @param iflag 一个标记值，要求此值在{@code call}调用期间不应该被改变。
         *              如果iflag=1，则将方程计算值更新至fvec，而fjac不变。
         *              如果iflag=i，则将方程的雅可比矩阵第i-1行更新至fjrow，而fvec不变。
         * @param p     用于提供其他额外需要的参数。
         * @return 如果为负值，则可中止“非线性最小二乘问题”的求解。
         */
        int call(int m, int n, IDoublePointer x, IDoublePointer fvec,
                 IDoublePointer fjrow, int iflag, IPointer... p);
    }
    //=========================================================================

    //========================== boolean value in c/cpp language ===============
    /**
     * 在c/cpp语言中，非{@code 0}是"真"，即{@code true}。
     */
    public static final int TRUE = 1;

    /**
     * 在c/cpp语言中，{@code 0}是"假"，即{@code false}。
     */
    public static final int FALSE = 0;
    //===========================================================================

    //========================= machine precision ===============================
    /**
     * 值{@code 2.220446049250313E-16}是函数{@code doubleEpsilon()}的计算结果，
     * 用于表征“计算机运算的精度（the precision of the computer's arithmetic）”。
     */
    public static final double DOUBLE_EPSILON = 2.220446049250313E-16;

    /**
     * 获取{@code double}型数据的“舍入单位（round-off unit）”，
     * 舍入单位是一个数字（这里记为{@code R}），用于表征“计算机
     * 运算的精度（the precision of the computer's arithmetic）”。<p>
     * 舍入单位{@code R}应同时满足如下两个条件：<p>
     * （1）{@code 1 < 1 + R} <p>
     * （2）{@code 1 = ( 1 + R / 2 )} <p>
     *
     * @return {@code double}型数据的“舍入单位（round-off unit）”
     */
    public static double doubleEpsilon() {
        double value = 1.0;
        while (1.0 < (1.0 + value)) {
            value = value / 2.0;
        }
        return 2.0 * value;
    }
    //============================================================================

    //========================== dpmpar.c =========================================

    /**
     * 值{@code 2.2204460492503131e-16}用于表征“计算机
     * 运算的精度（the precision of the computer's arithmetic）”，
     * 该值与函数{@code doubleEpsilon()}的计算结果一致。
     * <p>
     * 参考自：dpmpar.c
     */
    public static final double DPMPAR1 = 2.2204460492503131e-16;

    /**
     * 值{@code 2.2250738585072014e-308}被用于表征一个非常小的{@code double}型数。 <p>
     * 该值大于{@code Double.MIN_VALUE}，其中，{@code Double.MIN_VALUE = 4.9E-324}。
     * <p>
     * 参考自：dpmpar.c
     */
    public static final double DPMPAR2 = 2.2250738585072014e-308;

    /**
     * 值{@code 1.7976931348623157e+308}用于表征一个非常大的{@code double}型值。 <p>
     * 该值等于{@code Double.MAX_VALUE} 。
     * <p>
     * 参考自：dpmpar.c
     */
    public static final double DPMPAR3 = 1.7976931348623157e+308;
    //=============================================================================

    //========================== enorm.c ==========================================
    /**
     * 值{@code 1.8269129119256895e-153}参考自enorm.c，
     * 它与函数{@code doubleDwarf()}的计算结果完全一致。
     */
    public static final double DOUBLE_DWARF = 1.8269129119256895e-153;

    /**
     * 值{@code 1.3407807929942596e+153}参考自enorm.c，
     * 它与函数{@code doubleGiant()}的计算结果完全一致。
     */
    public static final double DOUBLE_GIANT = 1.3407807929942596e+153;

    /**
     * 函数{@code doubleDwarf()}参考自 enorm.c，
     * 其文档指出该函数是由库<a href="http://www.physics.wisc.edu/~craigm/idl/fitting.html">
     * cmpfit-1.2</a>所提出。
     */
    public static double doubleDwarf() {
        return Math.sqrt(DPMPAR2 * 1.5) * 10;
    }

    /**
     * 函数{@code doubleGiant()}参考自 enorm.c，
     * 其文档指出该函数是由库<a href="http://www.physics.wisc.edu/~craigm/idl/fitting.html">
     * cmpfit-1.2</a>所提出。
     */
    public static double doubleGiant() {
        return Math.sqrt(DPMPAR3) * 0.1;
    }

    /**
     * 函数{@code enorm}用于计算一个向量的欧几里得范数（euclidean norm），
     * 一个向量的欧几里得范数实际上是向量内所有元素的平方和的开方。<p>
     * 函数各参数的含义如下： <p>
     * （1）参数{@code x}给出了一个向量，但该函数不是直接用向量中所有元素进行计算，
     * 而是只用向量中的一部分值进行计算，
     * 即实际上是计算了向量{@code x}中的一个子向量的欧几里得范数。<p>
     * （2）参数{@code n}指出所需计算的子向量的元素数量。<p>
     * （3）参数{@code startIndex}指出所需计算的子向量的第一个元素在向量{@code x}中的索引。
     * 基于以上函数参数的含义可知： <p>
     * 参数{@code x}给出的向量的长度应大于等于{@code startIndex + n}，否则，
     * 将抛出{@code ArrayIndexOutOfBoundsException}。
     * <p>
     * 函数参考自enorm.c，原程序并没有{@code startIndex}参数，但参数{@code x}是一个指针，
     * 其可以指向数组中任意元素的位置作为子向量的起点。而Java中没有指针，所以这里添加了一个
     * 参数{@code startIndex}作为替代方案。
     *
     * @param n          一个正整数值，输入型变量，用于指出所需计算子向量的元素个数。
     * @param x          一个数组，输入型变量，其长度应{@code >= (startIndex + n)}。
     * @param startIndex 一个非负整数型值，输入型变量，用于指出所需计算子向量的第一个元素在向量
     *                   {@code x}中的索引。
     * @return 向量{@code x}或其子向量的欧几里得范数。
     */
    public static double enorm(int n, IDoublePointer x, int startIndex) {
        double s1 = 0.;
        double s2 = 0.;
        double s3 = 0.;
        double x1max = 0.;
        double x3max = 0.;
        double agiant = DOUBLE_GIANT / (double) n;
        double xabs, d1, ret_val;

        //将指针指到指定的起始索引的位置。
        x.move(startIndex);

        for (int i = 0; i < n; i++) {
            xabs = abs(x.get(i));
            if (xabs >= agiant) {
                /* sum for large components. */
                if (xabs > x1max) {
                    /* Computing 2nd power */
                    d1 = x1max / xabs;
                    s1 = 1. + s1 * (d1 * d1);
                    x1max = xabs;
                } else {
                    /* Computing 2nd power */
                    d1 = xabs / x1max;
                    s1 += d1 * d1;
                }
            } else if (xabs <= DOUBLE_DWARF) {
                /* sum for small components. */
                if (xabs > x3max) {
                    /* Computing 2nd power */
                    d1 = x3max / xabs;
                    s3 = 1. + s3 * (d1 * d1);
                    x3max = xabs;
                } else if (xabs != 0.) {
                    /* Computing 2nd power */
                    d1 = xabs / x3max;
                    s3 += d1 * d1;
                }
            } else {
                /* sum for intermediate components. */
                /* Computing 2nd power */
                s2 += xabs * xabs;
            }
        }

        /* calculation of norm. */
        if (s1 != 0.) {
            ret_val = x1max * Math.sqrt(s1 + (s2 / x1max) / x1max);
        } else if (s2 != 0.) {
            if (s2 >= x3max) {
                ret_val = Math.sqrt(s2 * (1. + (x3max / s2) * (x3max * s3)));
            } else {
                ret_val = Math.sqrt(x3max * ((s2 / x3max) + (x3max * s3)));
            }
        } else {
            ret_val = x3max * Math.sqrt(s3);
        }
        return ret_val;
    }

    /**
     * 重载函数{@code enorm(int n, double[] x, int startIndex)}，
     * 其中，参数{@code startIndex}被设置为{@code 0}。
     *
     * @param n 一个正整数值，输入型变量，用于指出所需计算子向量的元素个数。
     * @param x 一个数组，输入型变量，其长度应{@code >= n}。
     * @return 向量{@code x}或其子向量的欧几里得范数。
     */
    public static double enorm(int n, IDoublePointer x) {
        return enorm(n, x, 0);
    }

    /**
     * 重载函数{@code enorm(int n, double[] x, int startIndex)}，
     * 其中，参数{@code startIndex}被设置为{@code 0}，参数{@code n}
     * 被设置为{@code x}的长度，即{@code x.length}。
     *
     * @param x 一个数组，输入型变量，其长度应{@code >= n}。
     * @return 向量{@code x}或其子向量的欧几里得范数。
     */
    public static double enorm(IDoublePointer x) {
        if (x.getPoint() <= 0) {
            return enorm(x.getCapacity(), x, 0);
        } else if (x.getPoint() < x.getCapacity()) {
            return enorm(x.getCapacity() - x.getPoint(), x, 0);
        } else {
            throw new IllegalArgumentException();
        }
    }

    //=========================================================================
    public static void rwupdt(int n, IDoublePointer r, int ldr, final IDoublePointer w, IDoublePointer b,
                              IDoublePointer alpha, IDoublePointer cos, IDoublePointer sin) {

        /* Initialized data */
        double p5 = .5;
        double p25 = .25;

        /* System generated locals */
        int r_dim1, r_offset;

        /* Local variables */
        int i, j, jm1;
        double tan, temp, rowj, cotan;

        /* Parameter adjustments */
        sin.move(-1); // --sin;
        cos.move(-1); // --cos;
        b.move(-1); // --b;
        w.move(-1); // --w;
        r_dim1 = ldr;
        r_offset = 1 + r_dim1;
        r.move(-r_offset); //r -= r_offset;

        /* Function Body */
        for (j = 1; j <= n; ++j) {
            rowj = w.get(j);
            jm1 = j - 1;

            /* apply the previous transformations to */
            /* r(i,j), i=1,2,...,j-1, and to w(j). */
            if (jm1 >= 1) {
                for (i = 1; i <= jm1; ++i) {
                    temp = cos.get(i) * r.get(i + j * r_dim1) + sin.get(i) * rowj;
                    rowj = -sin.get(i) * r.get(i + j * r_dim1) + cos.get(i) * rowj;
                    r.set(i + j * r_dim1, temp);
                }
            }

            /* determine a givens rotation which eliminates w(j). */
            cos.set(j, 1.);
            sin.set(j, 0.);
            if (rowj != 0.) {
                if (abs(r.get(j + j * r_dim1)) < abs(rowj)) {
                    cotan = r.get(j + j * r_dim1) / rowj;
                    sin.set(j, p5 / sqrt(p25 + p25 * (cotan * cotan)));
                    cos.set(j, sin.get(j) * cotan);
                } else {
                    tan = rowj / r.get(j + j * r_dim1);
                    cos.set(j, p5 / sqrt(p25 + p25 * (tan * tan)));
                    sin.set(j, cos.get(j) * tan);
                }

                /* apply the current transformation to r(j,j), b(j), and alpha. */
                r.set(j + j * r_dim1, cos.get(j) * r.get(j + j * r_dim1) + sin.get(j) * rowj);
                temp = cos.get(j) * b.get(j) + sin.get(j) * alpha.get();
                alpha.set(-sin.get(j) * b.get(j) + cos.get(j) * alpha.get());
                b.set(j, temp);
            }
        }
        /* last card of subroutine rwupdt. */
    }

    public static void r1updt(int m, int n, IDoublePointer s, int ls, final IDoublePointer u,
                              IDoublePointer v, IDoublePointer w, IIntConstPointer sing) {

        /* Initialized data */
        double p5 = .5;
        double p25 = .25;

        /* Local variables */
        int i, j, l, jj, nm1;
        double tan;
        int nmj;
        double cos, sin, tau, temp, giant, cotan;

        /* Parameter adjustments */
        w.move(-1); //--w;
        u.move(-1); //--u;
        v.move(-1); //--v;
        s.move(-1); //--s;

        //(void)ls;

        /* Function Body */

        /* giant is the largest magnitude. */

        giant = DPMPAR3; //__cminpack_func__(dpmpar) (3);

        /* initialize the diagonal element pointer. */
        jj = n * ((m << 1) - n + 1) / 2 - (m - n);

        /* move the nontrivial part of the last column of s into w. */
        l = jj;
        for (i = n; i <= m; ++i) {
            w.set(i, s.get(l));//w[i] = s[l];
            ++l;
        }

        /* rotate the vector v into a multiple of the n-th unit vector */
        /* in such a way that a spike is introduced into w. */

        nm1 = n - 1;
        if (nm1 >= 1) {
            for (nmj = 1; nmj <= nm1; ++nmj) {
                j = n - nmj;
                jj -= m - j + 1;
                w.set(j, 0.);//w[j] = 0.;
                if (v.get(j) != 0.) {

                    /* determine a givens rotation which eliminates the */
                    /* j-th element of v. */

                    if (abs(v.get(n)) < abs(v.get(j))) {
                        cotan = v.get(n) / v.get(j);
                        sin = p5 / sqrt(p25 + p25 * (cotan * cotan));
                        cos = sin * cotan;
                        tau = 1.;
                        if (abs(cos) * giant > 1.) {
                            tau = 1. / cos;
                        }
                    } else {
                        tan = v.get(j) / v.get(n);
                        cos = p5 / sqrt(p25 + p25 * (tan * tan));
                        sin = cos * tan;
                        tau = sin;
                    }

                    /* apply the transformation to v and store the information */
                    /* necessary to recover the givens rotation. */
                    v.set(n, sin * v.get(j) + cos * v.get(n));
                    //v[n] = sin * v[j] + cos * v[n];
                    v.set(j, tau);
                    //v[j] = tau;

                    /* apply the transformation to s and extend the spike in w. */

                    l = jj;
                    for (i = j; i <= m; ++i) {
                        temp = cos * s.get(l) - sin * w.get(i);
                        w.set(i, sin * s.get(l) + cos * w.get(i));
                        s.set(l, temp);
                        ++l;
                    }
                }
            }
        }

        /* add the spike from the rank 1 update to w. */

        for (i = 1; i <= m; ++i) {
            w.set(i, w.get(i) + v.get(n) * u.get(i));
            //w[i] += v[n] * u[i];
        }

        /* eliminate the spike. */
        sing.set(0);
        //*sing = FALSE_;
        if (nm1 >= 1) {
            for (j = 1; j <= nm1; ++j) {
                if (w.get(j) != 0.) {

                    /* determine a givens rotation which eliminates the */
                    /* j-th element of the spike. */

                    if (abs(s.get(jj)) < abs(w.get(j))) {
                        cotan = s.get(jj) / w.get(j);
                        sin = p5 / sqrt(p25 + p25 * (cotan * cotan));
                        cos = sin * cotan;
                        tau = 1.;
                        if (abs(cos) * giant > 1.) {
                            tau = 1. / cos;
                        }
                    } else {
                        tan = w.get(j) / s.get(jj);
                        cos = p5 / sqrt(p25 + p25 * (tan * tan));
                        sin = cos * tan;
                        tau = sin;
                    }

                    /* apply the transformation to s and reduce the spike in w. */

                    l = jj;
                    for (i = j; i <= m; ++i) {
                        temp = cos * s.get(l) + sin * w.get(i);
                        w.set(i, -sin * s.get(l) + cos * w.get(i));
                        s.set(l, temp);
                        ++l;
                    }

                    /* store the information necessary to recover the */
                    /* givens rotation. */

                    w.set(j, tau);
                }

                /* test for zero diagonal elements in the output s. */

                if (s.get(jj) == 0.) {
                    sing.set(1);
                }
                jj += m - j + 1;
            }
        }

        /* move w back into the last column of the output s. */

        l = jj;
        for (i = n; i <= m; ++i) {
            s.set(l, w.get(i));
            ++l;
        }
        if (s.get(jj) == 0.) {
            sing.set(1); //TRUE_;
        }

        /* last card of subroutine r1updt. */
    }

    public static void r1mpyq(int m, int n, IDoublePointer a, int lda, final IDoublePointer v,
                              final IDoublePointer w) {
        /* System generated locals */
        int a_dim1, a_offset;

        /* Local variables */
        int i, j, nm1, nmj;
        double cos, sin, temp;

        /* Parameter adjustments */
        w.move(-1);// --w;
        v.move(-1);// --v;
        a_dim1 = lda;
        a_offset = 1 + a_dim1;
        a.move(-a_offset);
        // a -= a_offset;

        /* Function Body */

        /* apply the first set of givens rotations to a. */

        nm1 = n - 1;
        if (nm1 < 1) {
            return;
        }
        for (nmj = 1; nmj <= nm1; ++nmj) {
            j = n - nmj;
            if (abs(v.get(j)) > 1.) {
                cos = 1. / v.get(j);
                sin = sqrt(1. - cos * cos);
            } else {
                sin = v.get(j);
                cos = sqrt(1. - sin * sin);
            }
            for (i = 1; i <= m; ++i) {
                temp = cos * a.get(i + j * a_dim1) - sin * a.get(i + n * a_dim1);
                a.set(i + n * a_dim1, sin * a.get(i + j * a_dim1) + cos * a.get(i + n * a_dim1));
                a.set(i + j * a_dim1, temp);
            }
        }

        /* apply the second set of givens rotations to a. */

        for (j = 1; j <= nm1; ++j) {
            if (abs(w.get(j)) > 1.) {
                cos = 1. / w.get(j);
                sin = sqrt(1. - cos * cos);
            } else {
                sin = w.get(j);
                cos = sqrt(1. - sin * sin);
            }
            for (i = 1; i <= m; ++i) {
                temp = cos * a.get(i + j * a_dim1) + sin * a.get(i + n * a_dim1);
                a.set(i + n * a_dim1, -sin * a.get(i + j * a_dim1) + cos * a.get(i + n * a_dim1));
                a.set(i + j * a_dim1, temp);
            }
        }
        /* last card of subroutine r1mpyq. */
    }

    public static void qrsolv(int n, IDoublePointer r, int ldr, final IIntConstPointer ipvt,
                              final IDoublePointer diag, final IDoublePointer qtb,
                              IDoublePointer x, IDoublePointer sdiag, IDoublePointer wa) {

        /* Initialized data */
        double p5 = .5;
        double p25 = .25;

        /* Local variables */
        int i, j, k, l;
        double cos, sin, sum, temp;
        int nsing;
        double qtbpj;

        /* copy r and (q transpose)*b to preserve input and initialize s. */
        /* in particular, save the diagonal elements of r in x. */

        for (j = 0; j < n; ++j) {
            for (i = j; i < n; ++i) {
                r.set(i + j * ldr, r.get(j + i * ldr));
            }
            x.set(j, r.get(j + j * ldr));
            wa.set(j, qtb.get(j));
        }

        /* eliminate the diagonal matrix d using a givens rotation. */

        for (j = 0; j < n; ++j) {

            /* prepare the row of d to be eliminated, locating the */
            /* diagonal element using p from the qr factorization. */

            l = ipvt.get(j) - 1;
            if (diag.get(l) != 0.) {
                for (k = j; k < n; ++k) {
                    sdiag.set(k, 0.);
                }
                sdiag.set(j, diag.get(l));

                /* the transformations to eliminate the row of d */
                /*modify only a single element of (q transpose)*b */
                /*beyond the first n, which is initially zero. */

                qtbpj = 0.;
                for (k = j; k < n; ++k) {

                    /* determine a givens rotation which eliminates the */
                    /* appropriate element in the current row of d. */

                    if (sdiag.get(k) != 0.) {
                        if (abs(r.get(k + k * ldr)) < abs(sdiag.get(k))) {
                            double cotan;
                            cotan = r.get(k + k * ldr) / sdiag.get(k);
                            sin = p5 / sqrt(p25 + p25 * (cotan * cotan));
                            cos = sin * cotan;
                        } else {
                            double tan;
                            tan = sdiag.get(k) / r.get(k + k * ldr);
                            cos = p5 / sqrt(p25 + p25 * (tan * tan));
                            sin = cos * tan;
                        }

                        /* compute the modified diagonal element of r and */
                        /* the modified element of ((q transpose)*b,0). */

                        temp = cos * wa.get(k) + sin * qtbpj;
                        qtbpj = -sin * wa.get(k) + cos * qtbpj;
                        wa.set(k, temp);

                        /* accumulate the tranformation in the row of s. */

                        r.set(k + k * ldr, cos * r.get(k + k * ldr) + sin * sdiag.get(k));

                        if (n > k + 1) {
                            for (i = k + 1; i < n; ++i) {
                                temp = cos * r.get(i + k * ldr) + sin * sdiag.get(i);
                                sdiag.set(i, -sin * r.get(i + k * ldr) + cos * sdiag.get(i));
                                r.set(i + k * ldr, temp);
                            }
                        }
                    }
                }
            }

            /* store the diagonal element of s and restore */
            /* the corresponding diagonal element of r. */
            sdiag.set(j, r.get(j + j * ldr));
            r.set(j + j * ldr, x.get(j));
        }

        /* solve the triangular system for z. if the system is */
        /* singular, then obtain a least squares solution. */
        nsing = n;
        for (j = 0; j < n; ++j) {
            if (sdiag.get(j) == 0. && nsing == n) {
                nsing = j;
            }
            if (nsing < n) {
                wa.set(j, 0.);
            }
        }
        if (nsing >= 1) {
            for (k = 1; k <= nsing; ++k) {
                j = nsing - k;
                sum = 0.;
                if (nsing > j + 1) {
                    for (i = j + 1; i < nsing; ++i) {
                        sum += r.get(i + j * ldr) * wa.get(i);
                    }
                }
                wa.set(j, (wa.get(j) - sum) / sdiag.get(j));
            }
        }

        /* permute the components of z back to components of x. */
        for (j = 0; j < n; ++j) {
            l = ipvt.get(j) - 1;
            x.set(l, wa.get(j));
        }
        /* last card of subroutine qrsolv. */
    }

    public static void qrfac(int m, int n, IDoublePointer a, int lda, int pivot,
                             IIntConstPointer ipvt, int lipvt, IDoublePointer rdiag,
                             IDoublePointer acnorm, IDoublePointer wa) {
        /* Initialized data */
        double p05 = .05;

        /* System generated locals */
        double d1;

        /* Local variables */
        int i, j, k, jp1;
        double sum;
        double temp;
        int minmn;
        double epsmch;
        double ajnorm;

        // (void) lipvt;

        /* epsmch is the machine precision. */

        epsmch = DPMPAR1;

        /* compute the initial column norms and initialize several arrays. */
        for (j = 0; j < n; ++j) {
            acnorm.set(j, enorm(m, a.copy(j * lda)));
            rdiag.set(j, acnorm.get(j));
            wa.set(j, rdiag.get(j));
            if (pivot != 0) {
                ipvt.set(j, j + 1);
            }
        }

        /* reduce a to r with householder transformations. */

        minmn = min(m, n);
        for (j = 0; j < minmn; ++j) {
            if (pivot != 0) {
                /* bring the column of largest norm into the pivot position. */
                int kmax = j;
                for (k = j; k < n; ++k) {
                    if (rdiag.get(k) > rdiag.get(kmax)) {
                        kmax = k;
                    }
                }
                if (kmax != j) {
                    for (i = 0; i < m; ++i) {
                        temp = a.get(i + j * lda);
                        a.set(i + j * lda, a.get(i + kmax * lda));
                        a.set(i + kmax * lda, temp);
                    }
                    rdiag.set(kmax, rdiag.get(j));
                    wa.set(kmax, wa.get(j));
                    k = ipvt.get(j);
                    ipvt.set(j, ipvt.get(kmax));
                    ipvt.set(kmax, k);
                }
            }

            /* compute the householder transformation to reduce the */
            /* j-th column of a to a multiple of the j-th unit vector. */

            ajnorm = enorm(m - (j + 1) + 1, a.copy(j + j * lda));
            if (ajnorm != 0.) {
                if (a.get(j + j * lda) < 0.) {
                    ajnorm = -ajnorm;
                }
                for (i = j; i < m; ++i) {
                    a.set(i + j * lda, a.get(i + j * lda) / ajnorm);
                }
                a.set(j + j * lda, a.get(j + j * lda) + 1);

                /* apply the transformation to the remaining columns */
                /* and update the norms. */

                jp1 = j + 1;
                if (n > jp1) {
                    for (k = jp1; k < n; ++k) {
                        sum = 0.;
                        for (i = j; i < m; ++i) {
                            sum += a.get(i + j * lda) * a.get(i + k * lda);
                        }
                        temp = sum / a.get(j + j * lda);
                        for (i = j; i < m; ++i) {
                            a.set(i + k * lda, a.get(i + k * lda) - temp * a.get(i + j * lda));
                        }
                        if (pivot != 0 && rdiag.get(k) != 0.) {
                            temp = a.get(j + k * lda) / rdiag.get(k);
                            /* Computing MAX */
                            d1 = 1. - temp * temp;
                            rdiag.set(k, rdiag.get(k) * sqrt((max(0., d1))));
                            /* Computing 2nd power */
                            d1 = rdiag.get(k) / wa.get(k);
                            if (p05 * (d1 * d1) <= epsmch) {
                                rdiag.set(k, enorm(m - (j + 1), a.copy(jp1 + k * lda)));
                                wa.set(k, rdiag.get(k));
                            }
                        }
                    }
                }
            }
            rdiag.set(j, -ajnorm);
        }
        /* last card of subroutine qrfac. */
    }


    public static void qform(int m, int n, IDoublePointer q, int ldq, IDoublePointer wa) {
        /* System generated locals */
        int q_dim1, q_offset;

        /* Local variables */
        int i, j, k, l, jm1, np1;
        double sum, temp;
        int minmn;

        /* Parameter adjustments */
        wa.move(-1); //--wa;
        q_dim1 = ldq;
        q_offset = 1 + q_dim1;
        q.move(q_offset);
        //q -= q_offset;

        /* Function Body */

        /* zero out upper triangle of q in the first min(m,n) columns. */

        minmn = min(m, n);
        if (minmn >= 2) {
            for (j = 2; j <= minmn; ++j) {
                jm1 = j - 1;
                for (i = 1; i <= jm1; ++i) {
                    q.set(i + j * q_dim1, 0.);
                }
            }
        }

        /* initialize remaining columns to those of the identity matrix. */

        np1 = n + 1;
        if (m >= np1) {
            for (j = np1; j <= m; ++j) {
                for (i = 1; i <= m; ++i) {
                    q.set(i + j * q_dim1, 0.);
                }
                q.set(j + j * q_dim1, 1.);
            }
        }

        /* accumulate q from its factored form. */

        for (l = 1; l <= minmn; ++l) {
            k = minmn - l + 1;
            for (i = k; i <= m; ++i) {
                wa.set(i, q.get(i + k * q_dim1));
                q.set(i + k * q_dim1, 0.);
            }
            q.set(k + k * q_dim1, 1.0);
            if (wa.get(k) != 0.) {
                for (j = k; j <= m; ++j) {
                    sum = 0.;
                    for (i = k; i <= m; ++i) {
                        sum += q.get(i + j * q_dim1) * wa.get(i);
                    }
                    temp = sum / wa.get(k);
                    for (i = k; i <= m; ++i) {
                        q.set(i + j * q_dim1, q.get(i + j * q_dim1) - temp * wa.get(i));
                    }
                }
            }
        }
        /* last card of subroutine qform. */
    }

    public static void chkder(int m, int n, final IDoublePointer x, IDoublePointer fvec,
                              IDoublePointer fjac, int ldfjac, IDoublePointer xp,
                              IDoublePointer fvecp, int mode, IDoublePointer err) {

        double log10e = 0.43429448190325182765;
        double factor = 100.;

        /* Local variables */
        int i, j;
        double eps, epsf, temp, epsmch;
        double epslog;

        /*epsmch is the machine precision. */

        epsmch = DPMPAR1;

        eps = sqrt(epsmch);

        if (mode != 2) {

            /* mode = 1. */
            for (j = 0; j < n; ++j) {
                temp = eps * abs(x.get(j));
                if (temp == 0.) {
                    temp = eps;
                }
                xp.set(j, x.get(j) + temp);
            }
            return;
        }

        /* mode = 2. */
        epsf = factor * epsmch;
        epslog = log10e * log(eps);
        for (i = 0; i < m; ++i) {
            err.set(i, 0.);
        }
        for (j = 0; j < n; ++j) {
            temp = abs(x.get(j));
            if (temp == 0.) {
                temp = 1.;
            }
            for (i = 0; i < m; ++i) {
                err.set(i, err.get(i) + temp * fjac.get(i + j * ldfjac));
            }
        }
        for (i = 0; i < m; ++i) {
            temp = 1.;
            if (fvec.get(i) != 0. && fvecp.get(i) != 0. &&
                    abs(fvecp.get(i) - fvec.get(i)) >= epsf * abs(fvec.get(i))) {
                temp = eps * abs((fvecp.get(i) - fvec.get(i)) / eps - err.get(i)) / (abs(fvec.get(i)) + abs(fvecp.get(i)));
            }
            err.set(i, 1.);
            if (temp > epsmch && temp < eps) {
                err.set(i, (log10e * log(temp) - epslog) / epslog);
            }
            if (temp >= eps) {
                err.set(i, 0.);
            }
        }
        /*last card of subroutine chkder. */
    }

    public static void covar(int n, IDoublePointer r, int ldr, final IIntConstPointer ipvt,
                             double tol, IDoublePointer wa) {
        /* Local variables */
        int i, j, k, l, ii, jj;
        boolean sing;
        double temp, tolr;

        tolr = tol * abs(r.get(0));

        /* form the inverse of r in the full upper triangle of r. */

        l = -1;
        for (k = 0; k < n; ++k) {
            if (abs(r.get(k + k * ldr)) <= tolr) {
                break;
            }
            r.set(k + k * ldr, 1. / r.get(k + k * ldr));
            if (k > 0) {
                for (j = 0; j < k; ++j) {
                    // coverity[copy_paste_error]
                    temp = r.get(k + k * ldr) * r.get(j + k * ldr);
                    r.set(j + k * ldr, 0.);
                    for (i = 0; i <= j; ++i) {
                        r.set(i + k * ldr, r.get(i + k * ldr) - temp * r.get(i + j * ldr));
                    }
                }
            }
            l = k;
        }

        /* form the full upper triangle of the inverse of (r transpose)*r */
        /* in the full upper triangle of r. */

        if (l >= 0) {
            for (k = 0; k <= l; ++k) {
                if (k > 0) {
                    for (j = 0; j < k; ++j) {
                        temp = r.get(j + k * ldr);
                        for (i = 0; i <= j; ++i) {
                            r.set(i + j * ldr, r.get(i + j * ldr) + temp * r.get(i + k * ldr));
                        }
                    }
                }
                temp = r.get(k + k * ldr);
                for (i = 0; i <= k; ++i) {
                    r.set(i + k * ldr, r.get(i + k * ldr) * temp);
                }
            }
        }

        /* form the full lower triangle of the covariance matrix */
        /* in the strict lower triangle of r and in wa. */

        for (j = 0; j < n; ++j) {
            jj = ipvt.get(j) - 1;
            sing = j > l;
            for (i = 0; i <= j; ++i) {
                if (sing) {
                    r.set(i + j * ldr, 0.);
                }
                ii = ipvt.get(j) - 1;
                if (ii > jj) {
                    r.set(ii + jj * ldr, r.get(i + j * ldr));
                } else if (ii < jj) {
                    r.set(jj + ii * ldr, r.get(i + j * ldr));
                }
            }
            wa.set(jj, r.get(j + j * ldr));
        }

        /* symmetrize the covariance matrix in r. */

        for (j = 0; j < n; ++j) {
            for (i = 0; i < j; ++i) {
                r.set(i + j * ldr, r.get(j + i * ldr));
            }
            r.set(j + j * ldr, wa.get(j));
        }
        /* last card of subroutine covar. */
    }

    public static int covar1(int m, int n, double fsumsq, IDoublePointer r, int ldr,
                             final IIntConstPointer ipvt, double tol, IDoublePointer wa) {

        /* Local variables */
        int i, j, k, l, ii, jj;
        boolean sing;
        double temp, tolr;

        /* ********** */
        tolr = tol * abs(r.get(0));

        /* form the inverse of r in the full upper triangle of r. */

        l = -1;
        for (k = 0; k < n; ++k) {
            if (abs(r.get(k + k * ldr)) <= tolr) {
                break;
            }
            r.set(k + k * ldr, 1. / r.get(k + k * ldr));
            if (k > 0) {
                for (j = 0; j < k; ++j) {
                    // coverity[copy_paste_error]
                    temp = r.get(k + k * ldr) * r.get(j + k * ldr);
                    r.set(j + k * ldr, 0.);
                    for (i = 0; i <= j; ++i) {
                        r.set(i + k * ldr, r.get(i + k * ldr) - temp * r.get(i + j * ldr));
                    }
                }
            }
            l = k;
        }

        /* form the full upper triangle of the inverse of (r transpose)*r */
        /* in the full upper triangle of r. */

        if (l >= 0) {
            for (k = 0; k <= l; ++k) {
                if (k > 0) {
                    for (j = 0; j < k; ++j) {
                        temp = r.get(j + k * ldr);
                        for (i = 0; i <= j; ++i) {
                            r.set(i + j * ldr, r.get(i + j * ldr) + temp * r.get(i + k * ldr));
                        }
                    }
                }
                temp = r.get(k + k * ldr);
                for (i = 0; i <= k; ++i) {
                    r.set(i + k * ldr, r.get(i + k * ldr) * temp);
                }
            }
        }

        /* form the full lower triangle of the covariance matrix */
        /* in the strict lower triangle of r and in wa. */

        for (j = 0; j < n; ++j) {
            jj = ipvt.get(j) - 1;
            sing = j > l;
            for (i = 0; i <= j; ++i) {
                if (sing) {
                    r.set(i + j * ldr, 0.);
                }
                ii = ipvt.get(i) - 1;
                if (ii > jj) {
                    r.set(ii + jj * ldr, r.get(i + j * ldr));
                } else if (ii < jj) {
                    r.set(jj + ii * ldr, r.get(i + j * ldr));
                }
            }
            wa.set(jj, r.get(j + j * ldr));
        }

        /* symmetrize the covariance matrix in r. */

        temp = fsumsq / (m - (l + 1));
        for (j = 0; j < n; ++j) {
            for (i = 0; i < j; ++i) {
                r.set(j + i * ldr, r.get(j + i * ldr) * temp);
                r.set(i + j * ldr, r.get(j + i * ldr));
            }
            r.set(j + j * ldr, temp * wa.get(j));
        }

        /* last card of subroutine covar. */
        if (l == (n - 1)) {
            return 0;
        }
        return l + 1;
    }

    public static void dogleg(int n, final IDoublePointer r, int lr, final IDoublePointer diag,
                              final IDoublePointer qtb, double delta, IDoublePointer x,
                              IDoublePointer wa1, IDoublePointer wa2) {
        /* System generated locals */
        double d1, d2, d3, d4;

        /* Local variables */
        int i, j, k, l, jj, jp1;
        double sum, temp, alpha, bnorm;
        double gnorm, qnorm, epsmch;
        double sgnorm;

        /* Parameter adjustments */
        wa2.move(-1);  //--wa2;
        wa1.move(-1);  //--wa1;
        x.move(-1);    //--x;
        qtb.move(-1);  //--qtb;
        diag.move(-1); //--diag;
        r.move(-1);    //--r;
        // (void) lr;

        /* Function Body */

        /* epsmch is the machine precision. */
        epsmch = DPMPAR1;

        /* first, calculate the gauss-newton direction. */

        jj = n * (n + 1) / 2 + 1;
        for (k = 1; k <= n; ++k) {
            j = n - k + 1;
            jp1 = j + 1;
            jj -= k;
            l = jj + 1;
            sum = 0.;
            if (n >= jp1) {
                for (i = jp1; i <= n; ++i) {
                    sum += r.get(l) * x.get(i);
                    ++l;
                }
            }
            temp = r.get(jj);
            if (temp == 0.) {
                l = j;
                for (i = 1; i <= j; ++i) {
                    /* Computing MAX */
                    d2 = abs(r.get(l));
                    temp = max(temp, d2);
                    l = l + n - i;
                }
                temp = epsmch * temp;
                if (temp == 0.) {
                    temp = epsmch;
                }
            }
            x.set(j, (qtb.get(j) - sum) / temp);
        }

        /* test whether the gauss-newton direction is acceptable. */

        for (j = 1; j <= n; ++j) {
            wa1.set(j, 0.);
            wa2.set(j, diag.get(j) * x.get(j));
        }
        qnorm = enorm(n, wa2.copy(1));
        if (qnorm <= delta) {
            return;
        }

        /* the gauss-newton direction is not acceptable. */
        /* next, calculate the scaled gradient direction. */

        l = 1;
        for (j = 1; j <= n; ++j) {
            temp = qtb.get(j);
            for (i = j; i <= n; ++i) {
                wa1.set(i, wa1.get(i) + r.get(l) * temp);
                ++l;
            }
            wa1.set(j, wa1.get(j) / diag.get(j));
        }

        /*calculate the norm of the scaled gradient and test for */
        /*the special case in which the scaled gradient is zero. */

        gnorm = enorm(n, wa1.copy(1));
        sgnorm = 0.;
        alpha = delta / qnorm;
        if (gnorm != 0.) {

            /* calculate the point along the scaled gradient */
            /* at which the quadratic is minimized. */

            for (j = 1; j <= n; ++j) {
                wa1.set(j, wa1.get(j) / gnorm / diag.get(j));
            }
            l = 1;
            for (j = 1; j <= n; ++j) {
                sum = 0.;
                for (i = j; i <= n; ++i) {
                    sum += r.get(l) * wa1.get(i);
                    ++l;
                }
                wa2.set(j, sum);
            }
            temp = enorm(n, wa2.copy().move(1));
            sgnorm = gnorm / temp / temp;

            /* test whether the scaled gradient direction is acceptable. */

            alpha = 0.;
            if (sgnorm < delta) {

                /* the scaled gradient direction is not acceptable. */
                /* finally, calculate the point along the dogleg */
                /* at which the quadratic is minimized. */

                bnorm = enorm(n, qtb.copy().move(1));
                temp = bnorm / gnorm * (bnorm / qnorm) * (sgnorm / delta);
                /* Computing 2nd power */
                d1 = sgnorm / delta;
                /* Computing 2nd power */
                d2 = temp - delta / qnorm;
                /* Computing 2nd power */
                d3 = delta / qnorm;
                /* Computing 2nd power */
                d4 = sgnorm / delta;
                temp = temp - delta / qnorm * (d1 * d1)
                        + sqrt(d2 * d2
                        + (1. - d3 * d3) * (1. - d4 * d4));
                /* Computing 2nd power */
                d1 = sgnorm / delta;
                alpha = delta / qnorm * (1. - d1 * d1) / temp;
            }
        }

        /* form appropriate convex combination of the gauss-newton */
        /* direction and the scaled gradient direction. */

        temp = (1. - alpha) * min(sgnorm, delta);
        for (j = 1; j <= n; ++j) {
            x.set(j, temp * wa1.get(j) + alpha * x.get(j));
        }

        /* last card of subroutine dogleg. */
    }


    public static int fdjac1(INNonlinearEquations fcn, int n, IDoublePointer x, final IDoublePointer fvec,
                             IDoublePointer fjac, int ldfjac, int ml, int mu, double epsfcn,
                             IDoublePointer wa1, IDoublePointer wa2) {
        /* System generated locals */
        int fjac_dim1, fjac_offset;

        /* Local variables */
        double h;
        int i, j, k;
        double eps, temp;
        int msum;
        double epsmch;
        int iflag = 0;

        /* Parameter adjustments */
        wa2.move(-1);// --wa2;
        wa1.move(-1);// --wa1;
        fvec.move(-1);// --fvec;
        x.move(-1);// --x;
        fjac_dim1 = ldfjac;
        fjac_offset = 1 + fjac_dim1;
        fjac.move(-fjac_offset);// fjac -= fjac_offset;

        /* Function Body */

        /*epsmch is the machine precision. */

        epsmch = DPMPAR1;

        eps = sqrt((max(epsfcn, epsmch)));
        msum = ml + mu + 1;
        if (msum >= n) {

            /* computation of dense approximate jacobian. */

            for (j = 1; j <= n; ++j) {
                temp = x.get(j);
                h = eps * abs(temp);
                if (h == 0.) {
                    h = eps;
                }
                x.set(j, temp + h);
            /* the last parameter of fcn_nn() is set to 2 to differentiate
               calls made to compute the function from calls made to compute
               the Jacobian (see fcn() in examples/hybdrv.c, and how njev
               is used to compute the number of Jacobian evaluations) */
                iflag = fcn.call(n, x.copy(1), wa1.copy(1), 2);
                if (iflag < 0) {
                    return iflag;
                }
                x.set(j, temp);
                for (i = 1; i <= n; ++i) {
                    fjac.set(i + j * fjac_dim1, (wa1.get(i) - fvec.get(i)) / h);
                }
            }
            return 0;
        }

        /* computation of banded approximate jacobian. */

        for (k = 1; k <= msum; ++k) {
            for (j = k; j <= n; j += msum) {
                wa2.set(j, x.get(j));
                h = eps * abs(wa2.get(j));
                if (h == 0.) {
                    h = eps;
                }
                x.set(j, wa2.get(j) + h);
            }

            iflag = fcn.call(n, x.copy(1), wa1.copy(1), 1);
            if (iflag < 0) {
                return iflag;
            }
            for (j = k; j <= n; j += msum) {
                x.set(j, wa2.get(j));
                h = eps * abs(wa2.get(j));
                if (h == 0.) {
                    h = eps;
                }
                for (i = 1; i <= n; ++i) {
                    fjac.set(i + j * fjac_dim1, 0.);
                    if (i >= j - mu && i <= j + ml) {
                        fjac.set(i + j * fjac_dim1, (wa1.get(i) - fvec.get(i)) / h);
                    }
                }
            }
        }
        return 0;
        /* last card of subroutine fdjac1. */
    }

    public static int fdjac2(IMNNonlinearLeastSquares fcn, int m, int n, IDoublePointer x,
                             final IDoublePointer fvec, IDoublePointer fjac, int ldfjac,
                             double epsfcn, IDoublePointer wa) {
        /* Local variables */
        double h;
        int i, j;
        double eps, temp, epsmch;
        int iflag;
        /* epsmch is the machine precision. */

        epsmch = DPMPAR1;

        eps = sqrt((max(epsfcn, epsmch)));
        for (j = 0; j < n; ++j) {
            temp = x.get(j);
            h = eps * abs(temp);
            if (h == 0.) {
                h = eps;
            }
            x.set(j, temp + h);
        /* the last parameter of fcn_mn() is set to 2 to differentiate
           calls made to compute the function from calls made to compute
           the Jacobian (see fcn() in examples/lmfdrv.c, and how njev
           is used to compute the number of Jacobian evaluations) */
            iflag = fcn.call(m, n, x, wa, 2);
            if (iflag < 0) {
                return iflag;
            }
            x.set(j, temp);
            for (i = 0; i < m; ++i) {
                fjac.set(i + j * ldfjac, (wa.get(i) - fvec.get(i)) / h);
            }
        }
        return 0;
        /* last card of subroutine fdjac2. */
    }

    /**
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
     * 此方法中，参数 ipvt,diag,qtb三个指针都是常量指针，意味着这些指针指向的值是不可修改的。
     */
    public static void lmpar(final int n, IDoublePointer r, final int ldr,
                             final IIntConstPointer ipvt, final IDoublePointer diag,
                             final IDoublePointer qtb, final double delta,
                             final IDoublePointer par, final IDoublePointer x,
                             final IDoublePointer sdiag, final IDoublePointer wa1,
                             final IDoublePointer wa2) {

        /* Initialized data */
        double p1 = .1;
        double p001 = .001;

        /* System generated locals */
        double d1, d2;

        /* Local variables */
        int j, l;
        double fp;
        double parc, parl;
        int iter;
        double temp, paru, dwarf;
        int nsing;
        double gnorm;
        double dxnorm;

        /* dwarf is the smallest positive magnitude. */
        dwarf = DPMPAR2;

        /* compute and store in x the gauss-newton direction. if the */
        /* jacobian is rank-deficient, obtain a least squares solution. */
        nsing = n;
        for (j = 0; j < n; ++j) {
            wa1.set(j, qtb.get(j));
            if (r.get(j + j * ldr) == 0. && nsing == n) {
                nsing = j;
            }
            if (nsing < n) {
                wa1.set(j, 0.);
            }
        }
        if (nsing >= 1) {
            for (int k = 1; k <= nsing; ++k) {
                j = nsing - k;
                wa1.set(j, wa1.get(j) / r.get(j + j * ldr));
                temp = wa1.get(j);
                if (j >= 1) {
                    for (int i = 0; i < j; ++i) {
                        wa1.set(i, wa1.get(i) - r.get(i + j * ldr) * temp);
                    }
                }
            }
        }
        for (j = 0; j < n; ++j) {
            l = ipvt.get(j) - 1;
            x.set(l, wa1.get(j));
        }

        /* initialize the iteration counter. */
        /* evaluate the function at the origin, and test */
        /* for acceptance of the gauss-newton direction. */
        iter = 0;
        for (j = 0; j < n; ++j) {
            wa2.set(j, diag.get(j) * x.get(j));
        }
        dxnorm = enorm(n, wa2);
        fp = dxnorm - delta;
        if (fp <= p1 * delta) {
            //iter==0，所以这里不用判断，一定执行：par.set(0, 0.);
            // goto TERMINATE;
            par.set(0, 0.);
            return;
        }

        /* if the jacobian is not rank deficient, the newton */
        /* step provides a lower bound, parl, for the zero of */
        /* the function. otherwise set this bound to zero. */
        parl = 0.;
        if (nsing >= n) {
            for (j = 0; j < n; ++j) {
                l = ipvt.get(j) - 1;
                wa1.set(j, diag.get(l) * (wa2.get(l) / dxnorm));
            }
            for (j = 0; j < n; ++j) {
                double sum = 0.;
                if (j >= 1) {
                    for (int i = 0; i < j; ++i) {
                        sum += r.get(i + j * ldr) * wa1.get(i);
                    }
                }
                wa1.set(j, (wa1.get(j) - sum) / r.get(j + j * ldr));
            }
            temp = enorm(n, wa1);
            parl = fp / delta / temp / temp;
        }

        /* calculate an upper bound, paru, for the zero of the function. */
        for (j = 0; j < n; ++j) {
            double sum = 0.;
            for (int i = 0; i <= j; ++i) {
                sum += r.get(i + j * ldr) * qtb.get(i);
            }
            l = ipvt.get(j) - 1;
            wa1.set(j, sum / diag.get(l));
        }
        gnorm = enorm(n, wa1);
        paru = gnorm / delta;
        if (paru == 0.) {
            paru = dwarf / min(delta, p1);
        }

        /* if the input par lies outside of the interval (parl,paru), */
        /* set par to the closer endpoint. */
        par.set(max(par.get(), parl));
        par.set(min(par.get(), paru));
        if (par.get() == 0.) {
            par.set(gnorm / dxnorm);
        }

        /* beginning of an iteration. */
        for (; ; ) {
            ++iter;
            /* evaluate the function at the current value of par. */
            if (par.get() == 0.) {
                /* Computing MAX */
                d1 = dwarf;
                d2 = p001 * paru;
                par.set(max(d1, d2));
            }
            temp = sqrt(par.get());
            for (j = 0; j < n; ++j) {
                wa1.set(j, temp * diag.get(j));
            }
            qrsolv(n, r, ldr, ipvt, wa1, qtb, x, sdiag, wa2);
            for (j = 0; j < n; ++j) {
                wa2.set(j, diag.get(j) * x.get(j));
            }
            dxnorm = enorm(n, wa2);
            temp = fp;
            fp = dxnorm - delta;

            /* if the function is small enough, accept the current value */
            /* of par. also test for the exceptional cases where parl */
            /* is zero or the number of iterations has reached 10. */
            if (abs(fp) <= p1 * delta || (parl == 0. && fp <= temp && temp < 0.) || iter == 10) {
                /*iter == 10的条件，使这一句永远无法执行到。
                // goto TERMINATE;
                if (iter == 0) {
                    par.set(0.);
                }
                */
                return;
            }

            /* compute the newton correction. */
            for (j = 0; j < n; ++j) {
                l = ipvt.get(j) - 1;
                wa1.set(j, diag.get(l) * (wa2.get(l) / dxnorm));
            }
            for (j = 0; j < n; ++j) {
                wa1.set(j, wa1.get(j) / sdiag.get(j));
                temp = wa1.get(j);
                if (n > j + 1) {
                    for (int i = j + 1; i < n; ++i) {
                        wa1.set(i, wa1.get(i) - r.get(i + j * ldr) * temp);
                    }
                }
            }
            temp = enorm(n, wa1);
            parc = fp / delta / temp / temp;

            /* depending on the sign of the function, update parl or paru. */
            if (fp > 0.) {
                parl = max(parl, par.get());
            }
            if (fp < 0.) {
                paru = min(paru, par.get());
            }

            /* compute an improved estimate for par. */
            /* Computing MAX */
            d1 = parl;
            d2 = par.get() + parc;
            par.set(max(d1, d2));

            /* end of an iteration. */
        }

        /*
        因为iter始终不等于0，所以，这一句始终无法执行。
        TERMINATE:
        if(iter == 0){
            par.set(0.);
        }
        */

        /* last card of subroutine lmpar. */
    }
}
