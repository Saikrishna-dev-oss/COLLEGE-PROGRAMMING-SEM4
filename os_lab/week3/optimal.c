#include <stdio.h>

int main() {
    int n, pg[30], fr[10];
    int count[10], i, j, k, fault, f, flag, temp, current, c, dist, max, m, cnt, p, x;

    fault = 0;
    dist = 0;
    k = 0;

    printf("Enter the total number of pages: ");
    scanf("%d", &n);

    printf("Enter the page sequence: ");
    for (i = 0; i < n; i++) {
        scanf("%d", &pg[i]);
    }

    printf("Enter frame size: ");
    scanf("%d", &f);

    for (i = 0; i < f; i++) {
        count[i] = 0;
        fr[i] = -1;
    }

    for (i = 0; i < n; i++) {
        flag = 0;
        temp = pg[i];

        // Check if page is already in frame
        for (j = 0; j < f; j++) {
            if (temp == fr[j]) {
                flag = 1;
                break;
            }
        }

        // If not present and frames are not full
        if ((flag == 0) && (k < f)) {
            fault++;
            fr[k] = temp;
            k++;
        }
        // If not present and frames are full → Optimal replacement
        else if ((flag == 0) && (k == f)) {
            fault++;

            for (cnt = 0; cnt < f; cnt++) {
                count[cnt] = 0;
                current = fr[cnt];

                // Find next use distance
                for (c = i + 1; c < n; c++) {
                    if (current != pg[c]) {
                        count[cnt]++;
                    } else {
                        break;
                    }
                }
            }

            // Find the page with maximum distance (not used for longest time)
            max = -1;
            p = 0;
            for (m = 0; m < f; m++) {
                if (count[m] > max) {
                    max = count[m];
                    p = m;
                }
            }

            fr[p] = temp;
        }

        // Print current frame status
        printf("\nPage %d -> Frame: ", pg[i]);
        for (x = 0; x < f; x++) {
            if (fr[x] != -1)
                printf("%d\t", fr[x]);
            else
                printf("-\t");
        }
    }

    printf("\n\nTotal number of faults = %d\n", fault);

    return 0;
}