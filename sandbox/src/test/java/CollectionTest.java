import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class CollectionTest {

    @Test
    void testMap() {
        var digits = new HashMap<Character, String>();
        digits.put('1', "one");
        digits.put('2', "two");
        digits.put('3', "three");

        Assertions.assertEquals("one", digits.get('1'));
        digits.put('1', "один");
        Assertions.assertEquals("один", digits.get('1'));
    }
}
