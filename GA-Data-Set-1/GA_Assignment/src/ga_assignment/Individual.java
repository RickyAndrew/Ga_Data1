/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga_assignment;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author RickyL
 */
public class Individual {

    GeneticAlgorithm ga = new GeneticAlgorithm();
    
    public static String gen;
    Random rand = new Random();
    public String gene;
    public static final int SIZE = 6;
    int[] geno;

    private int fitness = 0;

    public Individual() {
        this.gene = gene;
    }

    public void setGene(String gene) {
        this.gene = gene;
    }

    public String getGene() {

        return gene;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int getFitness() {

        return fitness;
    }
    
    @Override
    public String toString() {

        return "gene = " + gene + " fit = " + fitness;

    }

    public void Mutator(Individual parent, int index) {
        String[] geneArray = parent.getGene().split(""); // turns string(gene) into array

        for (int k = 0; k < parent.getGene().length(); k++) { // iterates through gene
            if (Math.random() <= ga.mutation) { // if mutation occurs
                
                int g = (rand.nextInt(3) % 3); // mutate gene randomly

                String g1 = Integer.toString(g); // converts gene to int
                geneArray[k] = g1; // changes gene to mutation

                gen = Arrays.toString(geneArray); // Convert the array into string
                gen = gen.replaceAll(",", "");
                gen = gen.replaceAll(" ", "");
                gen = gen.replaceAll("\\[", "").replaceAll("\\]", "");

                parent.setGene(gen);
            }
        }
    }

    public void Crossover(Individual child1, Individual child2) {

        if (Math.random() <= ga.crossover) {
        
            // splits the gene into arrays
        String[] p1Array = child1.getGene().split("");
        String[] p2Array = child2.getGene().split("");

        int p1Count = 0;

        for (String st : p1Array) {
            p1Count++;
        }
        
        String[] child11;
        child11 = new String[p1Count];

        String[] child22;
        child22 = new String[p1Count];

        int randomPoint = rand.nextInt(p1Count - 0) + 0;

        // adds part of parent 1 to array
        for (int i = 0; i < randomPoint; i++) {
            child11[i] = p1Array[i];
        }
        
        // adds part of parent 2 to array
        for (int i = randomPoint; i < p1Count; i++) {
            child11[i] = p2Array[i];
        }
        
        // adds part of parent 1 to array
        for (int i = 0; i < randomPoint; i++) {
            child22[i] = p2Array[i];
        }

        // adds part of parent 2 to array
        for (int i = randomPoint; i < p1Count; i++) {
            child22[i] = p1Array[i];
        }

        String geno = Arrays.toString(child11); // Convert the array into string
        geno = geno.replaceAll(",", "");
        geno = geno.replaceAll(" ", "");
        geno = geno.replaceAll("\\[", "").replaceAll("\\]", "");
        
        // sets the gene
        child1.setGene(geno);


    }
    }
    

}
