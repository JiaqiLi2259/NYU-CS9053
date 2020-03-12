package edu.nyu.cs9053.homework11;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Random;

/**
 * User: blangel
 * Date: 11/23/14
 * Time: 4:31 PM
 */
public class BlockingChatter implements Chatter {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final int MAX_BUFFER_SIZE = 1024;
    private static final int MAX_LINE_NUMBER = 21700;
    private final InputStream chatServerInput;
    private final OutputStream chatServerOutput;
    private final InputStream userInput;
    private final String[] bookMobyDick;
    private final int totalLineNumber;

    public BlockingChatter(InputStream chatServerInput, OutputStream chatServerOutput, InputStream userInput) {
        // TODO
        this.chatServerInput = chatServerInput;
        this.chatServerOutput = chatServerOutput;
        this.userInput = userInput;
        this.bookMobyDick = new String[MAX_LINE_NUMBER];
        this.totalLineNumber = loadBookContent();
    }

    @Override public void run() {
        // TODO
        startUserInputThread();
        startChatServerInputThread();
    }

    private void easterEgg() throws IOException {
        Random random = new Random();
        int randomLineNumber = random.nextInt(totalLineNumber);
        byte[] bookContentBuffer = bookMobyDick[randomLineNumber].getBytes(UTF8);
        chatServerOutput.write(bookContentBuffer, 0, bookContentBuffer.length);
    }

    private int loadBookContent() {
        String fileName = "src/main/resources/Moby Dick.txt";
        BufferedReader bufferedReader = null;
        int validLineNumber = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                if(!line.trim().equals("")) {
                    bookMobyDick[validLineNumber++] = line;
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.printf("Failed to find the file %s%n", fileName);
        } catch (IOException ioe) {
            System.out.printf("Failed to read - %s%n", ioe.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ioe) {
                System.out.printf("Failed to close the BufferedReader - %s%n", ioe.getMessage());
            }
        }
        return validLineNumber;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void startUserInputThread() {
        Thread userInputThread = new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] buffer = new byte[MAX_BUFFER_SIZE];
                while (true) {
                    try {
                        int read = userInput.read(buffer);
                        if (read != -1) {
                            chatServerOutput.write(buffer, 0, read);
                        }
                    } catch (IOException ioe) {
                        System.out.printf("IOException - %s%n", ioe.getMessage());
                    }
                }
            }
        });
        userInputThread.start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private void startChatServerInputThread() {
        Thread chatServerInputThread = new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] buffer = new byte[MAX_BUFFER_SIZE];
                String easterEggText = "java";
                while (true) {
                    try {
                        Arrays.fill(buffer, (byte) 0);
                        int read = chatServerInput.read(buffer, 0, MAX_BUFFER_SIZE);
                        if (read != -1) {
                            String messageFromServer = new String(buffer, UTF8);
                            if (messageFromServer.contains(easterEggText)) {
                                easterEgg();
                            }
                            System.out.printf("%s%n", messageFromServer);
                        }
                    } catch (IOException ioe) {
                        System.out.printf("IOException - %s%n", ioe.getMessage());
                    }
                }
            }
        });
        chatServerInputThread.start();
    }

}
