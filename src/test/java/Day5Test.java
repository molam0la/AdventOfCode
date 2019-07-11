import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class Day5Test {

    Day5 d5;
    ArrayList<String> samplePolymer;

    @Before
    public void setUp() throws Exception {

        d5 = new Day5();
        samplePolymer = new ArrayList<>();
    }

    @Test
    public void makingPolymer() {
        samplePolymer.add("dabAcCaCBAcCcaDA");
        System.out.println(d5.makePolymer(samplePolymer));
        assertEquals(16, samplePolymer.get(0).length());
    }

    @Test
    public void ignoringNonReactingUnit() {
        samplePolymer.add("DDDDaa");
        d5.reactPolymer(d5.makePolymer(samplePolymer));
        assertEquals(6, d5.getPolymer().size());

    }

    @Test
    public void testRemovingUnits() {
        samplePolymer.add("dabAcCaCBAcCcaDA");
        d5.reactPolymer(d5.makePolymer(samplePolymer));
        assertEquals(10, d5.getPolymer().size());
    }

    @Test
    public void testLongerPolymer() {
        samplePolymer.add("XIiCEeVvoTtOFfTtvVQVvqcCcedDgGyYvVlLExJjMyYpPmuUwiIWCcIDduAaZzUGgeETLlUNnzZWmMmcCMwGpPoOuUgXxMmhHxXnNSvoOiIVHJjcChseEStAaaCcxXARrSsTmMNZzpPcGgPpChHpuUPQqPpnmMdDnNIqQipPcCWwUusPpTlLttaATpPtTsmMSRrgGfhzZHFOolLlAaLQjJqaxLlAaXyzZYRrdDAFfBgGbbBdGgdDjJDrhHROYzZyoEenNSLlhHipPIMhHhwWHiIDdCkKwWYRrywSFMoOmfslFfLWwthHEeZzgGTWYjJvVIiyLlXxWwZUuzCrRcynNYvVBMYymdDXxhTtHuUTpPqQtIKkPVvpsSnwWNibLlnnyYhaAHTuUtoONWOeEoMmwIjDYydW");
        d5.reactPolymer(d5.makePolymer(samplePolymer));
        System.out.println(d5.getPolymer());
    }
    
}