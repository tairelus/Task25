package bjs.task25;

/**
 * Created by YM on 20.12.2015.
 */
public class MessageLoop implements Runnable{
    /**Array of the messages*/
    private String[] messagesArray;
    /**Interval between messages in ms*/
    private int messagesInterval;

    /**Current thread*/
    private Thread thread;

    /**
     * Constructs MessageLoop with several messages
     */
    public MessageLoop(String threadName) {
        thread = new Thread(this, threadName);
    }

    @Override
    public void run() {
        //In this example we stopped this thread with "return" in catch clause. But if run() method does not contain
        //sleep() (or other methods which throws exceptions) we need to check "interrupted" flag. For example:
        //if (thread.isInterrupted()) {
        //    return;
        // }

        //Note, once the exception is thrown, the thread is no longer in an interrupted state. So isInterrupted()
        //returns false!!!

        for (int i = 0; i < messagesArray.length; i++) {
            System.out.println(messagesArray[i]);

            try{
                Thread.sleep(this.messagesInterval);
            }
            catch(InterruptedException e) {
                System.out.println(thread.getName() + ": " + InterruptedException.class.getSimpleName() + " \"" + e.getMessage() + "\"");
                System.out.println(thread.getName() + ": task was not completed!");

                return;
            }
        }

        System.out.println(thread.getName() + ": task completed successfully!");
    }

    /**
     * Gets current thread
     * @return
     */
    public Thread getThread() {
        return thread;
    }

    /**
     * Starts message loop thread
     * @param messagesNum Number of the messages
     * @param messagesInterval Interval between messages, ms
     */
    public void start(int messagesNum, int messagesInterval) {
        this.messagesInterval = messagesInterval;
        messagesArray = new String[messagesNum];

        for (int i = 0; i < messagesArray.length; i++) {
            StringBuilder message = new StringBuilder(messagesNum);

            message.append("\t");
            message.append(thread.getName());
            message.append(": ");
            message.append("Array message #");
            message.append(i + 1);

            messagesArray[i] = message.toString();
        }

        thread.start();
    }

    /**
     * Wrapper for Thread.join
     */
    public void join(long millis) {
        try{
            thread.join(millis);
        }
        catch(InterruptedException e) {
            System.out.println(thread.getName() + ": " + InterruptedException.class.getSimpleName() + " \"" + e.getMessage() + "\"");
        }
    }
}
