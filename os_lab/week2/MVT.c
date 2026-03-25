//MVT Simulation
#include <stdio.h>

#define MAX_PROCESSES 20

int main() {
    int totalMemory;
    int availableMemory;
    int n, i;
    int processSize[MAX_PROCESSES];

    printf("Enter total memory available (in KB): ");
    scanf("%d", &totalMemory);
    availableMemory = totalMemory;
    printf("Enter number of processes: ");
    scanf("%d", &n);

    if (n > MAX_PROCESSES) {
        printf("Process limit exceeded.\n");
        return 0;
    }

    for (i = 0; i < n; i++) {
        printf("Enter memory required for Process %d (in KB): ", i + 1);
        scanf("%d", &processSize[i]);
    }

    printf("\n--- MVT Memory Allocation Table ---\n");
    printf("Process\tMemory Required\tStatus\n");

    for (i = 0; i < n; i++) {
        if (processSize[i] <= availableMemory) {

            printf("%d\t%d KB\t\tAllocated\n", i + 1, processSize[i]);
            availableMemory -= processSize[i];

        } else {
            printf("%d\t%d KB\t\tNot Allocated\n", i + 1, processSize[i]);
        }
    }
    
    printf("\nTotal Memory = %d KB", totalMemory);
    printf("\nAvailable Memory = %d KB\n", availableMemory);
    return 0;
}