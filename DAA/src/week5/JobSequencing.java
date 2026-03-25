// JOB SEQUENCING
package week5;

import java.util.*;

public class JobSequencing {
    // Job class
    static class Job {
        char id;
        int deadline;
        int profit;

        Job(char id, int profit, int deadline) {
            this.id = id;
            this.profit = profit;
            this.deadline = deadline;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of jobs
        System.out.print("Enter number of jobs: ");
        int n = sc.nextInt();

        Job[] jobs = new Job[n];

        // Input job details
        // System.out.println("Enter job details (ID Deadline Profit):");
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the ID,Profit and Deadline of Job #" + (i + 1) + ": ");
            char id = sc.next().charAt(0);
            int profit = sc.nextInt();
            int deadline = sc.nextInt();
            jobs[i] = new Job(id,profit,deadline);
        }

        // Run job sequencing
        jobSequencing(jobs, n);

        sc.close();
    }

    static void jobSequencing(Job[] jobs, int n) {
        // Sort jobs by profit (descending)
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Find maximum deadline
        int maxDeadline = 0;
        for (Job job : jobs) {
            if (job.deadline > maxDeadline) {
                maxDeadline = job.deadline;
            }
        }

        // Create slot array
        char[] slot = new char[maxDeadline];
        boolean[] slotFilled = new boolean[maxDeadline];
        // Arrays.fill(slot, '-');

        int totalProfit = 0;

        System.out.println("\nJob-No   PROFIT   DEADLINE   SLOT ALLOTTED");

        // Schedule jobs
        for (Job job : jobs) {
            boolean scheduled = false;
            for (int j = Math.min(maxDeadline - 1, job.deadline - 1); j >= 0; j--) {
                if (!slotFilled[j]) {
                    slot[j] = job.id;
                    slotFilled[j] = true;
                    totalProfit += job.profit;
                    System.out.println(job.id + "        " + job.profit + "      " + job.deadline + "          [" + j
                            + " - " + (j + 1) + "]");
                    scheduled = true;
                    break;
                }
            }
            if (!scheduled) {
                System.out.println(job.id + "        " + job.profit + "      " + job.deadline + "          REJECTED");
            }
        }

        // Print slot assignment array
        System.out.println("\nSlot assignment Array");
        for (char c : slot) {
            // if (c != '-') {
                System.out.print(c + " ");
            // }
        }

        System.out.println("\n\nTotal Profit: " + totalProfit);
    }
}



