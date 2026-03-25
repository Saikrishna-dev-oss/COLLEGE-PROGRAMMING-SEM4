package practise;
import java.util.*;

class Job {
    char id;
    int deadline;
    int profit;

    Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobSequencing {

    public static void jobSequencing(Job[] jobs) {

        // Step 1: Sort jobs by profit (descending order)
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Step 2: Find maximum deadline
        int maxDeadline = 0;
        for (Job job : jobs) {
            if (job.deadline > maxDeadline)
                maxDeadline = job.deadline;
        }

        // Step 3: Create slots
        char[] result = new char[maxDeadline];
        boolean[] slot = new boolean[maxDeadline];

        // Step 4: Assign jobs to slots
        for (Job job : jobs) {
            for (int j = Math.min(maxDeadline, job.deadline) - 1; j >= 0; j--) {
                if (!slot[j]) {
                    slot[j] = true;
                    result[j] = job.id;
                    break;
                }
            }
        }

        // Step 5: Print result
        System.out.println("Job sequence for maximum profit:");
        for (int i = 0; i < maxDeadline; i++) {
            if (slot[i])
                System.out.print(result[i] + " ");
        }
    }

    public static void main(String[] args) {

        Job[] jobs = {
                new Job('A', 2, 100),
                new Job('B', 1, 19),
                new Job('C', 2, 27),
                new Job('D', 1, 25),
                new Job('E', 3, 15)
        };

        jobSequencing(jobs);
    }
}