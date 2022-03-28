

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class StatisticsTest.
 *
 *  @author: Sayaka, Janet, Apria and Jayden
 * @version 22.03.2022
 */
public class StatisticsTest extends Statistics
{
    /**
     * Default constructor for test class StatisticsTest
     */
    public StatisticsTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testDisplayStatistics()
    {
        Statistics statisti1 = new Statistics();
        statisti1.displayStatisticDetails();
    }

    @Test
    public void testStaticsFrame()
    {
        Statistics statisti3 = new Statistics();
        statisti3.statisticsFrame();
    }

    @Test
    public void testAverageNumberOfReviewsPerProperty()
    {
        Statistics statisti2 = new Statistics();
        assertEquals(12, statisti2.averageNumberOfReviewsPerProperty());
    }

    @Test
    public void testtotalNumberOfAvailableProperties()
    {
        Statistics statisti1 = new Statistics();
        assertEquals(41941, statisti1.totalNumberOfAvailableProperties());
    }

    @Test
    public void testNumberOfEntireHomesApartments()
    {
        Statistics statisti1 = new Statistics();
        assertEquals(27175, statisti1.numberOfEntireHomesApartments());
    }

    @Test
    public void testExpensiveBorough()
    {
        Statistics statisti1 = new Statistics();
        assertEquals("Richmond upon Thames", statisti1.expensiveBorough());
    }

    @Test
    public void testAveragePricePerNight()
    {
        Statistics statisti1 = new Statistics();
        assertEquals(96, statisti1.averagePricePerNight());
    }

    @Test
    public void testmostPropertiesHostDetails()
    {
        Statistics statisti1 = new Statistics();
        assertEquals("Name : Tom ID: 33889201" , statisti1.mostPropertiesHostDetails());
    }

    @Test
    public void testNeighbourhoodWithMostProperites()
    {
        Statistics statisti1 = new Statistics();
        assertEquals("Tower Hamlets", statisti1.neighbourhoodWithMostProperites());
    }
}









