package com.example.ecommerceapi.service;

import com.example.ecommerceapi.customException.ProductNotFoundException;
import com.example.ecommerceapi.modal.product;
import com.example.ecommerceapi.repository.productRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class productService {

    @Autowired
    productRepo repo;

    public List<product> getProducts() {
        return repo.findAll();
    }

    public product getproduct(int id) {
        if(repo.getById(id) ==null){
            throw new ProductNotFoundException("Product not found");
        }
        return repo.getById(id);
    }

    public product addone(product product, MultipartFile imagefile) throws IOException {
        product.setFilename(imagefile.getOriginalFilename());
        product.setFiletype(imagefile.getContentType());
        product.setFiledata(imagefile.getBytes());
        return repo.save(product);
    }

    public byte[] getimg(int id) {
        product prod = repo.getById(id);
        if(prod == null){
            throw new ProductNotFoundException("product not found");
        }
        return prod.getFiledata();
    }

    public void del(int id) {
        if(repo.getById(id) ==null){
            throw new ProductNotFoundException("Product not found");
        }
        repo.deleteById(id);
    }

    public product update(product product, MultipartFile imagefile, int id) throws IOException {
        product prod = repo.getById(id);
        if(prod == null){
            throw new ProductNotFoundException("product not found");
        }
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
