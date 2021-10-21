public class SimProcess {


    private int pid;
    private String procName;
    private int totalInstructions;
    private ProcessState processState;
    protected ProcessControlBlock pcb;


    public SimProcess(int pid, String procName, int totalInstructions) {
        this.pid = pid;
        this.procName = procName;
        this.totalInstructions = totalInstructions;
        this.processState = ProcessState.READY;
    }

    public ProcessState execute(int i) {
        ProcessState state;


        if (i >= totalInstructions) {
            state = ProcessState.FINISHED;
        } else {
            int randomInt = (int) (100 * Math.random());
            if (randomInt <= 15) {
                state = ProcessState.BLOCKED;

            } else {
                state = ProcessState.READY;
            }

        }
        return state;
    }


    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProcName() {
        return procName;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }

    public int getTotalInstructions() {
        return totalInstructions;
    }

    public void setTotalInstructions(int totalInstructions) {
        this.totalInstructions = totalInstructions;
    }

    public String toString(int currentInstruction) {
        StringBuilder builder = new StringBuilder();

        builder.append("Proc " + procName);
        builder.append(", PID: " + pid);
        builder.append(" executing instruction: " + currentInstruction);
        return builder.toString();
    }

    public ProcessState getProcessState() {
        return processState;
    }

    public void setProcessState(ProcessState processState) {
        this.processState = processState;
    }
}
