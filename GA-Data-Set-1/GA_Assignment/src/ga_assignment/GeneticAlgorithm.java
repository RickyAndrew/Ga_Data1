/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga_assignment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author RickyL
 */
public class GeneticAlgorithm {

    /**
     * @param args the command line arguments
     */
    public double mutation = 0.006; // mutation rate
    public double crossover = 0.6; // crossover rate
    final int P = 225; // size of population
    int N = 60; // bits of genome
    int G = 500; // number of generations

    public static void main(String[] args) throws FileNotFoundException, IOException {

        GeneticAlgorithm ga = new GeneticAlgorithm();

        // Reads in training set
        Rule[] rules;
        rules = new Rule[32];
        Scanner inin = new Scanner(new FileReader("data1.txt"));
        for (int x = 0; x < 32; x++) {
            Rule rule = new Rule();
            rule.setCondition(inin.next());
            rule.setOutput(inin.nextInt());
            rules[x] = rule;
        }

        Random rand = new Random();
        Individual in = new Individual();
        FitnessCalculator fit = new FitnessCalculator();

        ArrayList<Individual> population = new ArrayList<>();
        ArrayList<Individual> offspring = new ArrayList<>();

        int i;
        int j;
        int[] gene;
        gene = new int[ga.N];
        int tempFitness = 0;
        int totalFitness = 0;

        int optimalFitness = (ga.N / 6) * ga.P;
        System.out.println("OPTIMAL FITNESS = " + optimalFitness);

        //
        //
        //
        //
        //
        //
        // ======================================= INITIALISATION =======================================
        for (i = 0; i < ga.P; i++) { // for i is less than the population limit
            tempFitness = 0;

            for (j = 0; j < ga.N; j++) { // for j is less than the number of genes in an individual
                gene[j] = rand.nextInt(2) % 2; // population[individual] gets genes (10010) set
            }   // repeat until 5 individuals have 5 genes

            // converts gene to string
            String geno = "";
            for (int a : gene) {
                geno += Integer.toString(a);
            }

            // creates a new individual
            Individual ind = new Individual();
            ind.setGene(geno);
            ind.setFitness(tempFitness);
            population.add(ind);
        }

        // gets total fitness of original population
        for (Individual fitt : population) {
            fit.fitnessCalculator(fitt, rules);
            totalFitness = fitt.getFitness() + totalFitness;
            System.out.println("gene = " + fitt.getGene() + " fit = " + fitt.getFitness());
        }
        System.out.println("Initial Total Fitness = " + totalFitness + "\n");

//
        //
        //
        //
        //
        //
        // ======================================= PARENT SELECTION =======================================
        // 
        for (j = 0; j < ga.G; j++) {
            if (totalFitness < 500000) {
                totalFitness = 0;

                // Loop through Population
                for (i = 0; i < ga.P; i++) {

                    Individual winner1;

                    // chooses X individuals at random     
                    Individual parent1 = population.get(new Random().nextInt(population.size()));
                    Individual parent2 = population.get(new Random().nextInt(population.size()));

                    // compares parents based on fitness
                    if (parent1.getFitness() >= parent2.getFitness()) {
                        winner1 = parent1;
                    } else {
                        winner1 = parent2;
                    }

                    offspring.add(winner1);

                }

                //
                //
                //
                //
                //
                //
                // ======================================= CROSSOVER / MUTATION =======================================
                for (i = 0; i < ga.P; i++) {

                    // get random bit from individual
                    int pos1 = new Random().nextInt(offspring.size());
                    Individual child1 = offspring.get(pos1);

                    int pos2 = new Random().nextInt(offspring.size());
                    Individual child2 = offspring.get(pos2);

                    // Crossover between two individuals
                    in.Crossover(child1, child2);

                    // Mutation of individual 1
                    String ds = child1.getGene();
                    Individual c1 = new Individual();
                    c1.setGene(ds);
                    in.Mutator(c1, pos1);
                    offspring.remove(pos1);
                    offspring.add(c1);

                    // Calculate fitness of both individuals
                    fit.fitnessCalculator(c1, rules);

                }

                //
                //
                //
                //
                //
                //
                // ======================================= RESET POPULATION =======================================
                // calculates offspring's total fitness
                for (Individual fitt : offspring) {
                    totalFitness = fitt.getFitness() + totalFitness;
                }

                // REMOVE WORST AND ADD BEST
                // finds best fitness from population
                Individual best = in;
                int bestFitness = 0;
                for (Individual ind : population) {
                    if (ind.getFitness() > bestFitness) {
                        bestFitness = ind.getFitness();
                        best = ind;
                    }
                }

                // finds the worst fitness
                String p = "d";
                int low = 0;
                int worstFitness = 33;
                // find lowest fitness in offspring 
                for (int lo = 0; lo < offspring.size(); lo++) {
                    if (offspring.get(lo).getFitness() < worstFitness) {
                        worstFitness = offspring.get(lo).getFitness();
                        p = offspring.get(lo).getGene();
                        low = lo;
                    }
                }
                System.out.println("Lowest Fitness " + worstFitness);

                // Replace worst fitness with best from parent population
                offspring.remove(low);
                offspring.add(best);

                // Find the best individual in the offspring
                bestFitness = 0;
                for (Individual ind : offspring) {
                    if (ind.getFitness() > bestFitness) {
                        bestFitness = ind.getFitness();
                        p = ind.gene;
                    }
                }

                // Calculate the mean fitness
                int meanFitness = 0;
                meanFitness = totalFitness / ga.P;

                System.out.println("Best Individual: " + p + " f: " + bestFitness);
                System.out.println("Total Offspring Fitness: " + totalFitness + " Mean: " + meanFitness + " (Run " + (j + 1) + ")\n");

                population.clear();

                //Move offspring into population
                for (int a = 0; a < offspring.size(); a++) {
                    Individual transfer = offspring.get(a);
                    population.add(transfer);
                }

                //Clear offspring
                offspring.clear();

            } else {
                for (Individual fitt : population) {

                    System.out.println("gene = " + fitt.getGene() + " fit = " + fitt.getFitness());
                }

                System.out.println("\nComplete");
                System.exit(0);
            }
        }

    }
}
