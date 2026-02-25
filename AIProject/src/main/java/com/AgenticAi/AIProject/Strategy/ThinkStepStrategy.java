package com.AgenticAi.AIProject.Strategy;



import org.springframework.stereotype.Component;

import com.AgenticAi.AIProject.Executor.ExecutionContext;
import com.AgenticAi.AIProject.Planner.PlanStep;
import com.AgenticAi.AIProject.Planner.StepType;


import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ThinkStepStrategy implements StepExecutionStrategy {



    @Override
    public StepType getType() {
        return StepType.THINK;
    }

    @Override
    public Mono<String> execute(ExecutionContext context, PlanStep step) {

        context.getVariables().put("reasoningTask", step.getInput());

        return Mono.just("THINK_DONE");
    }
}