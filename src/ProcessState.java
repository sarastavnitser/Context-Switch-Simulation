public enum ProcessState {
    //    ADY, BLOCKED, SUSPENDED_READY, SUSPENDED_BLOCKED,andFINISHED
    READY("READY"),
    BLOCKED("BLOCKED"),
    SUSPENDED_READY("SUSPENDED_READY"),
    FINISHED("FINISHED");

    private final String state;

    ProcessState(String processState) {
        this.state = processState;
    }

    public String state() {
        return state;
    }
}