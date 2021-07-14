import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass
{
    @Test
    public void testGetLocalNumber()
    {
        int number = getLocalNumber();
        Assert.assertEquals("Число не равно ожидаемому!",14, number);
    }

    @Test
    public void testGetClassNumber()
    {
        int number = getClassNumber();
        Assert.assertTrue("Число не больше заданного!", number > 45);
    }
}
