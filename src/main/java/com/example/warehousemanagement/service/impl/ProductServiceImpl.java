package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.entity.Export;
import com.example.warehousemanagement.entity.ExportDetail;
import com.example.warehousemanagement.entity.Product;
import com.example.warehousemanagement.entity.Warehouse;
import com.example.warehousemanagement.model.request.ExportProductRequest;
import com.example.warehousemanagement.model.request.ListProductExportRequest;
import com.example.warehousemanagement.model.response.GetAllProductResponse;
import com.example.warehousemanagement.repository.ExportDetailRepository;
import com.example.warehousemanagement.repository.ExportRepository;
import com.example.warehousemanagement.repository.ProductRepository;
import com.example.warehousemanagement.repository.WarehouseRepository;
import com.example.warehousemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    ExportRepository exportRepository;

    ExportDetailRepository exportDetailRepository;

    WarehouseRepository warehouseRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ExportRepository exportRepository,
                              ExportDetailRepository exportDetailRepository, WarehouseRepository warehouseRepository) {
        this.productRepository = productRepository;
        this.exportRepository = exportRepository;
        this.exportDetailRepository = exportDetailRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public GetAllProductResponse getAllProduct(String name, int size, Long category, int pageIndex, int pageSize) {
        GetAllProductResponse getAllProductResponse = new GetAllProductResponse();
        List<Product> products;
        if (category == -1 && size == -1) {
            products = productRepository.findAllByNameContaining(name, PageRequest.of(pageIndex, pageSize));
        } else {
            if (category == -1 && size != -1) {
                products = productRepository.findAllByNameContainingAndSizeIs(name, size, PageRequest.of(pageIndex, pageSize));
            } else if (category != -1 && size == -1) {
                products = productRepository.findAllByNameContainingAndAndIdCategoryIs(name, category, PageRequest.of(pageIndex, pageSize));
            } else {
                products = productRepository.findAllByNameContainingAndAndIdCategoryIsAndSizeIs(name, category, size, PageRequest.of(pageIndex, pageSize));
            }
        }
        getAllProductResponse.setProducts(products);
        getAllProductResponse.setTotal(productRepository.findAll().size());

        return getAllProductResponse;
    }

    @Override
    public Product getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public List<Product> getAllProductByCategory(Long category) {
        return productRepository.findAllByIdCategory(category);
    }

    @Override
    public List<Product> getAllProductByBranch(Long branch) {
        return productRepository.findAllByIdBranch(branch);
    }

    @Override
    public Boolean deleteProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.delete(product.get());
            return true;
        }
        return false;
    }

    @Override
    public ExportProductRequest addNewExport(ExportProductRequest exportProductRequest) throws Exception {
        Export export = new Export();
        export.setExportDate(exportProductRequest.getExportDate());
        export.setEmployee(exportProductRequest.getEmployee());
        export = exportRepository.save(export);
        for (ListProductExportRequest product : exportProductRequest.getProducts()) {
            ExportDetail exportDetail = new ExportDetail();

            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(product.getProductId());
            if (!optionalWarehouse.isPresent()) {
                throw new Exception("Product not exist!!!");
            }
            Warehouse warehouse = optionalWarehouse.get();
            int quantity = Math.min(product.getQuantity(), warehouse.getQuantity());
            warehouse.setQuantity(warehouse.getQuantity() - quantity);
            warehouseRepository.save(warehouse);

            exportDetail.setQuantity(quantity);
            exportDetail.setProductId(product.getProductId());
            exportDetail.setTotalPrice(quantity * product.getPrice());
            exportDetail.setExportId(export.getId());
            exportDetail.setBranchId(product.getBranchId());
            exportDetailRepository.save(exportDetail);
        }
        return exportProductRequest;
    }
}
