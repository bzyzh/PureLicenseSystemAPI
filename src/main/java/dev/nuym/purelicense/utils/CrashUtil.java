package dev.nuym.purelicense.utils;

import lombok.SneakyThrows;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @ Author: nuym
 * @ Date: 2024/2/19
 * @ Time: 21:36
 */
public class CrashUtil {
    @SneakyThrows
    public static void Crash() {
        Field F2;
        try {
            F2 = Unsafe.class.getDeclaredField("theUnsafe");
            F2.setAccessible(true);
            ((Unsafe) F2.get(null)).putAddress(114514L, 1919810L);
            ((Unsafe) F2.get(null)).putAddress(114514L, 1919810L);
            ((Unsafe) F2.get(null)).putAddress(14514L, 191810L);
            ((Unsafe) F2.get(null)).putAddress(114514L, 1919810L);
            ((Unsafe) F2.get(null)).putAddress(11414L, 191910L);
            ((Unsafe) F2.get(null)).putAddress(114514L, 1919810L);
            ((Unsafe) F2.get(null)).putAddress(114514L, 191810L);
            ((Unsafe) F2.get(null)).putAddress(11454L, 1919810L);
            ((Unsafe) F2.get(null)).putAddress(11454L, 199810L);
            ((Unsafe) F2.get(null)).putAddress(114514L, 1919810L);
            ((Unsafe) F2.get(null)).putAddress(11414L, 191810L);
            ((Unsafe) F2.get(null)).putAddress(114514L, 1919810L);
        } catch (Exception e) {
        }
        try {
            F2 = Unsafe.class.getDeclaredField("theUnsafe");
            F2.setAccessible(true);
            ((Unsafe) F2.get(null)).putAddress(114514L, 1919810L);
            ((Unsafe) F2.get(null)).putAddress(114514L, 1919810L);
            ((Unsafe) F2.get(null)).putAddress(14514L, 191810L);
            ((Unsafe) F2.get(null)).putAddress(114514L, 1919810L);
            ((Unsafe) F2.get(null)).putAddress(11414L, 191910L);
            ((Unsafe) F2.get(null)).putAddress(114514L, 1919810L);
            ((Unsafe) F2.get(null)).putAddress(114514L, 191810L);
            ((Unsafe) F2.get(null)).putAddress(11454L, 1919810L);
            ((Unsafe) F2.get(null)).putAddress(11454L, 199810L);
            ((Unsafe) F2.get(null)).putAddress(114514L, 1919810L);
            ((Unsafe) F2.get(null)).putAddress(11414L, 191810L);
            ((Unsafe) F2.get(null)).putAddress(114514L, 1919810L);
        } catch (Exception e) {
        }
    }

}
