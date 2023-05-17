package com.ramon.state;

import com.ramon.model.Responsable;

public class WithoutStudents implements State{
    @Override
    public void next(Responsable responsable) {
        if (responsable.getStudents().size() > 0) {
            responsable.setState(new WithStudentsState());
            responsable.setWithStudents(true);
        }
    }

    @Override
    public String printStatus() {
        return "The responsable dont have any students";
    }
}
