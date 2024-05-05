/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ir.ac.kntu;

/**
 *
 * @author Markazi.co
 */


import java.util.Scanner;

public class ScannerWrapper {

    private static ScannerWrapper instance = new ScannerWrapper();

    private Scanner scanner;


    private ScannerWrapper() {
        scanner = new Scanner(System.in);
    }

    public static ScannerWrapper getInstance() {
        return instance;
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public Double nextDouble() {
        return scanner.nextDouble();
    }

    public Integer nextInt() {
        int ans=scanner.nextInt();
        scanner.nextLine();
        return ans;
    }

    public String next() {
        return scanner.next();
    }

    public void close() {
        scanner.close();
    }
}

