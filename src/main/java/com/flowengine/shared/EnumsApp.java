package com.flowengine.shared;

public class EnumsApp {

    public enum enumProcessStatus{
        open,
        in_progress,
        finnished
    }


    public enum enumActivityStatus{
        inactive,
        active,
        pending
    }

    public enum enumActivityTags{
        start,
        process,
        end
    }

    public enum enumVariableType{
        text,
        date,
        radio,
        file,
        textarea,
        checkbox
    }
}
