package com.cimb.vipflag.controller;


import com.cimb.vipflag.entity.FileLinkDirectory;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/cifapprove")
@CrossOrigin
public class CifDataController {


    private String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\converted_file\\";

    @PostMapping("/file")
    public void generateTextFile (@RequestBody FileLinkDirectory approvedData) throws IOException {
//        FileInputStream fis = new FileInputStream(approvedData.getLinkDirectory());
        System.out.println("ini adlah file id");
        System.out.println(approvedData.getFileId());
        LocalDateTime localDateTime = LocalDateTime.now();

        String localDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String localTime = localDateTime.format(DateTimeFormatter.ofPattern("hhMMss"));

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

        int lastCol = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

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
                        System.out.print((int) cell.getNumericCellValue() + "\t");
                        if(cell.getColumnIndex()==0){
                            sb.append("01"+"|"+(int)cell.getNumericCellValue());
                        }else if(cell.getColumnIndex()+1== lastCol){
                            sb.append((int)cell.getNumericCellValue());

                        }else{
                            sb.append("|"+(int)cell.getNumericCellValue() + "|");
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
        sb.append(99+"|"+ 3+"|"+0+"+"+"\n");

        Path path = Paths.get(filePath +"INCIFVIP_"+localDate+"_"+approvedData.getFileId()+".txt");
        Files.write(path, Arrays.asList(sb.toString()));



    }


}


