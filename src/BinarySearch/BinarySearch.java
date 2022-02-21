package BinarySearch;

import java.util.Scanner;

public class BinarySearch implements Runnable {

    public static boolean binarySearch(int left, int right, int[] array, int x) {
        int medium = left + ((right - left) / 2);

        if(left > right) {
            System.out.print(0);
            System.out.print(" ");
            if(medium > array.length - 1 || medium < 0) {
                System.out.print(array.length);
                System.out.print(" ");
                System.out.print(array.length);
            }
            else if(array[medium] > x) {
                System.out.print(medium);
                System.out.print(" ");
                System.out.print(medium);
            } else {
                int i = medium - 1;
                System.out.print(i);
                System.out.print(" ");
                System.out.print(i);
            }
            System.out.print(" " + "\n");
            return false;
        }

        if(array[medium] > x)
            binarySearch(left, medium - 1, array, x);
        else if(array[medium] < x)
            binarySearch(medium + 1, right, array, x);
        else if(array[medium] == x) {
            System.out.print(1);
            System.out.print(" ");
            int i = medium;
            while(i > -1 && array[i] == x)
                i--;
            System.out.print(i + 1);
            System.out.print(" ");
            i = medium;
            while(i < array.length && array[i] == x)
                i++;
            System.out.print(i);
            System.out.print(" " + "\n");
        }
        return true;
    }

    public static void main(String[] args) {
        new Thread(null, new BinarySearch(), "", 256 * 1024 * 1024).start();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = sc.nextInt();
        int requestsQuantity = sc.nextInt();
        int[] requests = new int[requestsQuantity];
        for (int i = 0; i < requestsQuantity; i++)
            requests[i] = sc.nextInt();
        sc.close();
        for(int request : requests)
            binarySearch(0, size - 1, array, request);
    }
}
