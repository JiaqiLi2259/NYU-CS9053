package edu.nyu.cs9053.homework8;

import java.util.*;

public class LambdaWeightedScheduler implements Scheduler{

    private final List<WeightedLambdaJob> jobList;
    private final List<WeightedLambdaJob> subList;
    private final int size;
    private final int[] maxValue;
    private final int[] mostRecentCompatibleJobNumber;
    private final int[][] recordOfJobNumber;

    public LambdaWeightedScheduler(List<WeightedLambdaJob> jobList) {
        this.jobList = jobList;
        this.size = jobList.size();
        this.subList = new ArrayList<>(size);
        this.maxValue = new int[size+1];
        this.mostRecentCompatibleJobNumber = new int[size+1];
        this.recordOfJobNumber = new int[size+1][2];
        this.initialize();
        this.findSubList();
    }

    private void initialize() {
        maxValue[0] = 0;
        Collections.sort(jobList, new Comparator<WeightedLambdaJob>(){
            @Override
            public int compare(WeightedLambdaJob job1, WeightedLambdaJob job2) {
                return job1.getFinalTime() - job2.getFinalTime();
            }
        });
        Arrays.fill(mostRecentCompatibleJobNumber, 0);
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j < i; j++) {
                if (jobList.get(j-1).getFinalTime() > jobList.get(i-1).getStartingTime()) {
                    break;
                }else {
                    mostRecentCompatibleJobNumber[i] = j;
                }
            }
        }
        for (int[] array : recordOfJobNumber) {
            Arrays.fill(array, 0);
        }
    }

    @Override
    public void findSubList() {
        for (int i = 1; i <= size; i++) {
            int valueOfChoosingNewJob = maxValue[mostRecentCompatibleJobNumber[i]] + jobList.get(i-1).getValue();
            int valueOfNotChoosingNewJob = maxValue[i-1];
            if (valueOfChoosingNewJob > valueOfNotChoosingNewJob) {
                maxValue[i] = valueOfChoosingNewJob;
                recordOfJobNumber[i][0] = 1;
                recordOfJobNumber[i][1] = mostRecentCompatibleJobNumber[i];
            }else {
                maxValue[i] = valueOfNotChoosingNewJob;
                recordOfJobNumber[i][0] = 0;
                recordOfJobNumber[i][1] = i - 1;
            }
        }
        Deque<WeightedLambdaJob> stack = new ArrayDeque<>(size);
        int jobNumber = size;
        while (jobNumber > 0) {
            if (recordOfJobNumber[jobNumber][0] == 1) {
                stack.push(jobList.get(jobNumber - 1));
            }
            jobNumber = recordOfJobNumber[jobNumber][1];
        }
        while (!stack.isEmpty()) {
            subList.add(stack.pop());
        }
    }

    @Override
    public List<WeightedLambdaJob> getSubList() {
        return subList;
    }

}
