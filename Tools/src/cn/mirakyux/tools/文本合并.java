package cn.mirakyux.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class 文本合并 {

    public static boolean unionFile(String outfile, String path) throws IOException {
        boolean result = false;
        List<File> fileList = getFiles(path);
        File fout = new File(outfile);
        FileWriter fw = new FileWriter(fout);
        for (File f : fileList) {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                fw.append(line + "\n");
                line = br.readLine();
            }
            fr.close();
        }
        fw.close();
        result = true;
        return result;
    }

    public static List<File> getFiles(String path) {
        File root = new File(path);
        List<File> files = new ArrayList<>();
        if (!root.isDirectory()) {
            files.add(root);
        } else {
            File[] subFiles = root.listFiles();
            for (File f : subFiles) {
                files.addAll(getFiles(f.getAbsolutePath()));
            }
        }
        return files;
    }

    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\Mi\\Desktop\\新建文件夹 (2)\\sql\\";
        String fileName = "all.sql";
        System.out.println(unionFile(path + fileName, path));
    }
}
