package com.ramon.state;

import com.ramon.model.Responsable;

public interface State {
    void next(Responsable responsable);
    String printStatus();
}
