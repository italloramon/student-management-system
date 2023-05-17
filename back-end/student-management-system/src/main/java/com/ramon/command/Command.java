package com.ramon.command;

public interface Command {
        int DELETE_STUDENT = 1;
        int RANKING_STUDENTS = 2;
        int GET_STUDENT = 3;
        int GET_STUDENTS = 4;

        Object execute();
}
