package cn.edu.gxust.jiweihuang.java.pointer.primitive;

public interface IStringPointer extends IShortConstPointer {
    /**
     * 设置指定索引处的数据。
     *
     * @param index 指定的索引
     * @param value 需要设置的值。
     */
    void set(int index, String value);

    /**
     * 设置指针当前指向处的数据。
     * 注意：如果指针当前指向数组索引范围之外，则将抛出异常。
     *
     * @param value 需要设置的值。
     */
    default void set(String value) {
        set(0, value);
    }
}
