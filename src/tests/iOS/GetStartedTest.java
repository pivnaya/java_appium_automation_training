package tests.iOS;

import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends iOSTestCase {

    @Test
    public void testPassTroughWelcome() {
        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);

        WelcomePageObject.waitForLearnMoreAboutWikipediaLink();
        WelcomePageObject.clickNextLink();

        WelcomePageObject.waitForNewWaysToExploreText();
        WelcomePageObject.clickNextLink();

        WelcomePageObject.waitForAddOrEditPreferredLangLink();
        WelcomePageObject.clickNextLink();

        WelcomePageObject.waitForLearnMoreAboutDataCollectedLink();
        WelcomePageObject.clickGetStartedButton();
    }
}
