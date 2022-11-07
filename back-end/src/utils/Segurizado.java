package utils;
import java.lang.annotation.*;
import javax.ws.rs.*;
/*Anotacion para indicar que el recurso requiere de autorización para acceder
 * se ejecuta el filtro AuthFilter*/
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
@NameBinding
public @interface Segurizado {

}