public class ProcessControlBlock {

    public void setProcess(SimProcess process) {
        this.process = process;
    }

    private SimProcess process;

    private int currentInstruction = 0;

    private int reg1;
    private int reg2;
    private int reg3;
    private int reg4;

    public ProcessControlBlock(SimProcess process) {
        this.process = process;
    }

    public SimProcess getProcess() {
        return process;
    }

    public void addCurrentInstruction() {
        currentInstruction += 1;
    }


    public int getReg1() {
        return reg1;
    }

    public void setReg1(int reg1) {
        this.reg1 = reg1;
    }

    public int getReg2() {
        return reg2;
    }

    public void setReg2(int reg2) {
        this.reg2 = reg2;
    }

    public int getReg3() {
        return reg3;
    }

    public void setReg3(int reg3) {
        this.reg3 = reg3;
    }

    public int getReg4() {
        return reg4;
    }

    public void setReg4(int reg4) {
        this.reg4 = reg4;
    }


    public int getCurrentInstruction() {
        return currentInstruction;
    }

    public void setCurrentInstruction(int currentInstruction) {
        this.currentInstruction = currentInstruction;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Instruction: " + getCurrentInstruction());
        str.append(" R1: " + getReg1());
        str.append(" R2: " + getReg2());
        str.append(" R3: " + getReg3());
        str.append(" R1: " + getReg1());
        return str.toString();
    }
}
