// LAB4_26JAN19/MergeSortProg.java :: Merge Sort
package week1;

import java.util.Scanner;

public class MergeSortProg
{
    // Merge two sorted halves
    public static void merge(int arr[], int l, int mid, int r)
    {
        int s1 = mid - l + 1;
        int s2 = r - mid;

        int[] Left = new int[s1];
        int[] Right = new int[s2];

        // Copy data to temp arrays
        for (int i = 0; i < s1; i++)
        {
            Left[i] = arr[l + i];
        }
        for (int j = 0; j < s2; j++)
        {
            Right[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = l;

        while (i < s1 && j < s2)
        {
            if (Left[i] <= Right[j])
            {
                arr[k++] = Left[i++];
            }
            else
            {
                arr[k++] = Right[j++];
            }
        }

        while (i < s1)
        {
            arr[k++] = Left[i++];
        }
        while (j < s2)
        {
            arr[k++] = Right[j++];
        }
    }

    public static void mergeSortProg(int arr[], int l, int r)
    {
        if (l < r)
        {
            int mid = (l + r) / 2;
            mergeSortProg(arr, l, mid);
            mergeSortProg(arr, mid + 1, r);
            merge(arr, l, mid, r);
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++)
        {
            arr[i] = sc.nextInt();
        }

        System.out.println("Original Array:");
        for (int num : arr)
        {
            System.out.print(num + " ");
        }
        System.out.println();

        mergeSortProg(arr, 0, n - 1);

        System.out.println("Sorted Array:");
        for (int num : arr)
        {
            System.out.print(num + " ");
        }

        sc.close();
    }
}