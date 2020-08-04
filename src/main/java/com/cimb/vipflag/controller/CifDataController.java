package com.cimb.vipflag.controller;


import com.cimb.vipflag.dao.CifDataUploadedRepo;
import com.cimb.vipflag.dao.FileDirectoryRepo;
import com.cimb.vipflag.dao.UserRepo;
import com.cimb.vipflag.entity.CifDataUploaded;
import com.cimb.vipflag.entity.FileDirectory;
import com.cimb.vipflag.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/cifapprove")
@CrossOrigin
public class CifDataController {


    private String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\converted_file\\";

    @Autowired
    private FileDirectoryRepo fileDirectoryRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CifDataUploadedRepo cifDataUploadedRepo;

    @GetMapping("/all_approved")
    public Iterable<CifDataUploaded> getAllData(){
        return cifDataUploadedRepo.findAll();
    }

    private static final int BUFFER_SIZE = 4096;
        @PostMapping("/getftp")
    public void getFileFtp() throws IOException {
        String ftpUrl = "ftp://%s:%s@%s/%s";
        String host = "10.25.131.38";
        String user = "FTPAS400";
        String pass = "Bintaro.1!";
        String dirPath = "sit1/eTP/SIBS/";


        ftpUrl = String.format(ftpUrl, user, pass, host, dirPath);
        System.out.println("URL: " + ftpUrl);

        try {
            URL url = new URL(ftpUrl);
            URLConnection conn = url.openConnection();
            InputStream inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));


            String line = null;
            String txtLine = null;
            System.out.println("--- START ---");
            while ((line = reader.readLine()) != null) {
                if(line.contains("INCIFVIP_20200728_1529.txt")){
                    System.out.println(ftpUrl+"INCIFVIP_20200728_1529.txt");
                    URL txtUrl = new URL(ftpUrl+"INCIFVIP_20200728_1529.txt");
                    URLConnection txtCon = txtUrl.openConnection();
                    InputStream txtInputStream = txtCon.getInputStream();
                    BufferedReader txtReader = new BufferedReader(new InputStreamReader(txtInputStream));
                    while((txtLine = txtReader.readLine()) != null){
                        System.out.println(txtLine);
                    }
                }
            }

            System.out.println("--- END ---");

            inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @PostMapping("/file/{checkerId}")
    @Transactional
    public FileDirectory generateTextFile (@RequestBody FileDirectory approvedData, @PathVariable int checkerId) throws IOException {

        FileDirectory findFile = fileDirectoryRepo.findById(approvedData.getFileId()).get();
        User findChecker = userRepo.findById(checkerId).get();

        LocalDateTime localDateTime = LocalDateTime.now();

        String localDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String localTime = localDateTime.format(DateTimeFormatter.ofPattern("hhMMss"));

        findFile.setApprovedBy(findChecker.getUsername());
        findFile.setApprovalStatus("Approved");
        findFile.setUserChecker(findChecker);
        findFile.setApprovalDate(localDateTime);
        findFile.setChecksumStatus(approvedData.getChecksumStatus());

        //        FileInputStream fis = new FileInputStream(approvedData.getLinkDirectory());
        System.out.println("ini adlah file id");
        System.out.println(approvedData.getFileId());


        StringBuilder sb = new StringBuilder();

        InputStream fis = new URL(approvedData.getLinkDirectory()).openStream();
        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);

        // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);

        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = mySheet.iterator();
        sb.append("00"+"|"+ localDate+"|"+localDate+"|"+localTime+"|"+"INCIFVIP");
        // Traversing over each row of XLSX file
        int lastRow = 0;
        int lastCol = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            lastRow += 1;
//            System.out.println("ini last col");
            lastCol = row.getLastCellNum();
//            System.out.println(lastCol);
            // For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();
                if(cell.getRowIndex()==0){
                    continue;
                }

                switch (cell.getCellType()) {
                    case STRING:
                        System.out.print(cell.getStringCellValue() + "\t");
                        if(cell.getColumnIndex()==0){
                            sb.append("01"+"|"+cell.getStringCellValue());
                        }else if(cell.getColumnIndex()+1== lastCol){
                            sb.append(cell.getStringCellValue());

                        }else{
                            sb.append("|"+cell.getStringCellValue() + "|");
                        }
                        break;
                    case NUMERIC:
                        System.out.print((long) cell.getNumericCellValue() + "\t");
                        if(cell.getColumnIndex()==0){
                            sb.append("01"+"|"+(long)cell.getNumericCellValue());
                        }else if(cell.getColumnIndex()+1== lastCol){
                            sb.append((long)cell.getNumericCellValue());

                        }else{
                            sb.append("|"+(long)cell.getNumericCellValue() + "|");
                        }
                        break;
//                    case BOOLEAN:
//                        System.out.print(cell.getBooleanCellValue() + "\t");
//
//                        if(cell.getColumnIndex()+1== lastCol){
//                            sb.append("01"+"|"+cell.getBooleanCellValue());
//                        }else{
//                            sb.append("01"+"|"+cell.getBooleanCellValue()+ "|");
//                        }
//                        break;
                    default :

                }
            }
            System.out.println("");
            sb.append("\n");
        }
        sb.append(99+"|"+ (lastRow-1)+"|"+0+"+"+"\n");
        String fileNameTxt = "INCIFVIP_"+localDate+"_"+approvedData.getFileId()+".txt";
        Path path = Paths.get(filePath +fileNameTxt);
        Files.write(path, Arrays.asList(sb.toString()));
        sendFtpTxt(path.toString(),fileNameTxt);
        return approvedData;


    }

    public void sendFtpTxt(String txtDirectory, String fileNameTxt) throws IOException {

//        ftp://user:password@host:port/path
        String ftpUrl = "ftp://%s:%s@%s/%s;type=i";
        String host = "10.25.131.38";
        String user = "FTPAS400";
        String pass = "Bintaro.1!";
        String filePath = txtDirectory;
        String uploadPath = "sit1/eTP/SIBS/"+fileNameTxt;

        ftpUrl = String.format(ftpUrl, user, pass, host, uploadPath);
        System.out.println("Upload URL: " + ftpUrl);


        try {
            URL url = new URL(ftpUrl);
            URLConnection conn = url.openConnection();
            OutputStream outputStream = conn.getOutputStream();
            FileInputStream inputStream = new FileInputStream(filePath);
//            InputStream inputStream = new URL(txtDirectory).openStream(); // jika dari URL pake ini

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

            System.out.println("File uploaded");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @Autowired
    private EntityManager entityManager;

    @PostMapping("/cif_storetable/{dateString:.+}")
    public void createdData (@PathVariable String dateString, @RequestBody List<CifDataUploaded> listData){
        LocalDateTime localDateTime =  LocalDateTime.now();

//    cifDataUploadedRepo.saveAll(listData);
        listData.forEach(data->{
            CifDataUploaded findCFCIFN = cifDataUploadedRepo.findCFCIFN(data.getCFCIFN());



            if(findCFCIFN==null){
                System.out.println("masuk if");
                data.setApprovalDate(localDateTime);

                data.setCreatedDate(LocalDateTime.parse(dateString));
                data.setApprovalStatus("Approved");
                cifDataUploadedRepo.save(data);
            }
            else{
                System.out.println("masuk else");
                data.setId(findCFCIFN.getId());
                data.setApprovalDate(localDateTime);


                data.setCreatedDate(LocalDateTime.parse(dateString));
                data.setApprovalStatus("Approved");

                cifDataUploadedRepo.save(data);
            }
        });

    }


}


