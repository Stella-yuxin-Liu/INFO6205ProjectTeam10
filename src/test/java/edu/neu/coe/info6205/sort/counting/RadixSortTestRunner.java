package edu.neu.coe.info6205.sort.counting;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:edu/neu/coe/info6205/huskySort.sort/counting/radixsort/RadixSort.feature",
        glue = "classpath:edu.neu.coe.info6205.huskySort.sort.counting.RadixSortStepDefinition",
        plugin = "html:target/Radixsort-report", strict = true
)
public class RadixSortTestRunner {

}
