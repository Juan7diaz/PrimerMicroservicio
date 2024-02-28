package org.unimagdalena.crudpostgresql.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    public ResponseEntity<Object> registrarProduct(@RequestBody Product product) {
        return this.productService.newProduct(product);
    }

    @PutMapping
    public ResponseEntity<Object> actualizarProduct(@RequestBody Product product) {
        return this.productService.newProduct(product);
    }

    @DeleteMapping( path = "{productID}")
    public ResponseEntity<Object> eliminarrProduct(@PathVariable("productID") Long id) {
        return this.productService.deleteProduct(id);
    }

}
