package edu.nyu.cs9053.homework8;

import java.util.List;

public interface Scheduler {

    void findSubList();

    List<? extends LambdaJob> getSubList();

}
