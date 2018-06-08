package cn.mirakyux.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 按等于号分割数组 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> s1 = new ArrayList<>();
        List<String> s2 = new ArrayList<>();
        String str = scanner.next();
        while (!str.equals("EOF")) {
            String[] s = str.split("=");
            s1.add("\"." + s[0] + "\"");
            s2.add("\"" + s[1] + "\"");
            str = scanner.next();
        }
        for (int i = 0; i < s1.size(); i++) {
            System.err.println(
                    "    TYPE_EXTENSION.put(\"" + s2.get(i) + "\", \"" + s1.get(i) + "\");");
        }
    }

}
