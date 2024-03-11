package com.vidracariaCampos.unit.config;
import org.junit.platform.commons.annotation.Testable;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@Testable
@SelectClasses({
        GlobalExceptionHandlerTest.class
})
public class ConfigSuite {
}
