package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Fibanacci {
    private static int n;
    private static ArrayList<Integer>arrayList=new ArrayList<>();
    public static ArrayList<Integer>fibancciCounter(int n){
        arrayList.clear();
        if (n >= 1)
            arrayList.add(0);

        if (n >= 2)
            arrayList.add(1);

        for (int i = 2; i < n; i++) {
            arrayList.add(arrayList.get(i - 1) + arrayList.get(i - 2));
        }
        return arrayList;
    }

    public static void main(String[] args) {
        try(BufferedReader reader=new BufferedReader(new InputStreamReader(System.in))) {
            n=Integer.parseInt(reader.readLine());
            System.out.println(fibancciCounter(n));
        } catch (IOException e) {
            e.printStackTrace();
        } catch(NullPointerException e){
            e.printStackTrace();
        }
    }
}