package com.eyeieye.melody.demo.web.action.utils;

import java.io.File;
import java.util.List;

public class TestClass {
    static File orgDir = new File("D:\\SinoChem\\views\\");

    void dealFile(File file){
        if(file.isDirectory() == true){
            System.out.println("处理目录"+file.getPath());
            File[] files = file.listFiles();
            if(files!=null && files.length >0){
                for(File tempFile : files){
                    dealFile(tempFile);
                }
            }
        }else{
            System.out.println("处理文件"+file.getPath());
            changeFileName(file);
        }
    }

    void changeFileName(File file) {
        String fileName = file.getName();
        String superPath = file.getParent();
        String newFileName = fileName.split("\\.")[0] + ".htm";
        System.out.println("重命名文件："+file.getPath()+"-->"+superPath + "\\" + newFileName);
        file.renameTo(new File(superPath + "\\" + newFileName));
    }
    public static void main(String[] args) {
        new TestClass().dealFile(orgDir);
    }

}
