package by.Adamenko.quizer.task_generators.math_task_generators;

import by.Adamenko.quizer.exceptions.NoOperators;
import by.Adamenko.quizer.tasks.math_tasks.EquationMathTask;
import by.Adamenko.quizer.tasks.math_tasks.Operator;

import java.util.EnumSet;

public class EquationMathTaskGenerator extends AbstractMathTaskGenerator {
    public EquationMathTaskGenerator(
            int minNumber,
            int maxNumber,
            EnumSet<Operator> operators
    ) {
       initialize(minNumber, maxNumber, operators);
    }

    public EquationMathTask generate() {
        try {
            if (operatorArrayList.isEmpty()) {
                throw new NoOperators("EquationMathTask");
            }
        } catch (NoOperators e) {
            e.fillInStackTrace();
            throw new RuntimeException(e);
        }
        int pos = rnd.nextInt(0, operatorArrayList.size());
        return new EquationMathTask(rnd.nextInt(min, max + 1),
                                rnd.nextInt(min, max + 1),
                                operatorArrayList.get(pos));
    }
}
