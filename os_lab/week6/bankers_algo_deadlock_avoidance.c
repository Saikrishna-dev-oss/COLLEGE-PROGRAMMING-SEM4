// WEEK6 :: BANKER'S ALGORITHM FOR DEADLOCK AVOIDANCE
#include <stdio.h>

int main()
{
    // Number of processes and resources
    int n = 5; // processes
    int m = 3; // resources

    // Allocation Matrix
    int alloc[5][3] = {
        {0, 1, 0}, // P0
        {2, 0, 0}, // P1
        {3, 0, 2}, // P2
        {2, 1, 1}, // P3
        {0, 0, 2}  // P4
    };

    // Maximum Matrix
    int max[5][3] = {
        {7, 5, 3}, // P0
        {3, 2, 2}, // P1
        {9, 0, 2}, // P2
        {2, 2, 2}, // P3
        {4, 3, 3}  // P4
    };

    // Available Resources
    int avail[3] = {3, 3, 2};

    int f[n], ans[n], ind = 0;
    for (int k = 0; k < n; k++)
    {
        f[k] = 0;
    }

    // Need Matrix = Max - Allocation
    int need[n][m];
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            need[i][j] = max[i][j] - alloc[i][j];
        }
    }

    // Banker's Algorithm
    for (int k = 0; k < n; k++)
    {
        for (int i = 0; i < n; i++)
        {
            if (f[i] == 0) {
                int flag = 0;
                for (int j = 0; j < m; j++)
                {
                    if (need[i][j] > avail[j])
                    {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0)
                {
                    ans[ind++] = i;
                    for (int y = 0; y < m; y++)
                    {
                        avail[y] += alloc[i][y];
                    }
                    f[i] = 1;
                }
            }
        }
    }

    // Print Safe Sequence
    printf("Following is the SAFE Sequence:\n");
    for (int i = 0; i < n - 1; i++)
    {
        printf(" P%d ->", ans[i]);
    }
    printf(" P%d\n", ans[n - 1]);

    return 0;
}