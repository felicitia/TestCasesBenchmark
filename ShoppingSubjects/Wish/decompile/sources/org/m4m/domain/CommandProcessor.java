package org.m4m.domain;

import java.util.ArrayList;
import java.util.Iterator;
import org.m4m.IProgressListener;

public class CommandProcessor implements ICommandProcessor {
    private static final MatchingCommands matchingCommands = new MatchingCommands();
    private volatile boolean isPaused = false;
    private final PairQueueSpecification pairQueueSpecification = new PairQueueSpecification(new MatchingCommands());
    private final ArrayList<OutputInputPair> pairs = new ArrayList<>();
    private final IProgressListener progressListener;
    private boolean stopped = false;

    public CommandProcessor(IProgressListener iProgressListener) {
        this.progressListener = iProgressListener;
    }

    public void add(OutputInputPair outputInputPair) {
        this.pairs.add(outputInputPair);
    }

    public void stop() {
        this.stopped = true;
    }

    public void process() {
        Iterator it = this.pairs.iterator();
        while (it.hasNext()) {
            OutputInputPair outputInputPair = (OutputInputPair) it.next();
            outputInputPair.output.fillCommandQueues();
            outputInputPair.input.fillCommandQueues();
        }
        while (!this.stopped) {
            Iterator it2 = this.pairs.iterator();
            while (it2.hasNext()) {
                processCommandPairs((OutputInputPair) it2.next());
            }
        }
    }

    private void processCommandPairs(OutputInputPair outputInputPair) {
        outputInputPair.output.fillCommandQueues();
        outputInputPair.input.fillCommandQueues();
        CommandQueue outputCommandQueue = outputInputPair.output.getOutputCommandQueue();
        CommandQueue inputCommandQueue = outputInputPair.input.getInputCommandQueue();
        while (this.pairQueueSpecification.satisfiedBy(outputCommandQueue, inputCommandQueue)) {
            checkIfPaused();
            Pair first = outputCommandQueue.first();
            Pair first2 = inputCommandQueue.first();
            if (!(first == null || first2 == null)) {
                if (first2.left == Command.NextPair) {
                    inputCommandQueue.dequeue();
                    return;
                } else if (first.left == Command.NextPair) {
                    outputCommandQueue.dequeue();
                    return;
                } else {
                    process(outputCommandQueue, inputCommandQueue, outputInputPair.commandHandlerFactory);
                }
            }
        }
    }

    private void process(CommandQueue commandQueue, CommandQueue commandQueue2, CommandHandlerFactory commandHandlerFactory) {
        Pair dequeMatchingCommands = dequeMatchingCommands(commandQueue, commandQueue2);
        process((Pair) dequeMatchingCommands.left, (Pair) dequeMatchingCommands.right, commandHandlerFactory);
    }

    private Pair<Pair<Command, Integer>, Pair<Command, Integer>> dequeMatchingCommands(CommandQueue commandQueue, CommandQueue commandQueue2) {
        Iterator it = matchingCommands.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            Pair first = commandQueue.first();
            Pair first2 = commandQueue2.first();
            if (!(first == null || first2 == null)) {
                if ((pair.left == null || pair.left == first.left) && (pair.right == null || pair.right == first2.left) && first.right == first2.right) {
                    if (pair.left != null) {
                        first = commandQueue.dequeue();
                    }
                    if (pair.right != null) {
                        first2 = commandQueue2.dequeue();
                    }
                    return new Pair<>(first, first2);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Pair (");
        sb.append(commandQueue.first());
        sb.append(", ");
        sb.append(commandQueue2.first());
        sb.append(") does not match.");
        throw new UnsupportedOperationException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void process(Pair<Command, Integer> pair, Pair<Command, Integer> pair2, CommandHandlerFactory commandHandlerFactory) {
        commandHandlerFactory.create(pair, pair2, this.progressListener).handle();
    }

    private synchronized void checkIfPaused() {
        while (this.isPaused) {
            try {
                this.progressListener.onMediaPause();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
