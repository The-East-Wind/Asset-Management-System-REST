package com.cg.assetmanagementsystem.services;

import com.cg.assetmanagementsystem.daos.RequestDAO;
import com.cg.assetmanagementsystem.entities.Request;
import com.cg.assetmanagementsystem.exceptions.ReportGenerationException;
import com.cg.assetmanagementsystem.exceptions.RequestNotFoundException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestDAO requestDAO;
    public RequestServiceImpl(){
    }
    @Override
    public Request addNewRequest(Request newRequest) {
        return requestDAO.save(newRequest);
    }

    @Override
    public Request getRequestWithId(int requestId) throws RequestNotFoundException {
        Optional<Request> requestWithId = requestDAO.findById(requestId);
        if(!requestWithId.isPresent()){
            throw new RequestNotFoundException("Error! No request was found with request Id="+requestId+".");
        }
        return requestWithId.get();
    }

    @Override
    public List<Request> getPendingRequests() {
        return ((List<Request>)requestDAO.findAll()).stream().filter(request->request.getStatus().equals("Pending")).collect(Collectors.toList());
    }

    @Override
    public Request updateRequest(Integer requestId, Request updatedRequest) throws RequestNotFoundException {
        Request requestWithId = getRequestWithId(requestId);
        updatedRequest.setRequestId(requestWithId.getRequestId());
        return requestDAO.save(updatedRequest);
    }

    @Override
    public ByteArrayInputStream generateRequestReport() throws ReportGenerationException {
        Iterable<Request> requests = requestDAO.findAll();
        String[] headers = new String[]{"Id","Requested From","Requested Till","Requested By","Requested For","Requested Asset","Status"};
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Request Details");
        Row row = sheet.createRow(0);
        int cellnum = 0;
        for(String header:headers){
            Cell cell = row.createCell(cellnum++);
            cell.setCellValue(header);
        }
        List<String[]> requestData = new ArrayList<>();
        requests.forEach(request -> requestData.add(new String[]{
                request.getRequestId().toString(),
                request.getFromDate().toString(),
                request.getToDate().toString(),
                request.getRequestedBy().getEmployeeId().toString(),
                request.getRequestedFor().getEmployeeId().toString(),
                request.getRequestedAsset().getAssetId().toString(),
                request.getStatus()
        }));
        int rownum = 0;
        for(String[] request:requestData){
            cellnum = 0;
            row = sheet.createRow(rownum++);
            for(String cellData:request){
                Cell cell = row.createCell(cellnum++);
                cell.setCellValue(cellData);
            }
        }
        ByteArrayOutputStream bos;
        byte[] byteArrayData;
        try{
            bos = new ByteArrayOutputStream();
            workbook.write(bos);
            byteArrayData = bos.toByteArray();
            bos.close();
        }
        catch (IOException exception){
            throw new ReportGenerationException("Error! Report generation failed",exception);
        }
        return new ByteArrayInputStream(byteArrayData);
    }

}
