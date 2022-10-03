package com.dcsplab.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DLFileZipper {
  private static final Logger logger = LoggerFactory.getLogger(DLFileZipper.class);
  
  // private String outputZipPath;
  
  private final List<File> filesToZip;
  
  public DLFileZipper() {
    filesToZip = new ArrayList<>();
  }

  /*
  public void setOutputZipPath(String outputZipPath) {
    this.outputZipPath = outputZipPath;
  }*/
  
  public void addFileToZip(File file) {
    if (!file.isFile()) {
      return;
    }
    filesToZip.add(file);
  }
  
  public void createZipFile(String outputZipPath, List<File> files) {
    if (files == null || files.size() < 1) {
      logger.info("No file to be zipped.");
      return;
    }
    
    filesToZip.addAll(files);
    
    // this.outputZipPath = outputZipPath;
    
    try {
      OutputStream is = new FileOutputStream(outputZipPath);
      CheckedOutputStream cos = new CheckedOutputStream(is, new CRC32());
      ZipOutputStream zos = new ZipOutputStream(cos);
      for (File file : filesToZip) {
        addToZip(zos, file);
      }
      zos.close();
      cos.close();
      is.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private void addToZip(ZipOutputStream zos, File file) {
    logger.info("add file '{}' to zip...", file.getName());
    try {
      zos.putNextEntry(new ZipEntry(file.getName()));
      InputStream is = new FileInputStream(file.getPath());
      BufferedInputStream bis = new BufferedInputStream(is);
      
      byte[] b = new byte[1024];
      while (bis.read(b) != -1) {
        zos.write(b);
      }
      
      bis.close();
      is.close();
    } catch (IOException e) {
      logger.error("zip file '{}' failed...", file.getName(), e);
    }
  }
}
