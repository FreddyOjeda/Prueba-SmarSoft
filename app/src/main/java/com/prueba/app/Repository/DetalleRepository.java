package com.prueba.app.Repository;

import com.prueba.app.Entity.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
se utiliza la etiqueta @repository para poder usar hibernate(JPA) el cual servira para comunicarnos a la base de
datos, aqui simplemente se hereda los metodos de la interfaz JpaRepository asiganadole como parametros la clase
modelo de detalle y el tipo de dato de su llave primaria
 */
@Repository
public interface DetalleRepository extends JpaRepository<Detalle,Long> {
}
