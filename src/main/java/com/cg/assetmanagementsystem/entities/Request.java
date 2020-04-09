package com.cg.assetmanagementsystem.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="request")
public class Request implements Serializable {
    @Id
    @Column(name="request_id")
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "request_id_gen")
    private Integer requestId;
    @Column(name="from_date")
    private LocalDate fromDate;
    @Column(name="to_date")
    private LocalDate toDate;
    @OneToOne
    @JoinColumn(name = "requested_by",nullable = false)
    private Employee requestedBy;
    @OneToOne
    @JoinColumn(name = "requested_for",nullable = false)
    private Employee requestedFor;
    @Column(name="status")
    private String status;
    @OneToOne
    @JoinColumn(name = "requested_asset",nullable = false)
    private Asset requestedAsset;
    public Request(){
    }
    public Request(Asset requestedAsset, LocalDate fromDate, LocalDate toDate, Employee requestedBy, Employee requestedFor) {
        this.requestedAsset = requestedAsset;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.requestedBy = requestedBy;
        this.requestedFor = requestedFor;
    }

    public Request(Integer requestId, LocalDate fromDate, LocalDate toDate, Employee requestedBy, Employee requestedFor, String status, Asset requestedAsset) {
        this.requestId = requestId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.requestedBy = requestedBy;
        this.requestedFor = requestedFor;
        this.status = status;
        this.requestedAsset = requestedAsset;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Employee getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(Employee requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Employee getRequestedFor() {
        return requestedFor;
    }

    public void setRequestedFor(Employee requestedFor) {
        this.requestedFor = requestedFor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Asset getRequestedAsset() {
        return requestedAsset;
    }

    public void setRequestedAsset(Asset requestedAsset) {
        this.requestedAsset = requestedAsset;
    }
}
