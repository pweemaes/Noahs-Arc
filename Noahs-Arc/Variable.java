/*
 * Write a description of class Variable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Variable
{
    // instance variables - replace the example below with your own
    private int[] domain;
    private String name;
    private int value;
    
    public Variable(int[] domainParam, int id)
    {
        domain = domainParam;
        name = Integer.toString(id);
    }
    
    public String getName() 
    {
        return name;
    }
    
    public int getValue()
    {
        return value;
    }

}
