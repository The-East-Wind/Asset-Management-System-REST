package com.cg.assetmanagementsystem.services;

import com.cg.assetmanagementsystem.entities.Request;
import com.cg.assetmanagementsystem.exceptions.ReportGenerationException;
import com.cg.assetmanagementsystem.exceptions.RequestNotFoundException;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface RequestService {
    Request addNewRequest(Request newRequest);

    Request getRequestWithId(int requestId) throws RequestNotFoundException;

    List<Request> getPendingRequests();

    Request updateRequest(Integer requestId, Request updatedRequest) throws RequestNotFoundException;

    ByteArrayInputStream generateRequestReport() throws ReportGenerationException;
}
