package com.flowjava.shared;

public class EnumsApp {

    public enum enumProcessStatus{
        open,
        in_progress,
        finnished;
    }


    public enum enumActivityStatus{
        inactive,
        active,
        pending;
    }

    public enum enumActivityTags{
        start,
        process,
        end;
    }
}