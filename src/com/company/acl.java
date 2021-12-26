package com.company;

import java.util.Objects;
import java.util.Scanner;

public class acl {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FileSystem fs = new FileSystem();
        while (true){
            System.out.format("%s%s@tuke%s:%s%s%s$ ",ConsoleColors.GREEN,"root",ConsoleColors.RESET,ConsoleColors.BLUE,fs.path,ConsoleColors.RESET);
            String inp = sc.nextLine();
            String[] inputArray = inp.split(" ");
            if (Objects.equals(inputArray[0], "quit") && inputArray.length == 1){
                fs.quit();
                break;
            }else if(Objects.equals(inputArray[0], "mkdir") && inputArray.length == 2){
                fs.mkdir(inputArray[1]);
            }else if(Objects.equals(inputArray[0], "ls") && inputArray.length == 1){
                fs.ls();
            }else if(Objects.equals(inputArray[0], "cd") && inputArray.length == 2){
                fs.cd(inputArray[1]);
            }else if(Objects.equals(inputArray[0], "rm") && inputArray.length == 2) {
                fs.rm(inputArray[1]);
            }else if(Objects.equals(inputArray[0], "touch") && inputArray.length == 2) {
                fs.touch(inputArray[1]);
            }else if(Objects.equals(inputArray[0], "chmod") && inputArray.length == 3) {
                fs.chmod(inputArray[2], inputArray[1]);
            }else if(Objects.equals(inputArray[0], "chown") && inputArray.length == 3) {
                fs.chown(inputArray[2], inputArray[1]);
            }else if(Objects.equals(inputArray[0], "vypis") && inputArray.length == 2) {
                fs.vypis(inputArray[1]);
            }else if(Objects.equals(inputArray[0], "spusti") && inputArray.length == 2) {
                fs.spusti(inputArray[1]);
            }else if(Objects.equals(inputArray[0], "zapis") && inputArray.length == 2) {
                fs.zapis(inputArray[1]);
            }
        }
    }
}
