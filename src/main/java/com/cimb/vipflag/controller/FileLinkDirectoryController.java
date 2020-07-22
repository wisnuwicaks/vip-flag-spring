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
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
//            String fileExtension = file.getContentType().split("/")[1];
            String fileExtension = file.getOriginalFilename();
            System.out.println("ini ekstensi :"+fileExtension);
            String newFileName = "DATA-" + date.getTime() + "." + "xlsx";

            // Get file's original name || can generate our own
            String fileName = StringUtils.cleanPath(newFileName);

            // Create path to upload destination + new file name
            Path path = Paths.get(StringUtils.cleanPath(filePath) + fileName);

            try {

                System.out.println("try");
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println("masukk");
                e.printStackTrace();
            }

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/file_download/")
                    .path(fileName).toUriString();

            newData.setDateCreated(date);
            newData.setLinkDirectory(fileDownloadUri);
        }

        return fileLinkDirectoryRepo.save(newData);
    }


    @PostMapping("/uploadExcelFile")
    public String uploadFile(Model model, MultipartFile file) throws IOException {
        InputStream in = file.getInputStream();
//        File currDir = new File(".");
//        String path = currDir.getAbsolutePath();
        String fileLocation = filePath.substring(0, filePath.length()) +file.getOriginalFilename();
        FileOutputStream f = new FileOutputStream(fileLocation);
        System.out.println(f.toString());
        int ch = 0;
        while ((ch = in.read()) != -1) {

            f.write(ch);

        }
        f.flush();
        f.close();
        model.addAttribute("message", "File: " + file.getOriginalFilename()
                + " has been uploaded successfully!");
        return fileLocation;
    }
//
//
//    @Resource(name = "excelPOIHelper")
//    private ExcelPOIHelper excelPOIHelper;
//
//    @RequestMapping(method = RequestMethod.GET, value = "/readPOI")
//    public String readPOI(Model model) throws IOException {
//
//        if (fileLocation != null) {
//            if (fileLocation.endsWith(".xlsx") || fileLocation.endsWith(".xls")) {
//                Map<Integer, List<MyCell>> data
//                        = excelPOIHelper.readExcel(fileLocation);
//                model.addAttribute("data", data);
//            } else {
//                model.addAttribute("message", "Not a valid excel file!");
//            }
//        } else {
//            model.addAttribute("message", "File missing! Please upload an excel file.");
//        }
//        return "excel";
//    }

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
