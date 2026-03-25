#include <stdio.h>

struct process {
    int pid;
    int bt;      // Burst Time
    int prior;   // Priority (lower number = higher priority)
    int wt;      // Waiting Time
    int tt;      // Turnaround Time
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
        printf("Enter burst time for P%d: ", p[i].pid);
        scanf("%d", &p[i].bt);
        printf("Enter priority for P%d (lower number = higher priority): ", p[i].pid);
        scanf("%d", &p[i].prior);
    }

    // Sort by priority - Selection Sort (lower priority number = higher priority)
    for(i = 0; i < n-1; i++) {
        int min_idx = i;
        for(j = i+1; j < n; j++) {
            if(p[j].prior < p[min_idx].prior) {   // < because lower number = higher priority
                min_idx = j;
            }
        }
        // Swap
        temp = p[i];
        p[i] = p[min_idx];
        p[min_idx] = temp;
    }

    // Calculate waiting time & turnaround time (all arrive at t=0)
    p[0].wt = 0;
    p[0].tt = p[0].bt;

    for(i = 1; i < n; i++) {
        p[i].wt = p[i-1].tt;
        p[i].tt = p[i].wt + p[i].bt;
    }

    // Calculate totals
    for(i = 0; i < n; i++) {
        totwt += p[i].wt;
        tottt += p[i].tt;
    }

    avgwt = totwt / n;
    avgtt = tottt / n;

    // Output
    printf("\n");
    printf("╔════════════╦════════════╦════════════╦════════════╦══════════╗\n");
    printf("║ Process ID ║ Priority   ║ Burst Time ║  Wait Time ║ Turnaround║\n");
    printf("╠════════════╬════════════╬════════════╬════════════╬══════════╣\n");

    for(i = 0; i < n; i++) {
        printf("║     P%-2d    ║     %3d    ║     %3d    ║     %3d    ║    %3d   ║\n",
               p[i].pid, p[i].prior, p[i].bt, p[i].wt, p[i].tt);
    }

    printf("╚════════════╩════════════╩════════════╩════════════╩══════════╝\n");

    printf("\nAverage Waiting Time    = %.2f\n", avgwt);
    printf("Average Turnaround Time   = %.2f\n\n", avgtt);

    return 0;
}