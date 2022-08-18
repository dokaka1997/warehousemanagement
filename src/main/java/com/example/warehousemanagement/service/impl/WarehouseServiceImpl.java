package com.example.warehousemanagement.service.impl;

import com.example.warehousemanagement.entity.PositionWarehouse;
import com.example.warehousemanagement.entity.Product;
import com.example.warehousemanagement.entity.Warehouse;
import com.example.warehousemanagement.model.request.AddWarehouseRequest;
import com.example.warehousemanagement.model.response.ImportWarehouseResponse;
import com.example.warehousemanagement.model.response.ListProductWarehouseResponse;
import com.example.warehousemanagement.model.response.WarehouseResponse;
import com.example.warehousemanagement.repository.PositionWarehouseRepository;
import com.example.warehousemanagement.repository.ProductRepository;
import com.example.warehousemanagement.repository.WarehouseRepository;
import com.example.warehousemanagement.service.WarehouseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    WarehouseRepository warehouseRepository;

    ProductRepository productRepository;

    PositionWarehouseRepository positionWarehouseRepository;

    ModelMapper mapper;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseRepository, ProductRepository productRepository,
                                ModelMapper mapper, PositionWarehouseRepository positionWarehouseRepository) {
        this.warehouseRepository = warehouseRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.positionWarehouseRepository = positionWarehouseRepository;
    }


    @Override
    public ImportWarehouseResponse addNewWarehouse(AddWarehouseRequest addWarehouseRequest) {
        ImportWarehouseResponse importWarehouseResponse = new ImportWarehouseResponse();
        Warehouse warehouse = warehouseRepository.findFirstByProductId(addWarehouseRequest.getProductId());
        PositionWarehouse positionWarehouse = positionWarehouseRepository.findFirstByWarehouseId(addWarehouseRequest.getProductId());
        Long positionId = 0L;
        if (warehouse != null) {
            warehouse.setQuantity(warehouse.getQuantity() + addWarehouseRequest.getQuantity());
            warehouseRepository.save(warehouse);
            importWarehouseResponse = mapper.map(warehouse, ImportWarehouseResponse.class);

            if (positionWarehouse != null) {
                importWarehouseResponse.setPositionId(positionWarehouse.getWarehouseId());
            }
            return importWarehouseResponse;
        } else {
            warehouse = new Warehouse();
            warehouse.setQuantity(addWarehouseRequest.getQuantity());
            warehouse.setProductId(addWarehouseRequest.getProductId());
            Optional<PositionWarehouse> optionalPositionWarehouse = positionWarehouseRepository.findById(addWarehouseRequest.getPositionId());
            if (optionalPositionWarehouse.isPresent()) {
                optionalPositionWarehouse.get().setWarehouseId(addWarehouseRequest.getProductId());
                optionalPositionWarehouse.get().setStatus(true);
                positionId = optionalPositionWarehouse.get().getId();
                positionWarehouseRepository.save(optionalPositionWarehouse.get());
            }

        }
        Optional<Product> optionalProduct = productRepository.findById(warehouse.getProductId());
        if (!optionalProduct.isPresent()) {
            throw new RuntimeException("Product not existed !!!!");
        }
        Product product = optionalProduct.get();
        warehouse = warehouseRepository.save(warehouse);

        product.setWarehouseId(warehouse.getId());
        importWarehouseResponse = mapper.map(warehouse, ImportWarehouseResponse.class);
        importWarehouseResponse.setPositionId(positionId);
        return importWarehouseResponse;
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
            Warehouse warehouse1 = warehouseRepository.findFirstByProductId(warehouseResponse.getId());
            if (warehouse1 != null) {
                warehouseResponse.setQuantity(warehouse1.getQuantity());
            }
        }

        listProductWarehouseResponse.setProducts(warehouseResponses);
        listProductWarehouseResponse.setTotal(productRepository.findAllByWarehouseId(warehouse).size());
        return listProductWarehouseResponse;
    }
}
