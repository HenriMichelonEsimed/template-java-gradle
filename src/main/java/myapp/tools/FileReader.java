package myapp.tools;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FileReader {
    private java.io.InputStreamReader f;
    private int last;

    public FileReader(String fileName) {
        try {
            f = new java.io.FileReader(fileName);
            last = f.read();
        } catch (FileNotFoundException e) {
            System.err.format("Error opening file %s : %s", fileName, e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.format("Error reading file %s : %s", fileName, e.getMessage());
            System.exit(1);
        }
    }

    public FileReader(String fileName, Charset charset) {
        try {
            f = new java.io.FileReader(fileName, charset);
            last = f.read();
        } catch (FileNotFoundException e) {
            System.err.format("Error opening file %s : %s", fileName, e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.format("Error reading file %s : %s", fileName, e.getMessage());
            System.exit(1);
        }
    }

    public char readChar() {
        if (last == -1) {
            System.err.println("Reading past end of file");
            System.exit(1);
        }
        int r = last;
        try {
            last = f.read();
        } catch (IOException e) {
            System.err.format("Error reading character : %s", e.getMessage());
            System.exit(1);
        }
        return (char) r;
    }

    public boolean isEndOfFile() {
        return last == -1;
    }

    public FileReader(InputStreamReader is) {
        try {
            f = is;
            last = f.read();
        } catch (IOException e) {
            System.err.format("Error reading stream %s : %s", e.getMessage());
            System.exit(1);
        }
    }

    public static FileReader fromString(String content) {
        InputStreamReader isr = new InputStreamReader(new ByteArrayInputStream( content.getBytes(StandardCharsets.UTF_8)) );;
        return new FileReader(isr);
    }

}
