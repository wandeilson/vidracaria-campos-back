package com.vidracariaCampos.unit;
import com.vidracariaCampos.unit.config.GlobalExceptionHandlerTest;
import com.vidracariaCampos.unit.model.CustomerTest;
import com.vidracariaCampos.unit.model.ProductTest;
import com.vidracariaCampos.unit.model.UserTest;
import com.vidracariaCampos.unit.security.CodeAccessTest;
import com.vidracariaCampos.unit.security.CryptServiceTest;
import com.vidracariaCampos.unit.security.TokenServiceTest;
import com.vidracariaCampos.unit.service.CustomerServiceTest;
import com.vidracariaCampos.unit.service.UserConverterTest;
import com.vidracariaCampos.unit.service.UserServiceTest;
import org.junit.platform.commons.annotation.Testable;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@Testable
@SelectClasses({
    UserTest.class,
    ProductTest.class,
    CustomerTest.class,
    GlobalExceptionHandlerTest.class,
    UserServiceTest.class,
    UserConverterTest.class,
    CustomerServiceTest.class,
    CodeAccessTest.class,
    CryptServiceTest.class,
    TokenServiceTest.class
})
public class SuiteUnitTest {
}
