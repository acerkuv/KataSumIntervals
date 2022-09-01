package ru.kuvaldin;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // write your code here
        int [][] test = new int[][]{{1, 4}, {7, 10}, {3, 5}};
//        int [][] test = new int[][]{ {1,4}, {4,7}};

        ArrayList<int[]> testList = ArrayToArrayList(test);
        testList = goAroundArrayList(testList);
        testList = dropDuplicates(testList);
        printArrayList(testList);
        System.out.println(sumInt(testList));

//        ArrayList <int[]>  newL= orderIntervals(new int[] {-1, 3}, testList);
//        printArrayList(newL);
    }

    public static int sumIntervals(int[][] intervals) {
        ArrayList<int[]> testList = ArrayToArrayList(intervals);
        testList = goAroundArrayList(testList);
        testList = dropDuplicates(testList);
        int sumInt = 0;
        for (int [] i: testList){
            sumInt += i[1] - i[0];
        }
        return sumInt;
    }

    public static ArrayList<int[]> orderIntervals(int[] forCheck, ArrayList<int[]> forOrder){
        int lm = forOrder.size();
        for (int i = 0; i < lm; i++) {
                int[] check = forCheck;
                int[] temp = forOrder.get(i);
                boolean between = forCheck[0] > temp[0] & forCheck[1] < temp[1];
                boolean right = forCheck[0] >= temp[0] & forCheck[1] > temp[1] & forCheck[0]< temp[1];
                boolean expand  = forCheck[0]< temp[0] & forCheck[1] > temp[1];
                boolean left = check[0] < temp[0] & check[1] <= temp[1] & check[1] > temp[0];
                boolean equal = check[0]==temp[0] & check[1]==temp[1];
                boolean free = !between & !right & !expand & !left & !equal;

                if (!equal){
                    if (between) forOrder.set(i, temp);
                    if (expand) forOrder.set(i, forCheck);
                    if (right) forOrder.set(i, new int[]{temp[0], check[1]});
                    if(left) forOrder.set(i, new int[] {check[0], temp[1]});
                }else {
                    forOrder.set(i,forCheck);
                }

        }


        return forOrder;
    }

    public static ArrayList<int[]> ArrayToArrayList (int[][] value){
        ArrayList<int[]> transformed = new ArrayList<>();
        for (int[] m: value){
            transformed.add(m);
        }
        return transformed;
    }
    public static ArrayList<int[]> goAroundArrayList(ArrayList <int[]> value){
        int next = 0;
        int ln = value.size();
        while (next < ln){
            for (int i = next; i < ln ; i++) {
                value = orderIntervals(value.get(i),value);
            }
            next++;
        }
//        value = dropDuplicates(value);
        return value;
    }
    public static ArrayList <int[]> dropDuplicates(ArrayList<int[]> value){
        ArrayList<int[]> dropped = new ArrayList<>();
        int st = 0;
        while (st < value.size()){
            for (int i=st; i < value.size(); i++){
                int [] temp  = value.get(st);
                if (value.get(st).equals(value.get(i))) value.remove(i);
                dropped.add(temp);
            }
            st++;
        }
        return dropped;
    }
    public static int sumInt (ArrayList<int[]> value){
        int sumInt = 0;
        for (int[]i:value){
            sumInt += i[1] - i[0];
        }
        return sumInt;
    }

    public static void printArrayList(ArrayList<int[]> value){
        for(int[] m:value) System.out.println(Arrays.toString(m));
    }
}
