package com.cimb.vipflag.controller;


import com.cimb.vipflag.dao.FileDirectoryRepo;
import com.cimb.vipflag.dao.UserRepo;
import com.cimb.vipflag.entity.FileDirectory;
import com.cimb.vipflag.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.ZoneId;
import java.util.Date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@RestController
@RequestMapping("/files")
@CrossOrigin
public class FileDirectoryController {

    private String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\cif_data\\";

    @Autowired
    private FileDirectoryRepo fileDirectoryRepo;

    @Autowired
    private UserRepo userRepo;


    @GetMapping("/all")
    public Iterable<FileDirectory> getAllFileUploaded (){
        return fileDirectoryRepo.findAll();
    }


    @GetMapping("/checker/{checkerId}")
    public Iterable<FileDirectory> getFileByChecker (@PathVariable int checkerId){
        return fileDirectoryRepo.findFileByCheckerId(checkerId);
    }

    @GetMapping("/maker/{makerId}")
    public Iterable<FileDirectory> getFileByMaker (@PathVariable int makerId){
        return fileDirectoryRepo.findFileByMakerId(makerId);
    }

    @GetMapping("/maker/{makerId}/{status}")
    public Iterable<FileDirectory> getFileByMakerStatus (@PathVariable int makerId, @PathVariable String status){
        return fileDirectoryRepo.findFileByMakerStatus(makerId,status);
    }

    @GetMapping("/need_approve/{checkerId}")
    public Iterable<FileDirectory> getFileToApproveByChecker (@PathVariable int checkerId){
        return fileDirectoryRepo.findFileChecker(checkerId);
    }

    @GetMapping("/null_status/{checkerId}")
    public Iterable<FileDirectory> getFileCheckerByStatus (@PathVariable int checkerId){
        return fileDirectoryRepo.findFileCheckerNull(checkerId);
    }

    @GetMapping("/not_null_status/{checkerId}")
    public Iterable<FileDirectory> findFileCheckerNotNull (@PathVariable int checkerId){

        return fileDirectoryRepo.findFileCheckerNotNull(checkerId);
    }

    @GetMapping("/checker/{checkerId}/{status}")
    public Iterable<FileDirectory> getFileByCheckerStatus (@PathVariable int checkerId, @PathVariable String status){
        return fileDirectoryRepo.findFileByCheckerStatus(checkerId,status);
    }

    @GetMapping("/checker/{checkerId}/{status1}/{status2}")
    public Iterable<FileDirectory> getFileByCheckerStatus2 (@PathVariable int checkerId, @PathVariable String status1, @PathVariable String status2){
        return fileDirectoryRepo.findFileByCheckerStatus2(checkerId,status1,status2);
    }



    @PostMapping("/reject/{fileId}")
    public FileDirectory setRejectFile(@PathVariable int fileId){

        LocalDateTime localDateTime = LocalDateTime.now();

        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();
        java.sql.Date date = java.sql.Date.valueOf(localDate);



        FileDirectory findFile = fileDirectoryRepo.findById(fileId).get();
        findFile.setApprovalStatus("Rejected");
        findFile.setApprovalDate(null);
        return fileDirectoryRepo.save(findFile);
    }

    @PostMapping("/uploadExcelFile/{rowCount}/{makerId}")
    public FileDirectory uploadFile(MultipartFile file,@PathVariable int rowCount, @PathVariable int makerId) throws IOException {
        User findMaker = userRepo.findById(makerId).get();

        Optional<FileDirectory> findLastFile = fileDirectoryRepo.findLastFile();

        System.out.println(findLastFile);
        int sequenceNumber = 0;
        if(findLastFile.toString()=="Optional.empty") {
            System.out.println("kosong");
            sequenceNumber = 1529;
        }else{
            sequenceNumber = findLastFile.get().getFileId()+1;
        }
        System.out.println(sequenceNumber);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        System.out.println(LocalDate.now().format(formatter));
        String newFileName = "INCIFVIP_"+LocalDate.now().format(formatter)+"_"+(sequenceNumber)+".xlsx";

        InputStream in = file.getInputStream();


        String fileLocation = filePath.substring(0, filePath.length()) +newFileName;


        FileOutputStream f = new FileOutputStream(fileLocation);

        int ch = 0;
        while ((ch = in.read()) != -1) {
            f.write(ch);

        }
        f.flush();
        f.close();


        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/download/")
                .path(newFileName).toUriString();



        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();
        LocalTime localTime = localDateTime.toLocalTime();
        java.sql.Date date = java.sql.Date.valueOf(localDate);

        Date mydate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        FileDirectory newData = new FileDirectory();
        newData.setFileId(sequenceNumber);
        newData.setFileName(file.getOriginalFilename());
        newData.setCreatedBy(findMaker.getUsername());
        newData.setCreatedDate(localDateTime);
        newData.setLinkDirectory(fileDownloadUri);
        newData.setUserMaker(findMaker);
        newData.setRowCount(rowCount);

        return fileDirectoryRepo.save(newData);
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
