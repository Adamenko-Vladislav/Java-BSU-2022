package by.Adamenko.quizer;

import by.Adamenko.quizer.task_generators.EquationTaskGenerator;
import by.Adamenko.quizer.task_generators.TaskGenerator;
import by.Adamenko.quizer.tasks.Task;

import java.util.HashMap;
import java.util.Map;

class Quiz {
    private final TaskGenerator taskGenerator;
    private final int amountOfTasks;
    private int wrongAnswers = 0;
    private int correctAnswers = 0;
    private int wrongFormat = 0;
    private Task currentTask;
    private Result lastResult;

    Quiz(TaskGenerator generator, int taskCount) {
        taskGenerator = generator;
        amountOfTasks = taskCount;
        lastResult = Result.OK;
    }

    static Map<String, Quiz> getQuizMap() {
        Map<String, Quiz> tests = new HashMap<String, Quiz>();
        tests.put("EqTaskOnlyPlus", new Quiz(new EquationTaskGenerator(0, 10, true, false, false, false), 5));
        tests.put("EqTaskAll", new Quiz(new EquationTaskGenerator(-10, 10, true, true, true, true), 5));
        return tests;
    }
    Task nextTask() {
        if (lastResult != Result.INCORRECT_INPUT) {
            currentTask = taskGenerator.generate();
        }
        return currentTask;
    }

    Result provideAnswer(String answer) {
        lastResult = currentTask.validate(answer);
        switch (lastResult) {
            case WRONG -> wrongAnswers++;
            case OK -> correctAnswers++;
            case INCORRECT_INPUT -> wrongFormat++;
        }
        return lastResult;
    }

    boolean isFinished() {
        return correctAnswers + wrongAnswers == amountOfTasks;
    }

    int getCorrectAnswerNumber() {
        return correctAnswers;
    }

    int getWrongAnswerNumber() {
        return wrongAnswers;
    }

    int getIncorrectInputNumber() {
        return wrongFormat;
    }

    double getMark() {
        return (double) correctAnswers / (double) amountOfTasks * 10.0;
    }
}
