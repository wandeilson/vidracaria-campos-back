package com.vidracariaCampos.unit;
import com.vidracariaCampos.unit.config.ConfigSuite;
import com.vidracariaCampos.unit.security.SecuritySuite;
import com.vidracariaCampos.unit.service.ServiceSuite;
import org.junit.platform.commons.annotation.Testable;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@Testable
@SelectClasses({
    ConfigSuite.class,
    SecuritySuite.class,
    ServiceSuite.class
})
public class SuiteUnitTest {
}
