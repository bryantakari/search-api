package com.apps.geonames;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@RunWith(Suite.class)
@Suite.SuiteClasses({
		DescendingStrategyTests.class,
		ScoreCalculatorTests.class,
		AscendingStrategyTests.class,
		GeonamesManagerTests.class
})
@SpringBootTest
class GeonamesApplicationTests {



}
