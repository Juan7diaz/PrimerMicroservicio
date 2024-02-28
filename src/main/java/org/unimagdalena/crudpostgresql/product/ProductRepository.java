package org.unimagdalena.crudpostgresql.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // esto es un metodo personalizado
    Optional<Product> findProductByName(String name);
}
