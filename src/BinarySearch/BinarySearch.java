package BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BinarySearch {

    static int findFirstElementGreaterThanX(int array[], int x, int i, int length) {
        int left = i;
        int right = length - 1;
        int medium;

        while (left <= right) {
            medium = left + (right - left) / 2;

            if (medium != length - 1) {
                if (array[medium] > x && array[medium - 1] == x)
                    return medium;
            } else {
                if (array[medium] > x)
                    return length - 1;
            }

            if (array[medium] <= x) left = medium + 1;
            else right = medium - 1;
        }

        return length;
    }

    public static void binarySearch(int array[], int x, int length)
    {
        int left = 0;
        int right = length - 1;
        int medium;
        int i;
        int j;

        while (left <= right) {
            medium = left + (right - left) / 2;

            if(medium != 0) {
                if (array[medium] == x && array[medium - 1] != x) {
                    i = medium;
                    System.out.print(1 + " " + i + " " + findFirstElementGreaterThanX(array, x, i, length) + "\n");
                    return;
                }
            } else {
                if(array[0] == x) {
                    i = 0;
                    System.out.print(1 + " " + i + " " + findFirstElementGreaterThanX(array, x, i, length) + "\n");
                    return;
                }
            }

            if(array[medium] >= x) right = medium - 1;
            else left = medium + 1;
        }

        medium = left + (right - left) / 2;

        if(medium > length - 1 || medium < 0)
            System.out.print(0 + " " + length + " " + length + "\n");
        else if(array[medium] > x)
            System.out.print(0 + " " + medium + " " + medium + "\n");
        else
            System.out.print(0 + " " + (medium - 1) + " " + (medium - 1) + "\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(br.readLine());
        int[] array = new int[length];
        String[] strArray = br.readLine().split(" ");
        for (int i = 0; i < length; i++)
            array[i] = Integer.parseInt(strArray[i]);
        int requestsQuantity = Integer.parseInt(br.readLine());
        String[] strRequests = br.readLine().split(" ");
        int[] requests = new int[requestsQuantity];
        for (int i = 0; i < requestsQuantity; i++)
            requests[i] = Integer.parseInt(strRequests[i]);
        for(int request : requests)
            binarySearch(array, request, length);
    }
}