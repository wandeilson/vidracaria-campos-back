package com.vidracariaCampos.unit.service;
import org.junit.platform.commons.annotation.Testable;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@Testable
@SelectClasses({
        UserConverterTest.class
})
public class ServiceSuite {
}
