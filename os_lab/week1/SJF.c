#include <stdio.h>

struct process {
    int pid;
    int bt;     // Burst Time
    int wt;     // Waiting Time
    int tt;     // Turnaround Time
};

int main() {
    struct process p[100], temp;
    int n, i, j;
    float totwt = 0, tottt = 0;
    float avgwt, avgtt;

    printf("Enter number of processes: ");
    scanf("%d", &n);

    // Input
    for(i = 0; i < n; i++) {
        p[i].pid = i + 1;
        printf("Enter burst time for process P%d: ", p[i].pid);
        scanf("%d", &p[i].bt);
    }

    // Selection Sort - sort by burst time (ascending) → SJF
    for(i = 0; i < n-1; i++) {
        int min_idx = i;
        for(j = i+1; j < n; j++) {
            if(p[j].bt < p[min_idx].bt) {
                min_idx = j;
            }
        }
        // Swap
        temp = p[i];
        p[i] = p[min_idx];
        p[min_idx] = temp;
    }

    // FCFS-style calculation after sorting (non-preemptive SJF)
    p[0].wt = 0;
    p[0].tt = p[0].bt;

    for(i = 1; i < n; i++) {
        p[i].wt = p[i-1].tt;           // start when previous finishes
        p[i].tt = p[i].wt + p[i].bt;
    }

    // Calculate totals
    for(i = 0; i < n; i++) {
        totwt += p[i].wt;
        tottt += p[i].tt;
    }

    avgwt = totwt / n;
    avgtt = tottt / n;

    // Beautiful output
    printf("\n");
    printf("╔════════════╦════════════╦════════════╦════════════╗\n");
    printf("║ Process ID ║ Burst Time ║  Wait Time ║ Turnaround ║\n");
    printf("╠════════════╬════════════╬════════════╬════════════╣\n");

    for(i = 0; i < n; i++) {
        printf("║     P%-2d     ║     %3d    ║     %3d    ║     %3d    ║\n",
               p[i].pid, p[i].bt, p[i].wt, p[i].tt);
    }

    printf("╚════════════╩════════════╩════════════╩════════════╝\n");

    printf("\nAverage Waiting Time    = %.2f\n", avgwt);
    printf("Average Turnaround Time = %.2f\n\n", avgtt);

    return 0;
}