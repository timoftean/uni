package common;

import java.io.*;

/**
 * Created by user on 4/1/2017.
 */
public class Message implements Serializable {
    public static final String OK = "OK";
    public static final String ERROR = "ERROR";
    private static String LINE_SEPARATOR = System.getProperty("line.separator");

    private String header;
    private Integer size;
    private String body;

    public Message() {}

    public Message(String header, String body) {
        this.header = header;
        this.size = body.length();
        this.body = body;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "common.Message{" +
                "header='" + header + '\'' +
                ", size=" + size +
                ", body='" + body + '\'' +
                '}';
    }

    public void writeTo(OutputStream os) throws IOException {
        byte[] stringToWrite = (header + LINE_SEPARATOR + size + LINE_SEPARATOR + body).getBytes();
        os.write(stringToWrite, 0, stringToWrite.length);
    }

    public void readFrom(InputStream is) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            header = br.readLine();
            size = Integer.valueOf(br.readLine());
            char[] someString = new char[size];
            br.read(someString, 0, size);
            body = String.valueOf(someString);
        }
    }
}
