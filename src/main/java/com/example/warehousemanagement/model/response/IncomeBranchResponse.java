package com.example.warehousemanagement.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class IncomeBranchResponse implements Comparable<IncomeBranchResponse> {

    private Date date;
    private Double income;

    @Override
    public int compareTo(IncomeBranchResponse o) {
        return this.date.compareTo(o.getDate());
    }
}
