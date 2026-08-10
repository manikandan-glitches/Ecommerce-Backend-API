package com.example.ecommerceapi.service;

import com.example.ecommerceapi.customException.ProductNotFoundException;
import com.example.ecommerceapi.modal.product;
import com.example.ecommerceapi.repository.productRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class productService {

    @Autowired
    productRepo repo;

    public List<product> getProducts() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public product getproduct(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id:"));
    }

    public product addone(product product, MultipartFile imagefile) throws IOException {
        product.setFilename(imagefile.getOriginalFilename());
        product.setFiletype(imagefile.getContentType());
        product.setFiledata(imagefile.getBytes());
        return repo.save(product);
    }

    @Transactional(readOnly = true)
    public byte[] getimg(int id) {
        product prod = repo.findById(id).orElseThrow(() -> new ProductNotFoundException("product not found"));
        return prod.getFiledata();
    }
    @Transactional(readOnly = true)
    public void del(int id) {
        product prod = repo.findById(id).orElseThrow(() -> new ProductNotFoundException("product not found"));
        repo.deleteById(id);
    }
    @Transactional(readOnly = true)
    public product update(product product, MultipartFile imagefile, int id) throws IOException {
        product prod = repo.findById(id).orElseThrow(() -> new ProductNotFoundException("product not found"));
        prod.setName(product.getName());
        prod.setBrand(product.getBrand());
        prod.setCategory(product.getCategory());
        prod.setReleaseDate(product.getReleaseDate());
        prod.setPrice(product.getPrice());
        prod.setDescription(product.getDescription());
        prod.setStockQuantity(product.getStockQuantity());
        prod.setFilename(imagefile.getOriginalFilename());
        prod.setFiletype(imagefile.getContentType());
        prod.setFiledata(imagefile.getBytes());
        return repo.save(prod);
    }


    public List<product> searchprod(String search) {
        List<product> prods = repo.searchById(search);
        return prods;
    }
}
