package com.enigmacamp.pascal.sepotifay.utils.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUtil {
  public String store(MultipartFile file, String destination) throws IOException;
  public Resource read(String filename);
}
