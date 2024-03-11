package com.vidracariaCampos;
import com.vidracariaCampos.integrate.SuiteIntegrateTest;
import com.vidracariaCampos.unit.SuiteUnitTest;
import org.junit.platform.commons.annotation.Testable;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


@Suite
@Testable
@SelectClasses({
		SuiteIntegrateTest.class,
		SuiteUnitTest.class
})
public class AllSuitesTest {
}