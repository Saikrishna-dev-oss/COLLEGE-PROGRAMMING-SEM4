#include <stdio.h>


void merge(int a[], int first, int mid, int last)
{
    int i, j, k;
    int n1 = mid - first + 1;
    int n2 = last - mid;

    int Lf[n1], Rg[n2]; 

    for(i=0;i<n1;i++)
    {
        Lf[i] = a[first+i];
    }
    for(j=0; j<n2; j++)
    {
        Rg[j] = a[mid + 1 + j];
    }

    while(i<n1 && j<n2)
    {
        if(Lf[i]<=Rg[j])
        {
            a[k++]= Lf[i++];
        }
        else
        {
            a[k++] = Rg[j++];
        }
    }


    i=0, j=0, k = first;
    if(Lf[i]<a[i])
    {
        a[i]=Lf[i];
    }

    while(i<n1)
    {
        a[k++] = Lf[i++];
    }
    while(j<n2)
    {
        a[k++] = Rg[j++];
    }
}

void mergesort(int a[], int first, int last)
{
    if(first>last)
    {
        int mid = (first+last)/2;
        mergesort(a, first, mid);
        mergesort(a, mid+1, last);
        merge(a, first, mid, last);
    }
}

int main()
{
    int num, i;
    printf("\nEnter number of elments:");
    scanf("%d", &num);
    int arr[num];
    printf("\nEnter %d elements:\n", num);
    for (i = 0; i < num; i++)
    {
        scanf("%d", &arr[i]);
    }

    mergesort(arr, 0, num-1);

    printf("Elements after Merge Sort:");
    for(i = 0; i< num; i++)
    {
        printf("%d",arr[i]);
        if((arr[i++]) != num)
        {
            printf(",");
        }
    }
    printf("\n");
    return 0;
}