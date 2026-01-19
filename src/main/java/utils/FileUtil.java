package utils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileUtil {

    public static File getValidFile(
            String folderPath,
            List<String> allowedExtensions,
            long maxSizeMB,
            String fileTypeName) {

        File folder = new File(folderPath);

        if (!folder.isAbsolute()) {
            folder = new File(System.getProperty("user.dir")
                    + File.separator + folderPath);
        }

        if (!folder.exists())
            throw new RuntimeException("❌ Folder missing: " + folder.getAbsolutePath());

        File[] files = folder.listFiles((dir, name) -> {
            String lower = name.toLowerCase();
            return allowedExtensions.stream().anyMatch(lower::endsWith);
        });

        if (files == null || files.length == 0)
            throw new RuntimeException("❌ No valid " + fileTypeName + " found in folder");

        for (File f : files) {
            long sizeMB = f.length() / (1024 * 1024);

            if (sizeMB <= maxSizeMB) {
                LogUtil.info("📁 Valid " + fileTypeName + " found: "
                        + f.getName() + " (" + sizeMB + " MB)");
                return f;
            }
        }

        throw new RuntimeException(
                "❌ No valid " + fileTypeName + " ≤ " + maxSizeMB + "MB found");
    }

    public static List<String> getExtensions(String extensions) {
        return Arrays.asList(extensions.split(","));
    }
    
    public static File waitForDownloadedFile(
            String folderPath,
            String extension,
            int timeoutSeconds) {

        File dir = new File(folderPath);
        long endTime = System.currentTimeMillis() + timeoutSeconds * 1000;

        while (System.currentTimeMillis() < endTime) {

            File[] files = dir.listFiles(file ->
                    file.getName().endsWith(extension)
                            && !file.getName().endsWith(".crdownload")
            );

            if (files != null && files.length > 0) {
                return files[0];
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
        }

        return null;
    }

    
    public static void cleanDownloadFolder(String folderPath) {
        File dir = new File(folderPath);
        if (dir.exists()) {
            for (File f : dir.listFiles()) {
                f.delete();
            }
        }
    }
    
    
}
