import java.io.*;

public class MyFileWriter {
    public static String stringify(String filePath) {
        File file = new File(filePath);
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (Exception e){
            // throw new IOException();
        }

        return sb.toString();
    }

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
        System.out.println(stringify(".file.txt"));
    }
}