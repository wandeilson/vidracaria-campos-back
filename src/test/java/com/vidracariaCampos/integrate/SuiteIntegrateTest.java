package com.vidracariaCampos.integrate;
import com.vidracariaCampos.integrate.controller.ControllerSuite;
import com.vidracariaCampos.integrate.security.SecuritySuite;
import com.vidracariaCampos.integrate.service.ServiceSuite;
import org.junit.platform.commons.annotation.Testable;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@Testable
@SelectClasses({
        ControllerSuite.class,
        SecuritySuite.class,
        ServiceSuite.class
})
public class SuiteIntegrateTest {
}
