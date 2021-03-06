package com.akingyin.base.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Description:
 *
 * 文件类操作
 * @ Author king
 * @ Date 2016/10/14 10:49
 * @ Version V1.0
 */

public class FileUtils {

  public final static String FILE_EXTENSION_SEPARATOR = ".";

  private FileUtils() {
    throw new AssertionError();
  }

  /**
   * read file
   *
   * @param charsetName The name of a supported {@link java.nio.charset.Charset
   * </code>charset<code>}
   * @return if file not exist, return null, else return content of file
   * @throws RuntimeException if an error occurs while operator BufferedReader
   */
  public static StringBuilder readFile(String filePath, String charsetName) {
    File file = new File(filePath);
    StringBuilder fileContent = new StringBuilder("");
    if (file == null || !file.isFile()) {
      return null;
    }

    BufferedReader reader = null;
    try {
      InputStreamReader is = new InputStreamReader(new FileInputStream(file), charsetName);
      reader = new BufferedReader(is);
      String line = null;
      while ((line = reader.readLine()) != null) {
        if (!"".equals(fileContent.toString())) {
          fileContent.append("\r\n");
        }
        fileContent.append(line);
      }
      return fileContent;
    } catch (IOException e) {
      throw new RuntimeException("IOException occurred. ", e);
    } finally {
      IOUtils.close(reader);
    }
  }

  /**
   * write file
   *
   * @param append is append, if true, write to the end of file, else clear content of file and
   * write into it
   * @return return false if content is empty, true otherwise
   * @throws RuntimeException if an error occurs while operator FileWriter
   */
  public static boolean writeFile(String filePath, String content, boolean append) {
    if (StringUtils.isEmpty(content)) {
      return false;
    }

    FileWriter fileWriter = null;
    try {
      makeDirs(filePath);
      fileWriter = new FileWriter(filePath, append);
      fileWriter.write(content);
      return true;
    } catch (IOException e) {
      throw new RuntimeException("IOException occurred. ", e);
    } finally {
      IOUtils.close(fileWriter);
    }
  }

  /**
   * write file
   *
   * @param append is append, if true, write to the end of file, else clear content of file and
   * write into it
   * @return return false if contentList is empty, true otherwise
   * @throws RuntimeException if an error occurs while operator FileWriter
   */
  public static boolean writeFile(String filePath, List<String> contentList, boolean append) {
    if (null == contentList || contentList.size() == 0) {
      return false;
    }

    FileWriter fileWriter = null;
    try {
      makeDirs(filePath);
      fileWriter = new FileWriter(filePath, append);
      int i = 0;
      for (String line : contentList) {
        if (i++ > 0) {
          fileWriter.write("\r\n");
        }
        fileWriter.write(line);
      }
      return true;
    } catch (IOException e) {
      throw new RuntimeException("IOException occurred. ", e);
    } finally {
      IOUtils.close(fileWriter);
    }
  }

  /**
   * write file, the string will be written to the begin of the file
   */
  public static boolean writeFile(String filePath, String content) {
    return writeFile(filePath, content, false);
  }

  /**
   * write file, the string list will be written to the begin of the file
   */
  public static boolean writeFile(String filePath, List<String> contentList) {
    return writeFile(filePath, contentList, false);
  }

  /**
   * write file, the bytes will be written to the begin of the file
   *
   * @see {@link #writeFile(String, InputStream, boolean)}
   */
  public static boolean writeFile(String filePath, InputStream stream) {
    return writeFile(filePath, stream, false);
  }

  /**
   * write file
   *
   * @param filePath the file to be opened for writing.
   * @param stream the input stream
   * @param append if <code>true</code>, then bytes will be written to the end of the file rather
   * than the beginning
   * @return return true
   * @throws RuntimeException if an error occurs while operator FileOutputStream
   */
  public static boolean writeFile(String filePath, InputStream stream, boolean append) {
    return writeFile(filePath != null ? new File(filePath) : null, stream, append);
  }

  /**
   * write file, the bytes will be written to the begin of the file
   *
   * @see {@link #writeFile(File, InputStream, boolean)}
   */
  public static boolean writeFile(File file, InputStream stream) {
    return writeFile(file, stream, false);
  }

  /**
   * write file
   *
   * @param file the file to be opened for writing.
   * @param stream the input stream
   * @param append if <code>true</code>, then bytes will be written to the end of the file rather
   * than the beginning
   * @return return true
   * @throws RuntimeException if an error occurs while operator FileOutputStream
   */
  public static boolean writeFile(File file, InputStream stream, boolean append) {
    OutputStream o = null;
    try {
      makeDirs(file.getAbsolutePath());
      o = new FileOutputStream(file, append);
      byte[] data = new byte[1024];
      int length = -1;
      while ((length = stream.read(data)) != -1) {
        o.write(data, 0, length);
      }
      o.flush();
      return true;
    } catch (FileNotFoundException e) {
      throw new RuntimeException("FileNotFoundException occurred. ", e);
    } catch (IOException e) {
      throw new RuntimeException("IOException occurred. ", e);
    } finally {
      IOUtils.close(o);
      IOUtils.close(stream);
    }
  }

  /**
   * move file
   */
  public static void moveFile(String sourceFilePath, String destFilePath) {
    if (TextUtils.isEmpty(sourceFilePath) || TextUtils.isEmpty(destFilePath)) {
      throw new RuntimeException("Both sourceFilePath and destFilePath cannot be null.");
    }
    moveFile(new File(sourceFilePath), new File(destFilePath));
  }

  /**
   * move file
   */
  public static void moveFile(File srcFile, File destFile) {
    boolean rename = srcFile.renameTo(destFile);
    if (!rename) {
      copyFile(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
      deleteFile(srcFile.getAbsolutePath());
    }
  }

  /**
   * copy file
   *
   * @throws RuntimeException if an error occurs while operator FileOutputStream
   */
  public static boolean copyFile(String sourceFilePath, String destFilePath) {
    InputStream inputStream = null;
    try {
      inputStream = new FileInputStream(sourceFilePath);
    } catch (FileNotFoundException e) {
      throw new RuntimeException("FileNotFoundException occurred. ", e);
    }
    return writeFile(destFilePath, inputStream);
  }

  /**
   * read file to string list, a element of list is a line
   *
   * @param charsetName The name of a supported {@link java.nio.charset.Charset
   * </code>charset<code>}
   * @return if file not exist, return null, else return content of file
   * @throws RuntimeException if an error occurs while operator BufferedReader
   */
  public static List<String> readFileToList(String filePath, String charsetName) {
    File file = new File(filePath);
    List<String> fileContent = new ArrayList<>();
    if (file == null || !file.isFile()) {
      return null;
    }

    BufferedReader reader = null;
    try {
      InputStreamReader is = new InputStreamReader(new FileInputStream(file), charsetName);
      reader = new BufferedReader(is);
      String line = null;
      while ((line = reader.readLine()) != null) {
        fileContent.add(line);
      }
      return fileContent;
    } catch (IOException e) {
      throw new RuntimeException("IOException occurred. ", e);
    } finally {
      IOUtils.close(reader);
    }
  }

  /**
   * get file name from path, not include suffix
   *
   * <pre>
   *      getFileNameWithoutExtension(null)               =   null
   *      getFileNameWithoutExtension("")                 =   ""
   *      getFileNameWithoutExtension("   ")              =   "   "
   *      getFileNameWithoutExtension("abc")              =   "abc"
   *      getFileNameWithoutExtension("a.mp3")            =   "a"
   *      getFileNameWithoutExtension("a.b.rmvb")         =   "a.b"
   *      getFileNameWithoutExtension("c:\\")              =   ""
   *      getFileNameWithoutExtension("c:\\a")             =   "a"
   *      getFileNameWithoutExtension("c:\\a.b")           =   "a"
   *      getFileNameWithoutExtension("c:a.txt\\a")        =   "a"
   *      getFileNameWithoutExtension("/home/admin")      =   "admin"
   *      getFileNameWithoutExtension("/home/admin/a.txt/b.mp3")  =   "b"
   * </pre>
   *
   * @return file name from path, not include suffix
   * @see
   */
  public static String getFileNameWithoutExtension(String filePath) {
    if (StringUtils.isEmpty(filePath)) {
      return filePath;
    }

    int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
    int filePosi = filePath.lastIndexOf(File.separator);
    if (filePosi == -1) {
      return (extenPosi == -1 ? filePath : filePath.substring(0, extenPosi));
    }
    if (extenPosi == -1) {
      return filePath.substring(filePosi + 1);
    }
    return (filePosi < extenPosi ? filePath.substring(filePosi + 1, extenPosi)
        : filePath.substring(filePosi + 1));
  }

  /**
   * get file name from path, include suffix
   *
   * <pre>
   *      getFileName(null)               =   null
   *      getFileName("")                 =   ""
   *      getFileName("   ")              =   "   "
   *      getFileName("a.mp3")            =   "a.mp3"
   *      getFileName("a.b.rmvb")         =   "a.b.rmvb"
   *      getFileName("abc")              =   "abc"
   *      getFileName("c:\\")              =   ""
   *      getFileName("c:\\a")             =   "a"
   *      getFileName("c:\\a.b")           =   "a.b"
   *      getFileName("c:a.txt\\a")        =   "a"
   *      getFileName("/home/admin")      =   "admin"
   *      getFileName("/home/admin/a.txt/b.mp3")  =   "b.mp3"
   * </pre>
   *
   * @return file name from path, include suffix
   */
  public static String getFileName(String filePath) {
    if (StringUtils.isEmpty(filePath)) {
      return filePath;
    }

    int filePosi = filePath.lastIndexOf(File.separator);
    return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
  }

  /**
   * get folder name from path
   *
   * <pre>
   *      getFolderName(null)               =   null
   *      getFolderName("")                 =   ""
   *      getFolderName("   ")              =   ""
   *      getFolderName("a.mp3")            =   ""
   *      getFolderName("a.b.rmvb")         =   ""
   *      getFolderName("abc")              =   ""
   *      getFolderName("c:\\")              =   "c:"
   *      getFolderName("c:\\a")             =   "c:"
   *      getFolderName("c:\\a.b")           =   "c:"
   *      getFolderName("c:a.txt\\a")        =   "c:a.txt"
   *      getFolderName("c:a\\b\\c\\d.txt")    =   "c:a\\b\\c"
   *      getFolderName("/home/admin")      =   "/home"
   *      getFolderName("/home/admin/a.txt/b.mp3")  =   "/home/admin/a.txt"
   * </pre>
   */
  public static String getFolderName(String filePath) {

    if (StringUtils.isEmpty(filePath)) {
      return filePath;
    }

    int filePosi = filePath.lastIndexOf(File.separator);
    return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
  }

  /**
   * get suffix of file from path
   *
   * <pre>
   *      getFileExtension(null)               =   ""
   *      getFileExtension("")                 =   ""
   *      getFileExtension("   ")              =   "   "
   *      getFileExtension("a.mp3")            =   "mp3"
   *      getFileExtension("a.b.rmvb")         =   "rmvb"
   *      getFileExtension("abc")              =   ""
   *      getFileExtension("c:\\")              =   ""
   *      getFileExtension("c:\\a")             =   ""
   *      getFileExtension("c:\\a.b")           =   "b"
   *      getFileExtension("c:a.txt\\a")        =   ""
   *      getFileExtension("/home/admin")      =   ""
   *      getFileExtension("/home/admin/a.txt/b")  =   ""
   *      getFileExtension("/home/admin/a.txt/b.mp3")  =   "mp3"
   * </pre>
   */
  public static String getFileExtension(String filePath) {
    if (StringUtils.isBlank(filePath)) {
      return filePath;
    }

    int extenPosi = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
    int filePosi = filePath.lastIndexOf(File.separator);
    if (extenPosi == -1) {
      return "";
    }
    return (filePosi >= extenPosi) ? "" : filePath.substring(extenPosi + 1);
  }

  /**
   * Creates the directory named by the trailing filename of this file, including the complete
   * directory path required
   * to create this directory. <br/>
   * <br/>
   * <ul>
   * <strong>Attentions:</strong>
   * <li>makeDirs("C:\\Users\\Trinea") can only create users folder</li>
   * <li>makeFolder("C:\\Users\\Trinea\\") can create Trinea folder</li>
   * </ul>
   *
   * @return true if the necessary directories have been created or the target directory already
   * exists, false one of
   * the directories can not be created.
   * <ul>
   * <li>if {@link FileUtils#getFolderName(String)} return null, return false</li>
   * <li>if target directory already exists, return true</li>
   * <li>return {@link File#}</li>
   * </ul>
   */
  public static boolean makeDirs(String filePath) {
    String folderName = getFolderName(filePath);
    if (StringUtils.isEmpty(folderName)) {
      return false;
    }

    File folder = new File(folderName);
    return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
  }

  /**
   * @see #makeDirs(String)
   */
  public static boolean makeFolders(String filePath) {
    return makeDirs(filePath);
  }

  /**
   * Indicates if this file represents a file on the underlying file system.
   */
  public static boolean isFileExist(String filePath) {
    if (StringUtils.isBlank(filePath)) {
      return false;
    }

    File file = new File(filePath);
    return (file.exists() && file.isFile());
  }

  /**
   * Indicates if this file represents a directory on the underlying file system.
   */
  public static boolean isFolderExist(String directoryPath) {
    if (StringUtils.isBlank(directoryPath)) {
      return false;
    }

    File dire = new File(directoryPath);
    return (dire.exists() && dire.isDirectory());
  }

  /**
   * delete file or directory
   * <ul>
   * <li>if path is null or empty, return true</li>
   * <li>if path not exist, return true</li>
   * <li>if path exist, delete recursion. return true</li>
   * <ul>
   */
  public static boolean deleteFile(String path) {
    if (StringUtils.isBlank(path)) {
      return true;
    }

    File file = new File(path);
    if (!file.exists()) {
      return true;
    }
    if (file.isFile()) {
      return file.delete();
    }
    if (!file.isDirectory()) {
      return false;
    }
    for (File f : file.listFiles()) {
      if (f.isFile()) {
        f.delete();
      } else if (f.isDirectory()) {
        deleteFile(f.getAbsolutePath());
      }
    }
    return file.delete();
  }

  /**
   * get file size
   * <ul>
   * <li>if path is null or empty, return -1</li>
   * <li>if path exist and it is a file, return file size, else return -1</li>
   * <ul>
   *
   * @return returns the length of this file in bytes. returns -1 if the file does not exist.
   */
  public static long getFileSize(String path) {
    if (StringUtils.isBlank(path)) {
      return -1;
    }

    File file = new File(path);
    return (file.exists() && file.isFile() ? file.length() : -1);
  }

  /**
   * 删除指定目录中特定的文件
   */
  public static void delete(String dir, FilenameFilter filter) {
    if (TextUtils.isEmpty(dir)) {
      return;
    }
    File file = new File(dir);
    if (!file.exists()) {
      return;
    }
    if (file.isFile()) {
      file.delete();
    }
    if (!file.isDirectory()) {
      return;
    }

    File[] lists = null;
    if (filter != null) {
      lists = file.listFiles(filter);
    } else {
      lists = file.listFiles();
    }

    if (lists == null) {
      return;
    }
    for (File f : lists) {
      if (f.isFile()) {
        f.delete();
      }
    }
  }

  /**
   * 将文件转byte
   */
  public static byte[] readFileToByteArray(File file) {
    ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
    BufferedInputStream in = null;
    try {
      in = new BufferedInputStream(new FileInputStream(file));
      int buf_size = 1024;
      byte[] buffer = new byte[buf_size];
      int len = 0;
      while (-1 != (len = in.read(buffer, 0, buf_size))) {
        bos.write(buffer, 0, len);
      }
      return bos.toByteArray();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        in.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        bos.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  /**
   * 采用NIO将文件转byte
   */
  public static byte[] readFileToByteArrayByNIO(File file) {
    FileChannel channel = null;
    FileInputStream fs = null;
    try {
      fs = new FileInputStream(file);
      channel = fs.getChannel();
      ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
      while ((channel.read(byteBuffer)) > 0) {
        // do nothing
        // System.out.println("reading");
      }
      return byteBuffer.array();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        channel.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        fs.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  /**
   * 处理大文件时优化
   * @param file
   * @return
   */
  public static byte[] readBigFileToByteArray(File file) {
    FileChannel fc = null;
    try {
      fc = new RandomAccessFile(file.getAbsolutePath(), "r").getChannel();
      MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).load();

      byte[] result = new byte[(int) fc.size()];
      if (byteBuffer.remaining() > 0) {
        // System.out.println("remain");
        byteBuffer.get(result, 0, byteBuffer.remaining());
      }
      return result;
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        fc.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return null;
  }

  //复制沙盒私有文件到Download公共目录下
  //orgFilePath是要复制的文件私有目录路径
  //displayName复制后文件要显示的文件名称带后缀（如xx.txt）


  @RequiresApi(api = Build.VERSION_CODES.Q)
  public static void copyPrivateApkToDownload(Context context,String orgFilePath,String displayName){
    ContentValues values = new ContentValues();
    //values.put(MediaStore.Images.Media.DESCRIPTION, "This is a file");
    values.put(MediaStore.Files.FileColumns.DISPLAY_NAME, displayName);
    values.put(MediaStore.Files.FileColumns.MIME_TYPE, "application/vnd.android.package-archive");//MediaStore对应类型名
    values.put(MediaStore.Files.FileColumns.TITLE, displayName);
    values.put(MediaStore.Images.Media.RELATIVE_PATH, "Download/Apks");//公共目录下目录名

    Uri external = MediaStore.Downloads.EXTERNAL_CONTENT_URI;//内部存储的Download路径
    ContentResolver resolver = context.getContentResolver();

    Uri insertUri = resolver.insert(external, values);//使用ContentResolver创建需要操作的文件
    //Log.i("Test--","insertUri: " + insertUri);

    InputStream ist=null;
    OutputStream ost = null;
    try {
      ist=new FileInputStream(new File(orgFilePath));
      if (insertUri != null) {
        ost = resolver.openOutputStream(insertUri);
      }
      if (ost != null) {
        byte[] buffer = new byte[4096];
        int byteCount = 0;
        while ((byteCount = ist.read(buffer)) != -1) {  // 循环从输入流读取 buffer字节
          ost.write(buffer, 0, byteCount);        // 将读取的输入流写入到输出流
        }
        // write what you want
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (ist != null) {
          ist.close();
        }
        if (ost != null) {
          ost.close();
        }
      } catch (IOException e) {
        //Log.i("copyPrivateToDownload--","fail in close: " + e.getCause());
      }
    }
  }

}
