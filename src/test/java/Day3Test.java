import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Day3Test {

    Day3 p3;
    List<String> sampleClaims = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        p3 = new Day3();

        p3.input = sampleClaims;
        sampleClaims.add("#1 @ 1,3: 4x4");
        sampleClaims.add("#2 @ 3,1: 4x4");
        sampleClaims.add("#3 @ 5,5: 2x2");

    }

    @Test
    public void createClaims() {
        p3.countOverlap(sampleClaims);
//        assertEquals(4, p3.countOverlap(sampleClaims));
    }

    @Test
    public void findNotOverlappingClaim() {
        p3.countOverlap(sampleClaims);

    }


}