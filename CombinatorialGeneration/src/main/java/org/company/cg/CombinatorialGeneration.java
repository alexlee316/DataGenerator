package org.company.cg;

import java.util.*;
/**
 * Alex Lee
 * 1/12/15
 */
public class CombinatorialGeneration {

    /**
     * Generates all possible unique n-way combinations of variables
     *
     * @param vars a Set of variable names
     * @param n the number of variables per n-tuple
     * @return a Set of Sets; each inner set contains the variable names of a single n-tuple
     */
    static Set<Set<String>> findNtuples(Set<String> vars, int n) {
        Set<String> emptySet = new HashSet<String>();
        Set<Set<String>> nTuples = new HashSet<Set<String>>();
        findNtuples(vars, n, emptySet, nTuples);
        return nTuples;
    }


    /**
     * Recursive helper method for the findNtuples method
     */
    private static void findNtuples(Set<String> vars, int n, Set<String> temp, Set<Set<String>> product) {
        if (temp.size() == n) {
            product.add(temp);
        }
        else {
            Iterator<String> iter = vars.iterator();
            while (iter.hasNext()) {
                String next = iter.next();
                if (!temp.contains(next)) {
                    Set<String> newTemp = new HashSet<String>(temp);
                    newTemp.add(next);
                    findNtuples(vars, n, newTemp, product);
                }
            }
        }
    }


    /**
     * Generates all possible variable assignments for a set of n-tuples
     *
     * @param ntuples a Set of Sets; each inner set contains the variable names of a single n-tuple
     * @param variables a Map of variable names to a set of possible values
     * @return a Set of Maps; each Map contains variable names and values for a single n-tuple
     */
    static Set<Map<String, Object>> assignNtuples(Set<Set<String>> ntuples, Map<String, Set<Object>> variables) {
        Map<String, Object> emptyMap = new HashMap<String, Object>();
        Set<Map<String, Object>> product = new HashSet<Map<String, Object>>();
        for (Set<String> ntuple : ntuples) {
            assignNtuple(ntuple, variables, emptyMap, product);
        }
        return product;
    }

    /**
     * Recursive helper method for the assignNtuples method
     */
    private static void assignNtuple(Set<String> ntuple, Map<String, Set<Object>> variables,
                                     Map<String, Object> temp, Set<Map<String, Object>> product) {
        if (temp.size() == ntuple.size()) {
            product.add(temp);
        }
        else {
            Iterator<String> varIter = ntuple.iterator();
            while (varIter.hasNext()) {
                String nextVar = varIter.next();
                //System.out.println("Variable:  " + nextVar);
                if (!temp.containsKey(nextVar)) {
                    Iterator<Object> valIter = variables.get(nextVar).iterator();
                    while (valIter.hasNext()) {
                        Object nextVal = valIter.next();
                        //System.out.print("Variable:  " + nextVar + " ");
                        //System.out.println("Value:  " + nextVal);
                        Map<String, Object> tempCopy = new HashMap<String, Object>(temp);
                        tempCopy.put(nextVar, nextVal);
                        //System.out.println("Size:  " + tempCopy.size());
                        assignNtuple(ntuple, variables, tempCopy, product);
                    }
                }
            }
        }
    }

    /**
     * Generates all possible tuples of given size
     *
     * @param variables a Map of variable names to corresponding sets of possible values
     * @param groupSize the number of variables in each combination
     * @return a Set of Maps; each Map represents a single tuple
     * @throws if groupSize is greater than the number of variables
     */
    public static Set<Map<String, Object>> getNtuples(Map<String, Set<Object>> variables, int groupSize) throws Exception{
        if (groupSize > variables.size()) {
            throw new Exception("Invalid tuple size for parameter set");
        }
        return  assignNtuples((findNtuples(variables.keySet(), groupSize)), variables);
    }

}
