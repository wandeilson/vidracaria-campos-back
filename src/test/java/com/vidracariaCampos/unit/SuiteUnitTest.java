package com.vidracariaCampos.unit;
import com.vidracariaCampos.unit.config.GlobalExceptionHandlerTest;
import com.vidracariaCampos.unit.model.*;
import com.vidracariaCampos.unit.security.CodeAccessTest;
import com.vidracariaCampos.unit.security.CryptServiceTest;
import com.vidracariaCampos.unit.security.TokenServiceTest;
import com.vidracariaCampos.unit.service.*;
import com.vidracariaCampos.unit.service.converter.ProductConverterTest;
import com.vidracariaCampos.unit.service.converter.UserConverterTest;
import org.junit.platform.commons.annotation.Testable;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@Testable
@SelectClasses({
    UserTest.class,
    ProductTest.class,
    CustomerTest.class,
    ProductStockTest.class,
    StockTest.class,
    TransactionStockTest.class,
    GlobalExceptionHandlerTest.class,
    UserServiceTest.class,
    UserConverterTest.class,
    ProductServiceTest.class,
    ProductStockServiceTest.class,
    StockServiceTest.class,
    ProductConverterTest.class,
    CustomerServiceTest.class,
    CodeAccessTest.class,
    CryptServiceTest.class,
    TokenServiceTest.class
})
public class SuiteUnitTest {
}
