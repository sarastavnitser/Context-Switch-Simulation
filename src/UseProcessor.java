import java.util.ArrayList;

public class UseProcessor {


    public static void main(String[] args) {
        try {

            SimProcess proc0 = (new SimProcess(0, "Proc0", 123));
            SimProcess proc1 = new SimProcess(1, "Proc1", 210);
            SimProcess proc2 = (new SimProcess(2, "Proc2", 123));
            SimProcess proc3 = new SimProcess(3, "Proc3", 302);
            SimProcess proc4 = ((new SimProcess(4, "Proc4", 123)));
            SimProcess proc5 = ((new SimProcess(5, "Proc5", 232)));
            SimProcess proc6 = ((new SimProcess(6, "Proc6", 111)));
            SimProcess proc7 = (new SimProcess(7, "Proc7", 304));
            SimProcess proc8 = ((new SimProcess(8, "Proc8", 105)));
            SimProcess proc9 = ((new SimProcess(9, "Proc9", 211)));

            ProcessControlBlock pcb0 = new ProcessControlBlock(proc0);
            ProcessControlBlock pcb1 = new ProcessControlBlock(proc1);
            ProcessControlBlock pcb2 = new ProcessControlBlock(proc2);
            ProcessControlBlock pcb3 = new ProcessControlBlock(proc3);
            ProcessControlBlock pcb4 = new ProcessControlBlock(proc4);
            ProcessControlBlock pcb5 = new ProcessControlBlock(proc5);
            ProcessControlBlock pcb6 = new ProcessControlBlock(proc6);
            ProcessControlBlock pcb7 = new ProcessControlBlock(proc7);
            ProcessControlBlock pcb8 = new ProcessControlBlock(proc8);
            ProcessControlBlock pcb9 = new ProcessControlBlock(proc9);

            ArrayList<ProcessControlBlock> ready = new ArrayList<ProcessControlBlock>();
            ArrayList<ProcessControlBlock> blocked = new ArrayList<ProcessControlBlock>();

            ready.add(pcb0);
            ready.add(pcb1);
            ready.add(pcb2);
            ready.add(pcb3);
            ready.add(pcb4);
            ready.add(pcb5);
            ready.add(pcb6);
            ready.add(pcb7);
            ready.add(pcb8);
            ready.add(pcb9);


            SimProcessor processor = new SimProcessor(ready, blocked);
            ProcessControlBlock currentPcb = ready.get(0);
            int currentInstruction = ready.get(0).getCurrentInstruction();
            ProcessState currentState = ready.get(0).getProcess().getProcessState();

            final  int QUANTUM = 5;
            int quantumCounter = 0;

            for (int i = 0; i < 3000; i++) {
                if (quantumCounter < QUANTUM) {
                    if (ready.get(0).getProcess().getProcessState() == ProcessState.READY) {
                        if (processor.getReg1() == -1) {
                            System.out.print("Step " + i + " ");
                            processor.contextSwitch(0);
                            System.out.println(processor.getContextSwitchMessage());
                        } else {
                            System.out.print("Step " + i + " ");
                            processor.executeNextInstruction();
                            quantumCounter += 1;
                            if (ready.get(0).getProcess().getProcessState() == ProcessState.BLOCKED) {
                                System.out.println("\n*** Process Blocked ***\n");
                            } else if (ready.get(0).getProcess().getProcessState() == ProcessState.FINISHED) {
                                System.out.println("\n*** Process Finished ***\n");
                            }
                        }
                    } else if (ready.get(0).getProcess().getProcessState() == ProcessState.BLOCKED) {
                        System.out.print("Step " + i + " ");
                        quantumCounter = 0;
                        processor.contextSwitch(1);
                        System.out.println(processor.getContextSwitchMessage());
                        blocked.add(ready.get(0));
                        ready.remove(0);
                    } else {
                        System.out.print("Step " + i + " ");
                        quantumCounter = 0;
                        processor.contextSwitch(0);
                        System.out.println(processor.getContextSwitchMessage());
                        ready.remove(0);
                    }
                } else if (ready.get(0).getProcess().getProcessState() == ProcessState.BLOCKED) {
                    System.out.print("Step " + i + " ");
                    quantumCounter = 0;
                    processor.contextSwitch(1);
                    System.out.println(processor.getContextSwitchMessage());
                    blocked.add(ready.get(0));
                    ready.remove(0);
                } else {
                    if (ready.get(0).getProcess().getProcessState() != ProcessState.BLOCKED) {
                        System.out.println("\n*** Quantum Expired ***\n");
                        System.out.print("Step " + i + " ");
                        quantumCounter = 0;
                        processor.contextSwitch(1);
                        System.out.println(processor.getContextSwitchMessage());

                    } else {
                        System.out.print("Step " + i + " ");
                        quantumCounter = 0;
                        processor.contextSwitch(1);
                        System.out.println(processor.getContextSwitchMessage());
                        blocked.add(ready.get(0));
                        ready.remove(0);
                    }

                }
            }
        } catch (NullPointerException e) {
            System.out.println("Error found");
        }
    }
}

