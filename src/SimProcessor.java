import java.util.ArrayList;


public class SimProcessor {

    private int reg1 = (int) (100 * Math.random());
    private int reg2 = (int) (100 * Math.random());
    private int reg3 = (int) (100 * Math.random());
    private int reg4 = (int) (100 * Math.random());


    private final int quantum = 5;
    private final ArrayList<ProcessControlBlock> ready;
    private final ArrayList<ProcessControlBlock> blocked;
    private ProcessControlBlock currentPcb;
    private int currentInstruction;
    private final ProcessState state;


    public SimProcessor(ArrayList<ProcessControlBlock> ready, ArrayList<ProcessControlBlock> blocked) {
        this.ready = ready;
        this.blocked = blocked;
        this.currentPcb = ready.get(0);
        this.currentInstruction = ready.get(0).getCurrentInstruction();
        this.state = ready.get(0).getProcess().getProcessState();

    }


    public ProcessState executeNextInstruction() {


        ProcessState state = ready.get(0).getProcess().execute(ready.get(0).getCurrentInstruction());
        System.out.println(ready.get(0).getProcess().toString(ready.get(0).getCurrentInstruction()));
        if (state.equals(ProcessState.READY)) {

            ready.get(0).addCurrentInstruction();
            resetRegisters();

        } else if (state.equals(ProcessState.BLOCKED)) {
            ready.get(0).getProcess().setProcessState(ProcessState.BLOCKED);
        } else {

            ready.get(0).getProcess().setProcessState(ProcessState.FINISHED);

        }
        wakeUp();
        return ready.get(0).getProcess().getProcessState();
    }


    public void resetRegisters() {
        setReg1((int) (1000 * Math.random()));
        setReg2((int) (1000 * Math.random()));
        setReg3((int) (1000 * Math.random()));
        setReg4((int) (1000 * Math.random()));
    }


    public void wakeUp() {
        for (int i = 0; i < blocked.size(); i++) {
            int randomInt = (int) (100 * Math.random());
            if (randomInt <= 30) {
                blocked.get(i).getProcess().setProcessState(ProcessState.READY);
                ready.add(blocked.get(i));
                blocked.remove(i);
            }
        }
    }

    public String getContextSwitchMessage() {
        return contextSwitchMessage;
    }

    public void setContextSwitchMessage(String contextSwitchMessage) {
        this.contextSwitchMessage = contextSwitchMessage;
    }

    private String contextSwitchMessage;


    private int contextKeeper = 5;

    public void contextSwitch(int i) {
        if (i == 0 || contextKeeper == 1) {
            setReg1(ready.get(0).getReg1());
            setReg2(ready.get(0).getReg2());
            setReg3(ready.get(0).getReg3());
            setReg4(ready.get(0).getReg4());
            currentPcb = ready.get(0);
            currentInstruction = ready.get(0).getCurrentInstruction();
            contextSwitchMessage = (" Context Switch: Restoring process: " + ready.get(0).getProcess().getPid() +
                    "\n" + "        Instruction: " + ready.get(0).getCurrentInstruction() + " R1: " + ready.get(0).getReg1() + "   R2: " +
                    ready.get(0).getReg2() + "   R3: " + ready.get(0).getReg3() +
                    "   R4: " + ready.get(0).getReg4() + "\n");
            wakeUp();
            setContextKeeper(0);
        } else {
            ready.get(0).setCurrentInstruction(ready.get(0).getCurrentInstruction());
            ready.get(0).setReg1(reg1);
            ready.get(0).setReg2(reg2);
            ready.get(0).setReg3(reg3);
            ready.get(0).setReg4(reg4);
//        this happens 1. a process is blocked 2. a process is finished 3. the quantum expires
            if (ready.get(0).getProcess().getProcessState() == ProcessState.READY) {


                contextSwitchMessage = (" Context Switch: Saving process: " + ready.get(0).getProcess().getPid() +
                        "\n" + "        Instruction: " + ready.get(0).getCurrentInstruction() + " R1: " + ready.get(0).getReg1() + "   R2: " +
                        ready.get(0).getReg2() + "   R3: " + ready.get(0).getReg3() +
                        "   R4: " + ready.get(0).getReg4() + "\n");
                ready.add(ready.get(0));
                ready.remove(0);
            }
            if (ready.get(0).getProcess().getProcessState() == ProcessState.FINISHED) {
                contextSwitchMessage = (" Context Switch: Saving process: " + ready.get(0).getProcess().getPid() +
                        "\n" + "        Instruction: " + ready.get(0).getCurrentInstruction() + " R1: " + ready.get(0).getReg1() + "   R2: " +
                        ready.get(0).getReg2() + "   R3: " + ready.get(0).getReg3() +
                        "   R4: " + ready.get(0).getReg4() + "\n");
                ready.remove(0);
            }
            if (ready.get(0).getProcess().getProcessState() == ProcessState.BLOCKED) {
                contextSwitchMessage = (" Context Switch: Saving process: " + ready.get(0).getProcess().getPid() +
                        "\n" + "        Instruction: " + ready.get(0).getCurrentInstruction() + "   R1: " + ready.get(0).getReg1() + "   R2: " +
                        ready.get(0).getReg2() + "   R3: " + ready.get(0).getReg3() +
                        "   R4: " + ready.get(0).getReg4() + "\n");


            }

            setReg1(-1);
            setReg2(-1);
            setReg3(-1);
            setReg4(-1);
            wakeUp();
            setContextKeeper(1);

        }
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

    public int getContextKeeper() {
        return contextKeeper;
    }

    public void setContextKeeper(int contextKeeper) {
        this.contextKeeper = contextKeeper;
    }
}
