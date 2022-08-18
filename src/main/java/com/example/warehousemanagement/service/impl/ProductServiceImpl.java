package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.entity.*;
import com.example.warehousemanagement.model.request.ExportProductRequest;
import com.example.warehousemanagement.model.request.ListProductExportRequest;
import com.example.warehousemanagement.model.response.GetAllProductResponse;
import com.example.warehousemanagement.model.response.ProductResponse;
import com.example.warehousemanagement.repository.*;
import com.example.warehousemanagement.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    ExportRepository exportRepository;

    ExportDetailRepository exportDetailRepository;

    WarehouseRepository warehouseRepository;

    ProductOfBranchRepository productOfBranchRepository;

    ModelMapper mapper;

    PositionWarehouseRepository positionWarehouseRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ExportRepository exportRepository,
                              ExportDetailRepository exportDetailRepository, WarehouseRepository warehouseRepository,
                              ProductOfBranchRepository productOfBranchRepository, ModelMapper mapper,
                              PositionWarehouseRepository positionWarehouseRepository) {
        this.productRepository = productRepository;
        this.exportRepository = exportRepository;
        this.exportDetailRepository = exportDetailRepository;
        this.warehouseRepository = warehouseRepository;
        this.productOfBranchRepository = productOfBranchRepository;
        this.mapper = mapper;
        this.positionWarehouseRepository = positionWarehouseRepository;
    }

    @Override
    public Product addNewProduct(Product product) {
        if (productRepository.getFirstBySku(product.getSku()) != null) {
            throw new RuntimeException("sku existed !!!");
        }

        return productRepository.save(product);
    }

    @Override
    public GetAllProductResponse getAllProduct(String name, int size, Long category, int pageIndex, int pageSize) {
        GetAllProductResponse getAllProductResponse = new GetAllProductResponse();
        List<Product> products;
        if (category == -1 && size == -1) {
            products = productRepository.findAllByNameContaining(name, PageRequest.of(pageIndex, pageSize));
            getAllProductResponse.setTotal(productRepository.findAllByNameContaining(name).size());

        } else {
            if (category == -1 && size != -1) {
                products = productRepository.findAllByNameContainingAndSizeIs(name, size, PageRequest.of(pageIndex, pageSize));
                getAllProductResponse.setTotal(productRepository.findAllByNameContainingAndSizeIs(name, size).size());

            } else if (category != -1 && size == -1) {
                products = productRepository.findAllByNameContainingAndIdCategoryIs(name, category, PageRequest.of(pageIndex, pageSize));
                getAllProductResponse.setTotal(productRepository.findAllByNameContainingAndIdCategoryIs(name, category).size());

            } else {
                products = productRepository.findAllByNameContainingAndIdCategoryIsAndSizeIs(name, category, size, PageRequest.of(pageIndex, pageSize));
                getAllProductResponse.setTotal(productRepository.findAllByNameContainingAndIdCategoryIsAndSizeIs(name, category, size).size());

            }
        }
        getAllProductResponse.setProducts(products);

        return getAllProductResponse;
    }

    @Override
    public ProductResponse getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            return null;
        }

        ProductResponse productResponse = mapper.map(product.get(), ProductResponse.class);
        Optional<PositionWarehouse> warehouse = positionWarehouseRepository.findById(product.get().getPosition());
        warehouse.ifPresent(positionWarehouse -> productResponse.setPositionName(positionWarehouse.getName()));
        return productResponse;
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
            Optional<Product> optionalProduct = productRepository.findById(product.getProductId());
            if (!optionalProduct.isPresent()) {
                throw new Exception("Product not exist!!!");
            }
            Product product1 = optionalProduct.get();

            ProductOfBranch productOfBranch = productOfBranchRepository.getFirstByProductIdAndBranchId(product.getProductId(), product.getBranchId());

            if (productOfBranch != null) {
                productOfBranch.setQuantity(productOfBranch.getQuantity() + product.getQuantity());
            } else {
                productOfBranch = mapper.map(product1, ProductOfBranch.class);

            }
            productOfBranch.setBranchId(product.getBranchId());
            productOfBranch.setProductId(product.getProductId());
            productOfBranch.setBranchId(product1.getIdBranch());
            productOfBranchRepository.save(productOfBranch);

            Warehouse warehouse = warehouseRepository.findFirstByProductId(product.getProductId());
            if (warehouse == null) {
                throw new Exception("Product not exist!!!");
            }
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
