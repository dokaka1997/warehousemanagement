package com.example.warehousemanagement.model.request;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class ExportProductRequest {
    private Date exportDate;

    private Long employee;

    private List<ListProductExportRequest> products;

}
