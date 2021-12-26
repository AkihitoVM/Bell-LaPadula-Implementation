package com.company;

public class File {
    private String fileName;
    private String chown;
    private String chmod;
    public File(String fileName, String chown, String chmod) {
        this.fileName = fileName;
        this.chown = chown;
        this.chmod = chmod;
    }

    public String getFileName() {
        return fileName;
    }

    public String getChown() {
        return chown;
    }

    public String getChmod() {
        return chmod;
    }

    public void setChown(String chown) {
        this.chown = chown;
    }

    public void setChmod(String chmod) {
        this.chmod = chmod;
    }
}
