package dev.nuym.purelicense;

import dev.nuym.purelicense.hwid.HardwareIdentifier;
import dev.nuym.purelicense.utils.CrashUtil;

/*
 * @ Author: nuym
 *
 * @ Date: 2024/2/19
 *
 * @ Time: 20:02
 *
 */
public class Main {
    public static void main(String[] args) {
        HardwareIdentifier.detectAndHandleVMWare();
        System.out.println(HardwareIdentifier.getDiskHWID());
    }
}