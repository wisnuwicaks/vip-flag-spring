package com.cimb.vipflag.controller;


import com.cimb.vipflag.dao.FileLinkDirectoryRepo;
import com.cimb.vipflag.entity.FileLinkDirectory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@RestController
@RequestMapping("/files")
@CrossOrigin
public class FileLinkDirectoryController {

    private String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\cif_data\\";

    @Autowired
    private FileLinkDirectoryRepo fileLinkDirectoryRepo;

    @PostMapping("/upload")
    public FileLinkDirectory uploadFile (@RequestParam MultipartFile file){
        Date date = new Date();

        FileLinkDirectory newData = new FileLinkDirectory();
        if(!file.equals(null)){
            String fileExtension = file.getContentType().split("/")[1];
            String newFileName = "DATA-" + date.getTime() + "." + fileExtension;

            // Get file's original name || can generate our own
            String fileName = StringUtils.cleanPath(newFileName);

            // Create path to upload destination + new file name
            Path path = Paths.get(StringUtils.cleanPath(filePath) + fileName);

            try {
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/file_download/")
                    .path(fileName).toUriString();

            newData.setDateCreated(date);
            newData.setLinkDirectory(fileDownloadUri);
        }

        return fileLinkDirectoryRepo.save(newData);
    }

    @GetMapping("/file_download/{fileName:.+}")
    public ResponseEntity<Object> downloadFile(@PathVariable String fileName){
        Path path = Paths.get(filePath + fileName);
        Resource resource = null;

        try {
            resource = new UrlResource(path.toUri());
        } catch(MalformedURLException e) {
            e.printStackTrace();
        }



        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
