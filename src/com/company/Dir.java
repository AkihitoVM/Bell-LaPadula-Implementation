package com.company;

import java.util.List;

public class Dir {
    String dirName;
    List<File> arrayOfFiles;
    List<Dir> arrayOfDirs;
    String chown;
    String chmod;

    public Dir(String dirName,List<File> arrayOfFiles,List<Dir> arrayOfDirs,String chown,String chmod) {
        this.dirName = dirName;
        this.arrayOfFiles = arrayOfFiles;
        this.arrayOfDirs = arrayOfDirs;
        this.chown = chown;
        this.chmod = chmod;
    }

}
