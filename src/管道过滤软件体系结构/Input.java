package 管道过滤软件体系结构;
import java.io.*;
import java.util.*;
public class Input extends Filter {
    private File file;
    public Input(File file,Pipe output) {
        super(null, output);
        this.file = file;
    }

    @Override
    protected void transform() throws IOException {
        BufferedReader inputFile = null;
        try {
            inputFile = new BufferedReader(new FileReader(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String line;
        try {
            while ((line = inputFile.readLine()) != null) {
                output.writerLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        output.closeWriter();
    }
}
