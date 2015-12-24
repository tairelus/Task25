package bjs.task25;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter waiting limit for main thread in milliseconds:");
        int limit = scanner.nextInt();

        String currentThreadName = Thread.currentThread().getName();

        MessageLoop messageLoop = new MessageLoop("MessageLoop");
        System.out.println(currentThreadName + ": Start " + MessageLoop.class.getSimpleName() + " thread");

        messageLoop.start(3, 4000); //Should take 12 seconds
        System.out.println(currentThreadName + ": Waiting until " + MessageLoop.class.getSimpleName() + " will be finished");

        messageLoop.join(limit);

        if (messageLoop.getThread().isAlive()) {
            System.out.println(currentThreadName + ": I cannot wait more than " + limit+ " milliseconds!");

            //Once we set "interrupt" sleep(), wait(), join() methods will throw InterruptedException immediately
            messageLoop.getThread().interrupt();
        }
    }
}

/*
Enter waiting limit for main thread in milliseconds:
1000
main: Start MessageLoop thread
main: Waiting until MessageLoop will be finished
	MessageLoop: Array message #1
main: I cannot wait more than 1000 milliseconds!
MessageLoop: InterruptedException "sleep interrupted"
MessageLoop: task was not completed!

Enter waiting limit for main thread in milliseconds:
15000
main: Start MessageLoop thread
main: Waiting until MessageLoop will be finished
	MessageLoop: Array message #1
	MessageLoop: Array message #2
	MessageLoop: Array message #3
MessageLoop: task completed successfully!
 */