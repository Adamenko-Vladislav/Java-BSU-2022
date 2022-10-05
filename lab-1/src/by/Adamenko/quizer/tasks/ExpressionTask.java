package by.Adamenko.quizer.tasks;

import by.Adamenko.quizer.Operator;
import by.Adamenko.quizer.Result;

public class ExpressionTask implements Task {

    private String Expression;
    private String Answer;

    public ExpressionTask(int value_1,
                          int value_2,
                          Operator operator) {
        Expression = "x";
        switch (operator) {
            case Plus -> {
                Expression += " + ";
                Answer = String.valueOf((long) value_2 - value_1);
            }
            case Minus -> {
                Expression += " - ";
                Answer = String.valueOf((long) value_1 + value_2);
            }
            case Divide -> {
                Expression += " / ";
                Answer = String.valueOf((long) value_1 * value_2);
            }
            case Multiple -> {
                Expression += " * ";
                Answer = String.valueOf((long) value_2 / value_1);
            }
        }
        Expression += String.valueOf(value_1) + " = " + String.valueOf(value_2);
    }
    @Override
    public String getText() {
        return Expression;
    }

    @Override
    public Result validate(String answer) {
        for (int i = 0; i < answer.length(); ++i) {
            if (!Character.isDigit(answer.charAt(i))) {
                if (i == 0 && answer.charAt(i) == '-') {
                    continue;
                }
                return Result.INCORRECT_INPUT;
            }
        }
        if (answer.equals(Answer)) {
            return Result.OK;
        }
        return Result.WRONG;
    }
}
