import com.sun.xml.internal.bind.v2.TODO;

import java.io.PrintWriter;
import java.net.*;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
 * author: Maksymilian Buk
 * student of second year Applied Computer Science AGH
 * 11.04.2019 */

public class Main {

    public static void main(String[] args) throws Exception {

        String input = "";
        System.out.print("Web calculator. Your argumet was: ");
        try {
            input = args[0];
            System.out.println(input);
        } catch (Exception e) {
            System.out.println("undefined");
            //Some invalid mask type for correctly run of IPandMask Constructor
            input = "1.1/35";

        }
        IPandMask ip = new IPandMask(input);

        //System.out.println(ip.getIpString());
        //System.out.println(ip.getMaskString());

        //Writing to text file
        PrintWriter summary = new PrintWriter("summary_web.txt");

        System.out.print("Checking for IP: " + Editing.binaryToDecimalWithDots35chars(ip.getIpString()) + " ");
        System.out.println("and mask: " + Editing.binaryToDecimalWithDots35chars(ip.getMaskString()));
        summary.print("Checking for IP: " + Editing.binaryToDecimalWithDots35chars(ip.getIpString()) + " ");
        summary.println("and mask :" + Editing.binaryToDecimalWithDots35chars(ip.getMaskString()));
        //#1
        String webAdress = "";
        webAdress = Calculations.webAdress(ip.getIpString(), ip.getMaskString());
        System.out.println("Web adress: " + webAdress);
        summary.println("Web adress: " + webAdress);

        //#2
        String webClass = "";
        webClass = Calculations.classOfAdress(ip.getIpString());
        System.out.println("Web Class: " + webClass);
        summary.println("Web Class: " + webClass);


        //#3
        String isPrivateOrPublic = "";
        isPrivateOrPublic = Calculations.checkPrivacy(Editing.binaryToDecimalWithDots35chars(ip.getIpString()));
        System.out.println("This adress is: " + isPrivateOrPublic);
        summary.println("This adress is: " + isPrivateOrPublic);

        //#4
        String maskDecimal = "", maskBinary = "";

        maskBinary = ip.getMaskString();
        maskDecimal = Editing.binaryToDecimalWithDots35chars(ip.getMaskString());
        System.out.println("Mask in decimal: " + maskDecimal);
        summary.println("Mask in decimal: " + maskDecimal);
        System.out.println("Mask in binary: " + maskBinary);
        summary.println("Mask in binary: " + maskBinary);

        //#5
        String broadcastDecimal = "", broadcastBinary = "";
        broadcastBinary = Calculations.broadcastAdress(ip.getIpString(), ip.getMaskString());
        broadcastDecimal = Editing.binaryToDecimalWithDots35chars(broadcastBinary);
        System.out.println("Broadcast in decimal: " + broadcastDecimal);
        summary.println("Broadcast in decimal: " + broadcastDecimal);
        System.out.println("Broadcast in binary: " + broadcastBinary);
        summary.println("Broadcast in binary: " + broadcastBinary)
    ;

        //#6
        String firstHostDecimal = "", firstHostBinary = "";
        firstHostBinary = Calculations.firstHost(webAdress);
        firstHostDecimal = Editing.binaryToDecimalWithDots35chars(firstHostBinary);
        System.out.println("First host in decimal: " + firstHostDecimal);
        summary.println("First host in decimal: " + firstHostDecimal);
        System.out.println("First host in binary: " + firstHostBinary);
        summary.println("First host in binary: " + firstHostBinary);

        //#7
        String lastHostDecimal = "", lastHostBinary = "";
        lastHostBinary = Calculations.lastHost(broadcastBinary);
        lastHostDecimal = Editing.binaryToDecimalWithDots35chars(lastHostBinary);
        System.out.println("Last host in decimal: " + lastHostDecimal);
        summary.println("Last host in decimal: " + lastHostDecimal);
        System.out.println("Last host in binary: " + lastHostBinary);
        summary.println("Last host in binary: " + lastHostBinary);

        //#8
        int maxNumberOfAdresses;
        maxNumberOfAdresses = Calculations.hostCounting(firstHostDecimal, lastHostDecimal);
        System.out.println("Max number of adresses: " + maxNumberOfAdresses);
        summary.println("Max number of adresses: " + maxNumberOfAdresses);


        //#9
        //if(ip.getIpFromArgumentLine())
        if (Editing.binaryToDecimalWithDots35chars(ip.getIpString()) != webAdress && Editing.binaryToDecimalWithDots35chars(ip.getIpString()) != broadcastDecimal) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Want you to ping that host? Y/N");
            String choice = scan.nextLine();
            if (choice.equals("Y") || choice.equals("y")) {
                String pingResult = "";
                pingResult = Calculations.pingRequest(Editing.binaryToDecimalWithDots35chars(ip.getIpString()));
                System.out.println(pingResult);
                summary.println(pingResult);
            }
        }

        summary.close();


    }
}