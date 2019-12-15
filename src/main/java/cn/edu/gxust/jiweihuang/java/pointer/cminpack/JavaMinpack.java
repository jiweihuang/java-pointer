/*
 *                             Apache License
 *                        Version 2.0, January 2004
 *                     http://www.apache.org/licenses/
 *
 * Copyright (c) 2019-2020, Jiwei Huang. All Rights Reserved.
 *
 * This file is part of JavaMinpack (https://github.com/gxusthjw/JavaMinpack).
 *
 *  ------------------------- Contact Author ----------------------------------
 *  Author: Jiwei Huang.
 *  Organization: Guangxi University of Science and Technology
 *  Postcode：545006
 *  Contact number：0772-2687033(School Office)  0772-2687033（Fax）
 *  Address：#268 Avenue Donghuan, Chengzhong District, Liuzhou, Guangxi, China
 *  ---------------------------------------------------------------------------
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package cn.edu.gxust.jiweihuang.java.pointer.cminpack;

import cn.edu.gxust.jiweihuang.java.pointer.IDoublePointer;
import cn.edu.gxust.jiweihuang.java.pointer.IFunctionPointer;
import cn.edu.gxust.jiweihuang.java.pointer.IIntPointer;
import cn.edu.gxust.jiweihuang.java.pointer.IPointer;

import static java.lang.Math.*;

/**
 * 类{@code JavaMinpackPointer}采用Java语言和<a href="https://github.com/gxusthjw/JavaPointer">JavaPointer</a>库
 * 重新实现了<a href="https://github.com/devernay/cminpack">cminpack</a>库。<p>
 * cminpack包是一种C语言实现，源于Fortran编写的<a href="http://www.netlib.org/minpack/">minpack</a>库。
 *
 * @author JiweiHuang
 * @since 20191102
 */
public class JavaMinpack {

    //========================== function interface ==============================================

    /**
     * The interface {@code IFunctionNNPointer} packages user-supplied function.
     * It inherits from {@code IFunctionPointer} of
     * <a href="https://github.com/gxusthjw/JavaPointer">JavaPointer library </a>
     * for using the facilities provided.<p>
     * It is used by {@code fdjac1}, {@code hybrd} and {@code hybrd1} in
     * {@code JavaMinpack}.<p>
     *
     * @author JiweiHuang
     * @since 20191023
     */
    public interface IFunctionNNPointer extends IFunctionPointer {
        /**
         * The user-supplied function.
         * <p>
         * Notes: the function signature references which of
         * <a href ="http://bytedeco.org/"> javacpp</a> api.
         */
        int call(int n, IDoublePointer x, IDoublePointer fvec,
                 int iflag, IPointer... p);
    }

    /**
     * The interface {@code IFunctionMNPointer} packages user-supplied function.<p>
     * It inherits from {@code IFunctionPointer} of
     * <a href="https://github.com/gxusthjw/JavaPointer">JavaPointer library </a>
     * for using the facilities provided.<p>
     * It is used by {@code fdjac2},{@code lmdif} and {@code lmdif1} in
     * {@code JavaMinpack}.<p>
     *
     * @author JiweiHuang
     * @since 20191023
     */
    public interface IFunctionMNPointer extends IFunctionPointer {
        /**
         * The user-supplied function.
         * <p>
         * Notes: the function signature references which of
         * <a href ="http://bytedeco.org/"> javacpp</a> api.
         */
        int call(int m, int n, IDoublePointer x, IDoublePointer fvec,
                 int iflag, IPointer... p);
    }

    /**
     * The interface {@code IFunctionDerNNPointer} packages user-supplied function.<p>
     * It inherits from {@code IFunctionPointer} of
     * <a href="https://github.com/gxusthjw/JavaPointer">JavaPointer library </a>
     * for using the facilities provided.<p>
     * It is used by {@code hybrj} and {@code hybrj1} in {@code JavaMinpack}.<p>
     *
     * @author JiweiHuang
     * @since 20191023
     */
    public interface IFunctionDerNNPointer extends IFunctionPointer {
        /**
         * The user-supplied function.
         * <p>
         * Notes: the function signature references which of
         * <a href ="http://bytedeco.org/"> javacpp</a> api.
         */
        int call(int n, IDoublePointer x, IDoublePointer fvec, IDoublePointer fjac,
                 int ldfjac, int iflag, IPointer... p);
    }

    /**
     * The interface {@code IFunctionDerMNPointer} packages a user-supplied function.<p>
     * It inherits from {@code IFunctionPointer} of
     * <a href="https://github.com/gxusthjw/JavaPointer">JavaPointer library </a>
     * for using the facilities provided.<p>
     * It is used by {@code lmder} and {@code lmder1} in {@code JavaMinpack}.<p>
     *
     * @author JiweiHuang
     * @since 20191023
     */
    public interface IFunctionDerMNPointer extends IFunctionPointer {
        /**
         * The user-supplied function.
         * <p>
         * Notes: the function signature references which of
         * <a href ="http://bytedeco.org/"> javacpp</a> api.
         */
        int call(int m, int n, IDoublePointer x, IDoublePointer fvec, IDoublePointer fjac,
                 int ldfjac, int iflag, IPointer... p);
    }

    /**
     * The interface {@code IFunctionDerStrMNPointer} packages user-supplied function.<p>
     * It inherits from {@code IFunctionPointer} of
     * <a href="https://github.com/gxusthjw/JavaPointer">JavaPointer library </a>
     * for using the facilities provided.<p>
     * It is used by {@code lmstr} and {@code lmstr1} in {@code JavaMinpack}.<p>
     *
     * @author JiweiHuang
     * @since 20191023
     */
    public interface IFunctionDerStrMNPointer extends IFunctionPointer {
        /**
         * The user-supplied function.
         * <p>
         * Notes: the function signature references which of
         * <a href ="http://bytedeco.org/"> javacpp</a> api.
         */
        int call(int m, int n, IDoublePointer x, IDoublePointer fvec,
                 IDoublePointer fjrow, int iflag, IPointer... p);
    }
    //=========================================================================
//========================== boolean value in c/cpp language ==================================
    /**
     * Not {@code 0} is true in c/cpp language.
     */
    public static final int TRUE = 1;
    /**
     * {@code 0} is false in c/cpp language.
     */
    public static final int FALSE = 0;
    //=============================================================================================

    //========================= machine precision =================================================
    /**
     * The value {@code 2.220446049250313E-16} is result of doubleEpsilon(),
     * which is used for representing the precision of the computer's arithmetic.
     */
    public static final double DOUBLE_EPSILON = 2.220446049250313E-16;

    /**
     * The round-off unit is a number {@code R} which is used for representing
     * the precision of the computer's arithmetic,that means:<p>
     * {@code 1 < 1 + R}, but {@code 1 = ( 1 + R / 2 )}.
     *
     * @return round-off unit of double
     */
    public static double doubleEpsilon() {
        double value = 1.0;
        while (1.0 < (1.0 + value)) {
            value = value / 2.0;
        }
        return 2.0 * value;
    }
    //===============================================================================================

    //========================== dpmpar.c ===========================================================

    /**
     * The value {@code 2.2204460492503131e-16} is used for representing the precision
     * of the computer's arithmetic which is same with the result of {@code doubleEpsilon()}.
     */
    public static final double DPMPAR1 = 2.2204460492503131e-16;
    //该值与{@code Precision.doubleEpsilon()的计算结果相同。

    /**
     * The value {@code 2.2250738585072014e-308} is used for representing a very small number
     * of {@code double} type.<p>
     * Notes: it more than {@code Double.MIN_VALUE} which is {@code 4.9E-324}.
     */
    public static final double DPMPAR2 = 2.2250738585072014e-308;
    //该值大于{@code Double.MIN_VALUE}的结果4.9E-324。

    /**
     * The value {@code 1.7976931348623157e+308} is used for representing a very large number
     * of {@code double} type.<p>
     * Notes: it equals to {@code Double.MAX_VALUE}.
     */
    public static final double DPMPAR3 = 1.7976931348623157e+308;
    //该值与{@code Double.MAX_VALUE}的结果相同。
    //=============================================================================================================

    //========================== enorm.c ==========================================================================
    /**
     * Reference from enorm.c, it is same with the result of {@code doubleDwarf()}.
     */
    public static final double DOUBLE_DWARF = 1.8269129119256895e-153;
    //该值为{@code doubleDwarf()}的计算结果。

    /**
     * Reference from enorm.c, it is same with the result of {@code doubleGiant()}.
     */
    public static final double DOUBLE_GIANT = 1.3407807929942596e+153;
    //该值为{@code doubleGiant()}的计算结果。

    /**
     * Reference from enorm.c, the library "cmpfit-1.2" prososed.
     */
    public static double doubleDwarf() {
        return Math.sqrt(DPMPAR2 * 1.5) * 10;
    }

    /**
     * Reference from enorm.c, the library "cmpfit-1.2" prososed
     */
    public static double doubleGiant() {
        return Math.sqrt(DPMPAR3) * 0.1;
    }

    /**
     * Reference from {@code enorm(int n, double[] x, int startIndex)},
     * The parameter {@code double[] x} is replaced by {@code IDoublePointer}.
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
     * Overloaded method {@code enorm(int n, IDoublePointer x, int startIndex)},
     * and set the parameter {@code startIndex = 0}.
     */
    public static double enorm(int n, IDoublePointer x) {
        return enorm(n, x, 0);
    }

    /**
     * Overloaded method {@code enorm(int n, IDoublePointer x, int startIndex)},
     * and set the parameter {@code startIndex = 0} and {@code n = x.getCapacity()}.
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
                              IDoublePointer v, IDoublePointer w, IIntPointer sing) {

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

    public static void qrsolv(int n, IDoublePointer r, int ldr, final IIntPointer ipvt,
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
                             IIntPointer ipvt, int lipvt, IDoublePointer rdiag,
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

    public static void covar(int n, IDoublePointer r, int ldr, final IIntPointer ipvt,
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
                             final IIntPointer ipvt, double tol, IDoublePointer wa) {

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


    public static int fdjac1(IFunctionNNPointer fcn, int n, IDoublePointer x, final IDoublePointer fvec,
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

    public static int fdjac2(IFunctionMNPointer fcn, int m, int n, IDoublePointer x,
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

    public static void lmpar(int n, IDoublePointer r, int ldr, final IIntPointer ipvt, final IDoublePointer diag,
                             final IDoublePointer qtb, double delta, IDoublePointer par, IDoublePointer x,
                             IDoublePointer sdiag, IDoublePointer wa1, IDoublePointer wa2) {

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
                    int i;
                    for (i = 0; i < j; ++i) {
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
            double sum;

            int i;
            sum = 0.;
            for (i = 0; i <= j; ++i) {
                sum += r.get(i + j * ldr) * qtb.get(i);
            }
            l = ipvt.get(j) - 1;
            wa1.set(j, sum / diag.get(l));
        }
        gnorm = enorm(n, wa1);
        paru = gnorm / delta;
        if (paru == 0.) {
            paru = dwarf / min(delta, p1) /* / p001 ??? */;
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
                if (iter == 0) {
                    par.set(0.);
                }
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
                    int i;
                    for (i = j + 1; i < n; ++i) {
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
        /* last card of subroutine lmpar. */
    }
}
