import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MyFileWriter {
    public static String hashFile(String filePath) {
        try {
            String content = stringify(filePath);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(content.getBytes(StandardCharsets.UTF_8)); //these two lines googled from library
            StringBuilder hex = new StringBuilder();
            for (byte b : hashBytes) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();

        } catch (Exception e) {
        }
        return null;
    }

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
        System.out.println(hashFile(".file.txt"));
    }
}