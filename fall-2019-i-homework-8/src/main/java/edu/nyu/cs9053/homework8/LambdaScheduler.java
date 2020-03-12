package edu.nyu.cs9053.homework8;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class LambdaScheduler implements Scheduler {

    private final List<LambdaJob> jobList;
    private final List<LambdaJob> subList;

    public LambdaScheduler(List<LambdaJob> jobList) {
        this.jobList = jobList;
        this.subList = new ArrayList<>(this.jobList.size());
        this.findSubList();
    }

    @Override
    public void findSubList() {
       Collections.sort(jobList, new Comparator<LambdaJob>(){
           @Override
           public int compare(LambdaJob job1, LambdaJob job2) {
               return job1.getFinalTime() - job2.getFinalTime();
           }
       });
       Iterator<LambdaJob>	iter = jobList.iterator();
       LambdaJob previousJob = new LambdaJob(0, 0);
       while (iter.hasNext()) {
           LambdaJob currentJob = iter.next();
           if (currentJob.getStartingTime() >= previousJob.getFinalTime()) {
               subList.add(currentJob);
               previousJob = currentJob;
           }
       }
    }

    @Override
    public List<LambdaJob> getSubList() {
        return subList;
    }

}
