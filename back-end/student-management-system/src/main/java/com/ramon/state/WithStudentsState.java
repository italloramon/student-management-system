package com.ramon.state;

import com.ramon.model.Responsable;

public class WithStudentsState implements State{
    @Override
    public void next(Responsable responsable) {
        if (responsable.getStudents().size() <= 0) {
            responsable.setState(new WithoutStudents());
            responsable.setWithStudents(false);
        } else {
            responsable.setWithStudents(true);
        }
    }

    @Override
    public String printStatus() {
        return "These are the students that you have: ";
    }
}
