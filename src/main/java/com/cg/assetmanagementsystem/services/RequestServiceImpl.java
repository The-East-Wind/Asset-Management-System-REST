package com.cg.assetmanagementsystem.services;

import com.cg.assetmanagementsystem.daos.RequestDAO;
import com.cg.assetmanagementsystem.entities.Request;
import com.cg.assetmanagementsystem.exceptions.RequestNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean generateRequestReport() {
        return false;
    }

}
