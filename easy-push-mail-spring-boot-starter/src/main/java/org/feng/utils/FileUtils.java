package org.feng.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Objects;

/**
 * @Author feng
 * @Date 2022/6/23 11:49
 * @Description TODO
 */
public class FileUtils {
    /**
     * MultipartFile 转 File
     *
     * @param multipartFile
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile multipartFile) throws IOException {

        File file = null;
        InputStream ins = null;
        OutputStream os = null;
        try {
            ins = multipartFile.getInputStream();
            file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                os.close();
            }
            if (ins != null) {
                ins.close();
            }
        }
        return file;
    }
}
