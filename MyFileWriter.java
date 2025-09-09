import java.io.*;

public class MyFileWriter {
    private static void printFileSize(String fileName) {
        File file = new File("./" + fileName);
        long length = file.length();
        System.out.println(String.format("%s has length %d", fileName, length));
    }

    public static void generateHiddenFile(String fileName, String data){
        String filePath = "." + fileName;
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath))) {
            bufferedOutputStream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateHiddenFile(String folderName, String fileName, String data){
        String filePath = "." + folderName + "/." + fileName;
        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath))) {
            bufferedOutputStream.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        generateHiddenFile("file.txt", "great assignment! love this!! so much fun!!!");
        generateHiddenFile("Folder", "file.txt", "this is very different");
        printFileSize(".file.txt");
    }
}