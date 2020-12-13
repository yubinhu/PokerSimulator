/**
 * @source https://www.w3schools.com/java/java_files_create.asp
 */

import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors


public class Record {
    private String nl = "\n";
    private String filename;
    private File file;
    private boolean print = true;

    public Record(String name) {
        filename = name;
        file = new File(filename);
    }

    public Record(String name, String[] content) {
        filename = name;
        file = new File(filename);
        this.overwriteLines(content);
    }

    public void setPrint(boolean doPrint) {
        print = doPrint;
    }

    //---------Layer 0-----------
    private void write(String content, boolean append) {
        try {
            FileWriter myWriter = new FileWriter(file, append);
            if (print) {
                System.out.print(content);
            }
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred in writing to file " + filename);
            e.printStackTrace();
        }
    }

    //---------Layer 1-----------
    public void append(Object content) {
        if (content == null) {
            return;
        }
        write(content.toString(), true);
    }

    public void overwrite(String content) {
        write(content, false);
    }

    //---------Layer 2-----------
    public void clear() {
        overwrite("");
    }

    public void appendln() {
        append(nl);
    }

    public void appendln(String content) {
        append(content + nl);
    }

    //---------Layer 3-----------

    /**
     * Add the strings as lines. Assuming starting on a fresh line.
     */
    public void addLines(String[] content) {
        for (int i = 0; i < content.length; i += 1) {
            appendln(content[i]);
        }
    }

    public void overwriteLines(String[] content) {
        String combined = "";
        for (int i = 0; i < content.length; i += 1) {
            combined += content[i];
            combined += nl;
        }
        overwrite(combined);
    }


    /**
     * Useless function so far.
     */
    public void create() {
        try {
            File file = new File("filename.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Record record = new Record("Test.txt",
                new String[]{"sentence1", "sentence2"});
    }
}
