/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga_assignment;

/**
 *
 * @author RickyL
 */
public class Rule {

    String condition;
    int output;
    int bitValue;
public Rule() {
        this.condition = condition;
        this.output = output;
        this.bitValue = bitValue;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {

        return condition;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public int getOutput() {

        return output;
    }
    
    public void setBitValue(int bitValue) {
        this.bitValue = bitValue;
    }

    public int getBitValue() {

        return bitValue;
    }
    
    @Override
    public String toString() {

        return "Condition = " + condition + " Output = " + output;

    }

    
}
