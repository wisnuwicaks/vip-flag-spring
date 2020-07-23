package com.cimb.vipflag.controller;


import com.cimb.vipflag.dao.FileLinkDirectoryRepo;
import com.cimb.vipflag.dao.UserRepo;
import com.cimb.vipflag.entity.FileLinkDirectory;
import com.cimb.vipflag.entity.User;
import com.cimb.vipflag.entity.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@RestController
@RequestMapping("/files")
@CrossOrigin
public class FileLinkDirectoryController {

    private String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\cif_data\\";

    @Autowired
    private FileLinkDirectoryRepo fileLinkDirectoryRepo;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/uploadExcelFile/{makerId}/{checkerId}")
    public FileLinkDirectory uploadFile(Model model, MultipartFile file,@PathVariable int makerId,@PathVariable int checkerId) throws IOException {
        User findMaker = userRepo.findById(makerId).get();
        User findChecker = userRepo.findById(checkerId).get();


        InputStream in = file.getInputStream();
        LocalDateTime localDateTime = LocalDateTime.now();
        Optional<FileLinkDirectory> findLastFile = fileLinkDirectoryRepo.findLastFile();

        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();


        System.out.println(findLastFile);
        int sequenceNumber = 0;
        if(findLastFile.toString()=="Optional.empty") {
            System.out.println("kosong");
            sequenceNumber = 1;
        }else{
            sequenceNumber = findLastFile.get().getFileId()+1;
        }
        System.out.println(sequenceNumber);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        System.out.println(LocalDate.now().format(formatter));
        String newFileName = "INCIFVIP_"+LocalDate.now().format(formatter)+"_"+Integer.toString(sequenceNumber)+".xlsx";
        String fileLocation = filePath.substring(0, filePath.length()) +newFileName;
        FileOutputStream f = new FileOutputStream(fileLocation);

        int ch = 0;
        while ((ch = in.read()) != -1) {
            f.write(ch);
        }
        f.flush();
        f.close();
        model.addAttribute("message", "File: " + newFileName
                + " has been uploaded successfully!");


        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/download/")
                .path(newFileName).toUriString();

        Date date = Date.valueOf(localDate);
        FileLinkDirectory newData = new FileLinkDirectory();
        newData.setCreatedDate(date);
        newData.setLinkDirectory(fileDownloadUri);
        newData.setUserMaker(findMaker);
        newData.setUserChecker(findChecker);
        return fileLinkDirectoryRepo.save(newData);
    }

    @GetMapping("/download/{fileName:.+}")
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
