package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.entity.Product;
import com.example.warehousemanagement.entity.Warehouse;
import com.example.warehousemanagement.model.response.ListProductWarehouseResponse;
import com.example.warehousemanagement.model.response.WarehouseResponse;
import com.example.warehousemanagement.repository.ProductRepository;
import com.example.warehousemanagement.repository.WarehouseRepository;
import com.example.warehousemanagement.service.WarehouseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    WarehouseRepository warehouseRepository;

    ProductRepository productRepository;

    ModelMapper mapper;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, ProductRepository productRepository, ModelMapper mapper) {
        this.warehouseRepository = warehouseRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }


    @Override
    public Warehouse addNewWarehouse(Warehouse warehouse) {

        return warehouseRepository.save(warehouse);
    }

    @Override
    public ListProductWarehouseResponse getListProductOfWarehouse(int pageIndex, int pageSize, Long warehouse, String name, int size, Long category) {
        ListProductWarehouseResponse listProductWarehouseResponse = new ListProductWarehouseResponse();
        List<Product> products;
        if (size == -1 && category == -1) {
            products = productRepository.findAllByWarehouseIdAndNameContaining(warehouse, name, PageRequest.of(pageIndex, pageSize));
        } else {
            if (size == -1) {
                products = productRepository.findAllByWarehouseIdAndNameContainingAndIdCategory(warehouse, name, category, PageRequest.of(pageIndex, pageSize));
            } else if (category == -1) {
                products = productRepository.findAllByWarehouseIdAndNameContainingAndSize(warehouse, name, size, PageRequest.of(pageIndex, pageSize));
            } else {
                products = productRepository.findAllByWarehouseIdAndNameContainingAndIdCategoryAndSize(warehouse, name, category, size, PageRequest.of(pageIndex, pageSize));
            }
        }

        List<WarehouseResponse> warehouseResponses = new ArrayList<>();
        for (Product product : products) {
            WarehouseResponse warehouseResponse = mapper.map(product, WarehouseResponse.class);
            warehouseResponses.add(warehouseResponse);

        }

        for (WarehouseResponse warehouseResponse : warehouseResponses) {
            Warehouse warehouse1 = warehouseRepository.findByProductId(warehouseResponse.getId());
            if (warehouse1 != null) {
                warehouseResponse.setQuantity(warehouse1.getQuantity());
            }
        }

        listProductWarehouseResponse.setProducts(warehouseResponses);
        listProductWarehouseResponse.setTotal(productRepository.findAllByWarehouseId(warehouse).size());
        return listProductWarehouseResponse;
    }
}
