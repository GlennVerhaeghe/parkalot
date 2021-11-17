package be.parkalot.knight_parkalot.switchsecure;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityGuard {
    ApiUserRole value() ;

    enum ApiUserRole {
        ADMIN, CUSTOMER
    }
}
