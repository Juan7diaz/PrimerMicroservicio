package org.unimagdalena.crudpostgresql.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private final ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public ResponseEntity<Object> newProduct(Product product){
        Optional<Product> res = productRepository.findProductByName(product.getName());

        HashMap<String, Object> datos = new HashMap<>();

        if( res.isPresent() && product.getId() == null ){
            datos.put("error", true);
            datos.put("mensaje", "Ya existe un producto con ese nombre");
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        }

        productRepository.save(product);
        datos.put("datos", product);
        datos.put("mensaje", "Se ha guardado con exito");
        if( product.getId() != null ){
            datos.put("mensaje", "Se ha actualizado con exito");
        }
        return new ResponseEntity<>(datos, HttpStatus.CREATED);

    }


    public ResponseEntity<Object> deleteProduct(Long id){
        HashMap<String, Object> datos = new HashMap<>();
        boolean existe = this.productRepository.existsById(id);
        if(!existe){
            datos.put("error", true);
            datos.put("mensaje", "No existe un producto con ese ID");
            return new ResponseEntity<>(datos, HttpStatus.CONFLICT);
        }

        this.productRepository.deleteById(id);
        datos.put("mensaje", "Se ha eliminado el producto");
        return new ResponseEntity<>(datos, HttpStatus.ACCEPTED);
    }

}
