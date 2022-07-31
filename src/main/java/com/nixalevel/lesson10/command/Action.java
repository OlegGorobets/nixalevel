package com.nixalevel.lesson10.command;

public enum Action {
    CREATE("Create vehicle", new Create()),
    UPDATE("Change vehicles", new Change()),
    DELETE("Remove vehicle", new Remove()),
    PRINT("Print vehicles", new Print()),
    EXIT("Exit", null);

    private final String name;
    private final Command command;

    Action(String name, Command command) {
        this.name = name;
        this.command = command;
    }

    public String getName() {
        return name;
    }

    public Command getCommand() {
        return command;
    }

    public Command execute() {
        if (command != null) {
            command.execute();
        }
        return command;
    }
}
