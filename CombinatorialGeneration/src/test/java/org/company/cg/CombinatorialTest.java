package org.company.cg;

import java.util.*;

import org.junit.*;

/**
 * Alex Lee
 * 1/12/15
 */
public class CombinatorialTest {


    /**
     * For n-tuples formed with k parameters, the number of possible unique n-tuples equals (k!)/(n!*(k-n)!)
     * For more information: http://csrc.nist.gov/staff/Kuhn/kuhn-wallace-gallo-04.pdf
     */
    @Test
    public void findNtupleSizeTest() {
        Set<String> variables = new HashSet<String>();
        String[] letters = {"A","B","C","D","E"};
        for (int i = 0; i < letters.length; i++) {
            variables.add(letters[i]);
            for (int v = 1; v <= i; v++) {
                Assert.assertEquals((factorial(i+1)/((factorial(v)*factorial(i+1-v)))),
                                                        CombinatorialGeneration.findNtuples(variables, v).size());
            }
        }
    }

    /**
     * When given a tuple size of 2, findNtuples returns all possible pairs formed with variables A,B,C,D
     */
    @Test
    public void ntupleContentTest() {
        Set<String> variables = new HashSet<String>();
        variables.add("A");
        variables.add("B");
        variables.add("C");
        variables.add("D");
        Set<Set<String>> ntuples =  CombinatorialGeneration.findNtuples(variables, 2);

        boolean ab = false, ac = false, ad = false, bc = false, bd = false, cd = false;

        for (Set<String> ntuple : ntuples) {
            if (ntuple.contains("A") && ntuple.contains("B"))
                ab = true;
            if (ntuple.contains("A") && ntuple.contains("C"))
                ac = true;
            if (ntuple.contains("A") && ntuple.contains("D"))
                ad = true;
            if (ntuple.contains("B") && ntuple.contains("C"))
                bc = true;
            if (ntuple.contains("B") && ntuple.contains("D"))
                bd = true;
            if (ntuple.contains("C") && ntuple.contains("D"))
                cd = true;
        }
        Assert.assertTrue(ab && ac && ad && bc && bd && cd);
    }

    /**
     * The number of possible assignments equals:
     * (number of variable tuples) * (tuple size ^ number of possible values per variable)
     */
    @Test
    public void assignNtupleSizeTest() {
        Set<String> variables = new HashSet<String>();
        variables.add("A");
        variables.add("B");
        variables.add("C");
        Set<Set<String>> ntuples = CombinatorialGeneration.findNtuples(variables, 2);

        Map<String, Set<Object>> values = new HashMap<String, Set<Object>>();
        for (String var : variables) {
            Set<Object> set = new HashSet<Object>();
            set.add(0);
            set.add(1);
            values.put(var, set);
        }

        Set<Map<String, Object>> assigned = CombinatorialGeneration.assignNtuples(ntuples, values);
        Assert.assertEquals(12, assigned.size());

        /*
        for (Map<String, Object> ntuple : assigned) {
            for (String var : ntuple.keySet()) {
                System.out.print(var + ":" + ntuple.get(var) + "  ");
            }
            System.out.println();
        }
        */
    }

    /**
     * The number of possible assignments equals:
     * (number of variable tuples) * (tuple size ^ number of possible values per variable)
     */
    @Test
    public void assignNtupleSizeTest2() {
        Set<String> variables = new HashSet<String>();
        variables.add("A");
        variables.add("B");
        variables.add("C");
        variables.add("D");
        variables.add("E");
        variables.add("F");
        Set<Set<String>> ntuples = CombinatorialGeneration.findNtuples(variables, 2);

        Map<String, Set<Object>> values = new HashMap<String, Set<Object>>();
        for (String var : variables) {
            Set<Object> set = new HashSet<Object>();
            set.add(0);
            set.add(1);
            set.add(2);
            set.add(3);
            values.put(var, set);
        }

        Set<Map<String, Object>> assigned = CombinatorialGeneration.assignNtuples(ntuples, values);
        Assert.assertEquals(ntuples.size() * (int) Math.pow(2, 4), assigned.size());

    }

    @Test
    public void assignNtupleContentTest() {
        Set<String> variables = new HashSet<String>();
        variables.add("A");
        variables.add("B");
        variables.add("C");
        Set<Set<String>> ntuples = CombinatorialGeneration.findNtuples(variables, 2);

        Map<String, Set<Object>> values = new HashMap<String, Set<Object>>();
        for (String var : variables) {
            Set<Object> set = new HashSet<Object>();
            set.add(0);
            set.add(1);
            values.put(var, set);
        }

        Set<Map<String, Object>> assigned = CombinatorialGeneration.assignNtuples(ntuples, values);

        boolean a0b0 = false, a0b1 = false, a1b0 = false, a1b1 = false,
                b0c0 = false, b0c1 = false, b1c0 = false, b1c1 = false,
                a0c0 = false, a0c1 = false, a1c0 = false, a1c1 = false;


        for (Map<String, Object> ntuple : assigned) {
            if (ntuple.containsKey("A") && ntuple.containsKey("B")) {
                if (ntuple.get("A").equals(0) && ntuple.get("B").equals(0))
                    a0b0 = true;
                if (ntuple.get("A").equals(0) && ntuple.get("B").equals(1))
                    a0b1 = true;
                if (ntuple.get("A").equals(1) && ntuple.get("B").equals(0))
                    a1b0 = true;
                if (ntuple.get("A").equals(1) && ntuple.get("B").equals(1))
                    a1b1 = true;
            }
            if (ntuple.containsKey("B") && ntuple.containsKey("C")) {
                if (ntuple.get("B").equals(0) && ntuple.get("C").equals(0))
                    b0c0 = true;
                if (ntuple.get("B").equals(0) && ntuple.get("C").equals(1))
                    b0c1 = true;
                if (ntuple.get("B").equals(1) && ntuple.get("C").equals(0))
                    b1c0 = true;
                if (ntuple.get("B").equals(1) && ntuple.get("C").equals(1))
                    b1c1 = true;
            }
            if (ntuple.containsKey("C") && ntuple.containsKey("A")) {
                if (ntuple.get("A").equals(0) && ntuple.get("C").equals(0))
                    a0c0 = true;
                if (ntuple.get("A").equals(0) && ntuple.get("C").equals(1))
                    a0c1 = true;
                if (ntuple.get("A").equals(1) && ntuple.get("C").equals(0))
                    a1c0 = true;
                if (ntuple.get("A").equals(1) && ntuple.get("C").equals(1))
                    a1c1 = true;
            }
        }
        Assert.assertTrue(a0b0 && a0b1 && a1b0 && a1b1 &&
                          b0c0 && b0c1 && b1c0 && b1c1 &&
                          a0c0 && a0c1 && a1c0 && a1c1);
    }

    @Test
    public void getTuplesTest() {

        Set<Object> aSet = new HashSet<Object>();
        aSet.add(0);
        aSet.add(1);
        Set<Object> bSet = new HashSet<Object>();
        bSet.add(0);
        bSet.add(1);
        bSet.add(2);

        Map<String, Set<Object>> values = new HashMap<String, Set<Object>>();
        values.put("A", aSet);
        values.put("B", bSet);

        Set<Map<String, Object>> assigned = CombinatorialGeneration.getNtuples(values, 2);
        Assert.assertEquals(6, assigned.size());


    }

    /**
     * Private utility method to calculate factorials
     */
    private static int factorial(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}
