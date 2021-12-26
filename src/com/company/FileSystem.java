package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileSystem {
    Dir root;
    List<Integer> currentDirectory;
    String path;

    public FileSystem() {
        this.root = new Dir("root",new ArrayList<>(),new ArrayList<>(),"akihito","rwx");
        this.currentDirectory = new ArrayList<>();
        this.path = "~";
    }

    public void mkdir(String dirname){
        Dir a = this.root;
        for (int i = 0; i < currentDirectory.size(); i++) {
            a = a.arrayOfDirs.get(currentDirectory.get(i));
        }
        if (a.chmod.toCharArray()[1] == 'w'){
            a.arrayOfDirs.add(new Dir(dirname, new ArrayList<>(),new ArrayList<>(), "root", "rwx"));
        }else{
            System.out.println("chyba: "+a.dirName+" has no permission to write into the directory");
        }
    }

    public void ls(){
        Dir a = this.root;
        for (int i = 0; i < currentDirectory.size(); i++) {
            a = a.arrayOfDirs.get(currentDirectory.get(i));
        }
        if (a.chmod.toCharArray()[0] == 'r'){
            for (Dir dir: a.arrayOfDirs) {
                System.out.println(dir.dirName + " " + dir.chown + " " + dir.chmod);
            }
            for (File file: a.arrayOfFiles) {
                System.out.println(file.getFileName() + " " + file.getChown() + " " + file.getChmod());
            }
        }else {
            System.out.println("chyba: Permission denied");
        }
    }

    public void cd(String dirname){
        if (dirname.equals("..")){
            if(currentDirectory.size() != 0){
                currentDirectory.remove(currentDirectory.size() - 1);
                List<String> arr = new ArrayList<>(List.of(path.split("/")));
                arr.remove(0);
                arr.remove(arr.size() - 1);
                String lol = "~";
                for (String elm: arr) {
                    lol += "/"+elm;
                }
                path = lol;
            }
        }else {
            Dir a = root;
            int pos = 0;
            for (int i = 0; i < currentDirectory.size(); i++) {
                a = a.arrayOfDirs.get(currentDirectory.get(i));
            }
            for (Dir dir: a.arrayOfDirs) {
                if (Objects.equals(dir.dirName, dirname)){
                    if (dir.chmod.toCharArray()[2] == 'x'){
                        currentDirectory.add(pos);
                        path += "/" + dir.dirName;
                    }else{
                        System.out.println("chyba: Permission denied");
                    }
                    break;
                }else{
                    pos++;
                }
            }
            if (pos == a.arrayOfDirs.size()){
                System.out.println("chyba: dir not exist");
            }
        }
    }

    public void rm(String name){
        Dir a = root;
        int posDir = 0;
        for (int i = 0; i < currentDirectory.size(); i++) {
            a = a.arrayOfDirs.get(currentDirectory.get(i));
        }
        int lengthDirs = a.arrayOfDirs.size();
        for (Dir eman : a.arrayOfDirs) {
            if (Objects.equals(eman.dirName, name)){
                a.arrayOfDirs.remove(posDir);
                break;
            }else {
                posDir++;
            }
        }
        int posFile  = 0;
        int lengthFiles = a.arrayOfFiles.size();
        for (File eman : a.arrayOfFiles) {
            if (Objects.equals(eman.getFileName(), name)){
                a.arrayOfFiles.remove(posFile);
                break;
            }else {
                posFile++;
            }
        }
        if (posDir == lengthDirs && posFile == lengthFiles){
            System.out.println("chyba: file or directory not exist");
        }
    }

    public void touch(String fileName){
        Dir a = this.root;
        for (Integer integer : currentDirectory) {
            a = a.arrayOfDirs.get(integer);
        }
        if (a.chmod.toCharArray()[1] == 'w'){
            a.arrayOfFiles.add(new File(fileName,"root","rwx"));
        }else{
            System.out.println("chyba: "+a.dirName+" has no permission to write into the directory");
        }
    }

    public void chmod(String name, String chmod){
        if (chmod.matches("\\d+")){
            if (Integer.parseInt(chmod) >= 0 && Integer.parseInt(chmod) < 8){
                Dir a = this.root;
                int posDir = 0;
                for (int i = 0; i < currentDirectory.size(); i++) {
                    a = a.arrayOfDirs.get(currentDirectory.get(i));
                }
                int lenDirs = a.arrayOfDirs.size();
                for (Dir eman: a.arrayOfDirs ) {
                    if (eman.dirName.equals(name)){
                        a.arrayOfDirs.get(posDir).chmod = chmodWordToInt(Integer.parseInt(chmod));
                        break;
                    }else{
                        posDir++;
                    }
                }
                int posFile = 0;
                int lenFiles = a.arrayOfFiles.size();
                for (File eman: a.arrayOfFiles ) {
                    if (eman.getFileName().equals(name)){
                        a.arrayOfFiles.get(posFile).setChmod(chmodWordToInt(Integer.parseInt(chmod)));
                        break;
                    }else{
                        posFile++;
                    }
                }
                if (posDir == lenDirs && posFile == lenFiles){
                    System.out.println("chyba: file or directory not exist");
                }
            }else{
                System.out.println("chyba: chmod must be number [0-8)");
            }
        }else{
            System.out.println("chyba: chmod must be NUMBER [0-8)");
        }
    }

    public void chown(String name, String chown){
        Dir a = this.root;
        int posDir = 0;
        for (int i = 0; i < currentDirectory.size(); i++) {
            a = a.arrayOfDirs.get(currentDirectory.get(i));
        }
        int lenDirs = a.arrayOfDirs.size();
        for (Dir eman: a.arrayOfDirs ) {
            if (eman.dirName.equals(name)){
                a.arrayOfDirs.get(posDir).chown = chown;
                break;
            }else{
                posDir++;
            }
        }
        int posFile = 0;
        int lenFiles = a.arrayOfFiles.size();
        for (File eman: a.arrayOfFiles ) {
            if (eman.getFileName().equals(name)){
                a.arrayOfFiles.get(posFile).setChown(chown);
                break;
            }else{
                posFile++;
            }
        }
        if (posDir == lenDirs && posFile == lenFiles){
            System.out.println("chyba: file or directory not exist");
        }
    }

    public void vypis(String filename){
        Dir a = this.root;
        int posFile = 0;
        for (int i = 0; i < currentDirectory.size(); i++) {
            a = a.arrayOfDirs.get(currentDirectory.get(i));
        }
        for (File eman: a.arrayOfFiles) {
            if (eman.getFileName().equals(filename)){
                if (eman.getChmod().toCharArray()[0] == 'r'){
                    System.out.println("OK");
                }else {
                    System.out.println("chyba prav");
                }
                break;
            }else {
                posFile++;
            }
        }
        if (posFile == a.arrayOfFiles.size()){
            System.out.println("chyba: file to vypis not exist");
        }
    }

    public void spusti(String filename){
        Dir a = this.root;
        int posFile = 0;
        for (int i = 0; i < currentDirectory.size(); i++) {
            a = a.arrayOfDirs.get(currentDirectory.get(i));
        }
        for (File eman: a.arrayOfFiles) {
            if (eman.getFileName().equals(filename)){
                if (eman.getChmod().toCharArray()[2] == 'x'){
                    System.out.println("OK");
                }else {
                    System.out.println("chyba prav");
                }
                break;
            }else {
                posFile++;
            }
        }
        if (posFile == a.arrayOfFiles.size()){
            System.out.println("chyba: file to spusti not exist");
        }
    }

    public void zapis(String name){
        Dir a = this.root;
        int posDir = 0;
        int lenDirs = a.arrayOfDirs.size();
        for (int i = 0; i < currentDirectory.size(); i++) {
            a = a.arrayOfDirs.get(currentDirectory.get(i));
        }
        for (Dir eman: a.arrayOfDirs) {
            if (eman.dirName.equals(name)){
                if (eman.chmod.toCharArray()[1] == 'w'){
                    System.out.println("OK");
                }else {
                    System.out.println("chyba prav");
                }
                break;
            }else {
                posDir++;
            }
        }

        int posFile = 0;
        int lenFiles = a.arrayOfFiles.size();
        for (File eman: a.arrayOfFiles) {
            if (eman.getFileName().equals(name)){
                if (eman.getChmod().toCharArray()[1] == 'w'){
                    System.out.println("OK");
                }else {
                    System.out.println("chyba prav");
                }
                break;
            }else {
                posFile++;
            }
        }
        if (posDir == lenDirs && posFile == lenFiles){
            System.out.println("chyba: file or directory not exist");
        }
    }

    public void quit(){
        System.out.println("EXIT");
        System.exit(0);
    }

    private String chmodWordToInt(int chmodInInt) {
        switch (chmodInInt){
            case 0: return "---";
            case 1: return "--x";
            case 2: return "-w-";
            case 3: return "-wx";
            case 4: return "r--";
            case 5: return "r-x";
            case 6: return "rw-";
            case 7: return "rwx";
        }
        return "---";
    }
}
