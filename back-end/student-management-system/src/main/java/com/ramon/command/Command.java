package com.ramon.command;

public interface Command {
        int DELETE_STUDENT = 1;
        int RANKING_STUDENTS = 2;
        int GET_STUDENT = 3;
        int GET_STUDENTS = 4;
        int SAVE_STUDENT = 5;

        default Object execute() {
                return null;
        }
        default Object executeWithException() throws Exception {
                return null;
        }
}
