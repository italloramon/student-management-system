package com.ramon.command;

public interface Command {
        int DELETE_STUDENT = 1;
        int RANKING_STUDENTS = 2;

        Object execute();
}
