package com.enigmacamp.pascal.sepotifay.utils.file.impl;

import com.enigmacamp.pascal.sepotifay.utils.file.FileUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUtilDiskImpl implements FileUtil {
  private final Path storageLocation = Paths.get("uploads").toAbsolutePath().normalize();

  @Override
  public String store(MultipartFile uploadedFile, String destination) throws IOException {
    Path target = storageLocation.resolve(destination);
    Files.copy(uploadedFile.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

    return FilenameUtils.getName(destination);
  }

  @Override
  public Resource read(String filename) {
    String exceptionMessage = String.format("File %s not found.", filename);
    try {
      Path file = storageLocation.resolve(filename).normalize();
      Resource resource = new UrlResource(file.toUri());

      if (!resource.exists()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, exceptionMessage);

      return resource;
    } catch (MalformedURLException mue) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, exceptionMessage);
    }
  }
}
