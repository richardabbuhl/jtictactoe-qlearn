package com.jmentor.jqlearn;

import com.jmentor.jtictactoe.Minimax;

public record QMinimax(int lookAheads, QLearn qlearn) extends Minimax {

    public QMinimax {
        super(lookAheads);
    }

    public double Evaluate(int[] b, int value) {
        double result = qlearn.getValue(b);
        return result;
    }
}
