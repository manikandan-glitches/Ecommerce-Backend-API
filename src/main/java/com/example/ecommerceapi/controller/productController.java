package com.example.ecommerceapi.controller;

import com.example.ecommerceapi.modal.product;
import com.example.ecommerceapi.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("prod")
public class productController {

    @Autowired
    productService Service;

    @GetMapping("/products")
    public List<product> getAllProducts(){
        return Service.getProducts();
    }

    @GetMapping("/product/{id}")
    public product getProductWithId(@PathVariable int id){
        return Service.getproduct(id);
    }

    @PostMapping("/product")
    public product addProduct(@RequestPart product product , @RequestPart MultipartFile imagefile) throws IOException {
        return Service.addone(product,imagefile);
    }

    @GetMapping("/product/image/{id}")
    public byte[] getImage(@PathVariable int id) {
        return Service.getimg(id);
    }

    @PutMapping("/product/{id}")
    public product updateProducts(@PathVariable int id, @RequestPart product product,@RequestPart MultipartFile imagefile) throws IOException {
        return Service.update(product,imagefile,id);
    }

    @DeleteMapping("/product/{id}")
    public void getProducts(@PathVariable int id){
        Service.del(id);
        return;
    }

    @GetMapping("/product/search")
    public List<product> search(@RequestParam String search){
        List<product> prods = Service.searchprod(search);
        return prods;
    }

}
