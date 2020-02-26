package edu.smith.cs.csc212.spooky;

import org.junit.Assert;
import org.junit.Test;

public class WinMansionTest {
    @Test
    public void testWinSequence() {
        TextInput fake = new TextInput(new String[] {
           "1", "1", "take", "0", "0", "0", "search", "3", "1", "1", "1", "1",
           "1", "1", "1", "1", "1"
        });

        String end = InteractiveFiction.runGame(fake, new SpookyMansion());
        Assert.assertEquals("crypt", end);
    }
}
