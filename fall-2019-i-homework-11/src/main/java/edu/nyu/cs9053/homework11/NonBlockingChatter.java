package edu.nyu.cs9053.homework11;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;

/**
 * User: blangel
 * Date: 11/23/14
 * Time: 4:32 PM
 */
public class NonBlockingChatter implements Chatter {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final int MAX_BUFFER_SIZE = 1024;
    private final SocketChannel chatServerChannel;
    private final Pipe.SourceChannel userInput;
    private final Selector selector;
    private final ByteBuffer readBuffer;
    private final ByteBuffer writeBuffer;
    private final ByteBuffer tempBuffer;

    public NonBlockingChatter(SocketChannel chatServerChannel,
                              Pipe.SourceChannel userInput) throws IOException {
        // TODO
        this.chatServerChannel = chatServerChannel;
        this.userInput = userInput;
        this.selector = Selector.open();
        this.readBuffer = ByteBuffer.allocate(MAX_BUFFER_SIZE);
        this.writeBuffer = ByteBuffer.allocate(MAX_BUFFER_SIZE);
        this.tempBuffer = ByteBuffer.allocate(MAX_BUFFER_SIZE);
        this.userInput.register(selector, SelectionKey.OP_READ);
        this.chatServerChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    }

    @Override public void run() {
        // TODO
        while (! Thread.currentThread().isInterrupted()) {
            try {
                handlingNIO();
            } catch (IOException ioe) {
                System.out.printf("IOException - %s%n", ioe.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    private void handlingNIO() throws IOException {
        int readyChannels = selector.select();
        if (readyChannels < 1) {
            return;
        }
        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
        while (keyIterator.hasNext()) {
            try {
                SelectionKey key = keyIterator.next();
                if (key.isReadable()) {
                    processReading(key);
                } else if (key.isWritable()) {
                    processWriting(key);
                }
            } catch (IOException ioe) {
                System.out.printf("IOException - %s%n", ioe.getMessage());
            } finally {
                keyIterator.remove();
            }
        }
    }

    private void processWriting(SelectionKey key) throws IOException {
        SocketChannel chatServerChannel = (SocketChannel) key.channel();
        if ((writeBuffer != null) && (writeBuffer.position() != 0)) {
            writeBuffer.flip();
            chatServerChannel.write(writeBuffer);
            writeBuffer.clear();
        }
    }

    private void processReading(SelectionKey key) throws IOException {
        if (key.channel() instanceof SocketChannel) {
            SocketChannel chatServerChannel = (SocketChannel) key.channel();
            int read = chatServerChannel.read(readBuffer);
            if (read > 0) {
                readBuffer.flip();
                CharsetDecoder decoder = UTF8.newDecoder();
                CharBuffer charBuffer = decoder.decode(readBuffer);
                System.out.printf("%s%n", charBuffer.toString());
                readBuffer.clear();
            } else {
                key.cancel();
            }
        } else if (key.channel() instanceof Pipe.SourceChannel) {
            Pipe.SourceChannel userInput = (Pipe.SourceChannel) key.channel();
            //writeBuffer.flip();
            tempBuffer.clear();
            int read = userInput.read(tempBuffer);
            if (read > 0) {
                tempBuffer.flip();
                writeBuffer.clear();
                writeBuffer.put(tempBuffer);
                writeBuffer.put((byte) '\n');
            }
        }
    }

}
