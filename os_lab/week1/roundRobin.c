#include <stdio.h>
#define MAX 30

struct process {
    int pid;
    int bt;          // original burst time
    int remaining_bt;
    int wt;
    int tt;
};

int main() {
    struct process p[MAX];
    int n, quantum, i;
    int completed = 0;
    int current_time = 0;
    float total_wt = 0, total_tt = 0;

    printf("Enter number of processes: ");
    scanf("%d", &n);

    printf("\nEnter burst time for each process:\n");
    for (i = 0; i < n; i++) {
        p[i].pid = i + 1;
        printf("P%d : ", p[i].pid);
        scanf("%d", &p[i].bt);
        p[i].remaining_bt = p[i].bt;
        // p[i].wt = 0;
        // p[i].tt = 0;
    }

    printf("\nEnter time quantum: ");
    scanf("%d", &quantum);

    printf("\nGantt Chart:\n");
    printf("------------------------------------------------\n");

    while (completed < n) {
        for (i = 0; i < n; i++) {
            if (p[i].remaining_bt > 0) {
                printf("P%d ", p[i].pid);

                // Process runs for min(quantum, remaining time)
                int exec_time = (p[i].remaining_bt > quantum) ? quantum : p[i].remaining_bt;

                current_time += exec_time;
                p[i].remaining_bt -= exec_time;

                // If process completes now
                if (p[i].remaining_bt == 0) {
                    p[i].tt = current_time;
                    p[i].wt = p[i].tt - p[i].bt;
                    total_wt += p[i].wt;
                    total_tt += p[i].tt;
                    completed++;
                }
            }
        }
    }

    printf("\n\n------------------------------------------------\n");
    printf("Process   BT   WT    TAT\n");
    printf("------------------------------------------------\n");

    for (i = 0; i < n; i++) {
        printf("P%-2d     %3d   %3d    %3d\n",
               p[i].pid, p[i].bt, p[i].wt, p[i].tt);
    }

    printf("------------------------------------------------\n");

    printf("\nAverage Waiting Time    = %.2f\n", total_wt / n);
    printf("Average Turnaround Time = %.2f\n\n", total_tt / n);

    return 0;
}