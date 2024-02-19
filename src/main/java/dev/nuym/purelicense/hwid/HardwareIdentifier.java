package dev.nuym.purelicense.hwid;

/**
 * @ Author: nuym
 * @ Date: 2024/2/19
 * @ Time: 20:57
 */
import dev.nuym.purelicense.utils.CrashUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Scanner;

public class HardwareIdentifier {

    // 获取并存储操作系统名称的大写形式
    private static String OS_NAME = System.getProperty("os.name").toUpperCase();

    /**
     * 从Windows或Mac系统中获取主板硬件标识符（HWID）
     *
     * @return HWID字符串，若出现异常则返回"Error"
     */
    public static String getBaseboardHWID() {
        try {
            if (OS_NAME.contains("WIN")) {

                Process exec = Runtime.getRuntime().exec("wmic baseboard get product,Manufacturer,version,serialnumber");
                exec.getOutputStream().close();
                Scanner scanner = new Scanner(exec.getInputStream());
                scanner.nextLine(); // 跳过一行标题信息
                String nextLine = scanner.nextLine();
                scanner.close();
                return nextLine;

            } else if (OS_NAME.contains("MAC")) {
                try {
                    Process exec = Runtime.getRuntime().exec("system_profiler SPMemoryDataType");
                    exec.getOutputStream().close();
                    Scanner scanner = new Scanner(exec.getInputStream());
                    // 跳过前9行无关信息
                    for (int i = 0; i < 9; i++) {
                        scanner.nextLine();
                    }
                    final String nextLine = scanner.nextLine();
                    scanner.close();
                    return nextLine;
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Error";
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return "Error";
        }
        return "Error";
    }

    public static String getHWID1() {
        try {
            if (OS_NAME.contains("WIN")) {
                Process exec = Runtime.getRuntime().exec("wmic baseboard get product,Manufacturer,version,serialnumber");
                Scanner scanner = new Scanner(exec.getInputStream());
                scanner.nextLine(); // 跳过第一行
                scanner.nextLine(); // 继续
                String winHWID = scanner.nextLine();
                scanner.close();
                return winHWID;
            } else if (OS_NAME.contains("MAC")) {
                Process exec2 = Runtime.getRuntime().exec("system_profiler SPHardwareDataType");
                Scanner scanner3 = new Scanner(exec2.getInputStream());
                for (int i = 0; i < 16; i++) { // 跳过前 16 行
                    scanner3.nextLine();
                }
                String macHWID = scanner3.nextLine();
                scanner3.close();
                return macHWID;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error";
    }
    public static String getPNPHWID() {
        try {
            if (OS_NAME.contains("WIN")) {
                Process exec = Runtime.getRuntime().exec("wmic path Win32_VideoController get Description,PNPDeviceID");
                Scanner scanner = new Scanner(exec.getInputStream());
                scanner.nextLine(); // 跳过标题行
                scanner.nextLine(); // 跳过另一行
                String winVideoControllerInfo = scanner.nextLine();
                scanner.close();
                return winVideoControllerInfo;
            } else if (OS_NAME.contains("MAC")) {
                Process exec2 = Runtime.getRuntime().exec("system_profiler SPDisplaysDataType");
                Scanner scanner3 = new Scanner(exec2.getInputStream());
                for (int i = 0; i < 4; i++) { // 跳过头 4 行
                    scanner3.nextLine();
                }
                String macDisplayInfo = scanner3.nextLine();
                scanner3.close();
                return macDisplayInfo;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Error";
    }
    public static String getMemoryHWID() {

        try {

            // 检查操作系统是否为Windows
            if (OS_NAME.contains("WIN")) {
                Process exec = Runtime.getRuntime().exec(new String[] { "wmic", "memorychip", "get", "serialnumber" });
                exec.waitFor(); // 等待命令执行完成

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                StringBuilder sb = new StringBuilder();

                // 读取并拼接内存芯片序列号信息
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
                bufferedReader.close();

                // 提取最后一个换行符后的字符串内容，并去除前后空白字符
                String result = sb.substring(sb.toString().lastIndexOf("r") + 1).trim();
                return result;
            }

            // 检查操作系统是否为Mac
            else if (HardwareIdentifier.OS_NAME.contains("MAC")) {
                Process exec2 = Runtime.getRuntime().exec("system_profiler SPMemoryDataType");
                exec2.getOutputStream().close();

                Scanner scanner = new Scanner(exec2.getInputStream());
                // 跳过前9行无关信息
                for (int i = 0; i < 9; i++) {
                    scanner.nextLine();
                }
                final String macMemoryInfo = scanner.nextLine();
                scanner.close();
                return macMemoryInfo;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Error";
    }
    public static String getMemoryHWID2() {
        try {
            if (OS_NAME.contains("WIN")) {
                Process exec = Runtime.getRuntime().exec(new String[] { "wmic", "memorychip", "get", "serialnumber" });
                exec.waitFor();

                StringBuilder sb = new StringBuilder();
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }
                }

                // 优化：使用正则表达式去除换行符并截取最后一个“r”字符之后的内容，确保不会因字符串中存在其他“r”字符而误判
                return sb.toString().trim().replaceAll("\\s+", "").substring(sb.toString().lastIndexOf("r") + 1);
            } else if (OS_NAME.contains("MAC")) {
                Process exec2 = Runtime.getRuntime().exec("system_profiler SPMemoryDataType");
                exec2.getOutputStream().close();

                Scanner scanner = new Scanner(exec2.getInputStream());
                for (int i = 0; i < 9; i++) {
                    scanner.nextLine(); // 跳过前9行
                }

                String macMemoryInfo = scanner.nextLine();
                scanner.close();
                return macMemoryInfo;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "Error";
    }
    public static String getDiskHWID() {
        try {
            if (OS_NAME.contains("WIN")) {
                Process exec = Runtime.getRuntime().exec("wmic volume get driveletter,serialnumber");
                exec.getOutputStream().close();

                Scanner scanner = new Scanner(exec.getInputStream());
                scanner.nextLine(); // 跳过标题行

                HashMap<String, String> diskSerialMap = new HashMap<>();
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().replaceAll(" ", "");
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        diskSerialMap.put(parts[0].toLowerCase(), parts[1]);
                    }
                }
                scanner.close();

                return diskSerialMap.getOrDefault("c", "");
            } else if (OS_NAME.contains("LINUX")) {
                Process exec2 = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", "/sbin/udevadm info --query=property --name=sda" });
                exec2.waitFor();

                BufferedReader reader = new BufferedReader(new InputStreamReader(exec2.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("ID_SERIAL_SHORT=")) {
                        sb.append(line.substring(line.indexOf("=") + 1));
                        break;
                    }
                }

                reader.close();
                return sb.toString().trim();
            } else if (OS_NAME.contains("MAC")) {
                Process exec3 = Runtime.getRuntime().exec("system_profiler SPSerialATADataType");
                exec3.getOutputStream().close();

                Scanner scannerMac = new Scanner(exec3.getInputStream());
                for (int i = 0; i < 16; i++) {
                    scannerMac.nextLine(); // 跳过无关行
                }

                String macDiskInfo = scannerMac.nextLine();
                scannerMac.close();
                return macDiskInfo;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "Error";
    }
    /**
     * 检测是否运行在VMWare虚拟机上，并进行相应处理
     */
    public static void detectAndHandleVMWare() {
        // 收集相关环境变量和硬件标识符信息
        String[] hardwareInfo = {System.getenv("PROCESSOR_IDENTIFIER"), System.getenv("NUMBER_OF_PROCESSORS"), getBaseboardHWID(),getHWID1(),getMemoryHWID(),getMemoryHWID2(),getDiskHWID(),getPNPHWID()};

        for (String info : hardwareInfo) {
            if (info.contains("VMware")) {
                System.out.println("错误！检测到正在虚拟计算机上运行！您不能在虚拟计算机上运行此程序。");
                try {
                    CrashUtil.Crash();
                } catch (Exception ex) {
                    throw new RuntimeException("加载失败！");
                }
            }
        }
    }
}
