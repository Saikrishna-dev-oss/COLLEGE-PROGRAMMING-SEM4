// FCFS.c
#include <stdio.h>

struct process {
    int pid;
    int bt;     // Burst Time
    int wt;     // Waiting Time
    int tt;     // Turnaround Time
};

int main() {
    struct process p[100];
    int n, i;
    float totwt = 0, tottt = 0;    // Use float for accurate average
    float avgwt, avgtt;

    printf("Enter number of processes: ");
    scanf("%d", &n);

    // Input burst times
    for (i = 0; i < n; i++) {
        p[i].pid = i + 1;
        printf("Enter burst time for process P%d: ", p[i].pid);
        scanf("%d", &p[i].bt);
    }

    // FCFS calculation
    p[0].wt = 0;
    p[0].tt = p[0].bt;

    for (i = 1; i < n; i++) {
        p[i].wt = p[i-1].tt;           // Waiting time = previous process completion time
        p[i].tt = p[i].wt + p[i].bt;   // Turnaround time = WT + BT
    }

    // Calculate totals
    for (i = 0; i < n; i++) {
        totwt += p[i].wt;
        tottt += p[i].tt;
    }

    avgwt = totwt / n;
    avgtt = tottt / n;

    // Output Table
    printf("\n");
    printf("╔════════════╦════════════╦════════════╦════════════╗\n");
    printf("║ Process ID ║ Burst Time ║  Wait Time ║ Turnaround ║\n");
    printf("╠════════════╬════════════╬════════════╬════════════╣\n");

    for (i = 0; i < n; i++) {
        printf("║     P%-2d    ║     %3d    ║     %3d    ║     %3d    ║\n",
               p[i].pid, p[i].bt, p[i].wt, p[i].tt);
    }

    printf("╚════════════╩════════════╩════════════╩════════════╝\n");

    printf("\nAverage Waiting Time    : %.2f\n", avgwt);
    printf("Average Turnaround Time : %.2f\n", avgtt);

    return 0;
}
