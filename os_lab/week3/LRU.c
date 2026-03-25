#include <stdio.h>

int main() {
    int i, j, k, min, rs[25], frame[10], count[10], flag[25];
    int n, f, pf = 0, next = 1;

    printf("Enter the length of reference string: ");
    scanf("%d", &n);

    printf("Enter the reference string: ");
    for (i = 0; i < n; i++) {
        scanf("%d", &rs[i]);
        // flag[i] = 0;
    }

    printf("Enter the number of frames: ");
    scanf("%d", &f);

    for (i = 0; i < f; i++) {
        count[i] = 0;
        frame[i] = -1;
    }

    printf("\nThe Page Replacement process is:\n");

    for (i = 0; i < n; i++) {
        int avail = 0;

        // Check if page is already in frame
        for (j = 0; j < f; j++) {
            if (frame[j] == rs[i]) {
                avail = 1;
                count[j] = next++;
                break;
            }
        }
        // If page not found → page fault
        if (avail == 0) {
            pf++;

            // If empty frame available
            if (i < f) {
                frame[i] = rs[i];
                count[i] = next++;
            } else {
                // Find least recently used page
                min = 0;
                for (k = 1; k < f; k++) {
                    if (count[k] < count[min]) {
                        min = k;
                    }
                }
                frame[min] = rs[i];
                count[min] = next++;
            }
        }
        // Print current frame status
        printf("Page %d -> Frame: ", rs[i]);
        for (j = 0; j < f; j++) {
            if (frame[j] != -1)
                printf("%d\t", frame[j]);
            else
                printf("-\t");
        }
        printf("\n");
    }

    printf("\nTotal number of page faults using LRU = %d\n", pf);

    return 0;
}