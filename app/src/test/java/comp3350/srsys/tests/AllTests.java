package comp3350.srsys.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import comp3350.srsys.tests.business.AccessCoursesTest;
import comp3350.srsys.tests.business.AccessSCTest;
import comp3350.srsys.tests.business.AccessStudentsTest;
import comp3350.srsys.tests.objects.CourseTest;
import comp3350.srsys.tests.objects.SCTest;
import comp3350.srsys.tests.objects.StudentTest;
import comp3350.srsys.tests.business.CalculateGPATest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        StudentTest.class,
        CourseTest.class,
        SCTest.class,
        CalculateGPATest.class,
        AccessCoursesTest.class,
        AccessStudentsTest.class,
        AccessSCTest.class
})
public class AllTests
{

}
