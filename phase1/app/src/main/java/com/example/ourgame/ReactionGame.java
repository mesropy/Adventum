package com.example.ourgame;

enum State {
    INSTRUCTION,
    WAITING,
    GO,
    TIME,
    EARLY,
    DONE
}

public class ReactionGame extends Game{

    private String instructions;

    ReactionGame(){
        super();
        instructions = "When the screen turns green, tap as quickly as you can.";
        initializeNumLevels();
        initializeInstructions();
    }

    @Override
    void initializeNumLevels() {
        setNumLevels(5);
    }

    @Override
    void initializeInstructions() {
        setInstructions(instructions);
    }

    @Override
    void updateStatistics() {
        // TODO: Update stats using your average time
    }
}
