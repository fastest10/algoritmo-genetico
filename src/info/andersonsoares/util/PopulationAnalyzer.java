
package info.andersonsoares.util;

import info.andersonsoares.decoders.interfaces.Decoder;
import info.andersonsoares.functions.interfaces.FunctionInterface;
import info.andersonsoares.model.Chromosome;

import java.util.ArrayList;
import java.util.List;

public class PopulationAnalyzer
{
    public static void print(List<Chromosome> population,
            Decoder d, FunctionInterface f)
    {
        Run.log("CROMOSSOMO\t\tVALORES DECODIFICADOS\t\tF(X)");

        
        StringBuffer sb2 = new StringBuffer();
        for (Chromosome i : population)
        {
        	StringBuffer sb = new StringBuffer();
            List<Double> vals = d.decode(i);

            
            sb.append("{ ");
            for (Double j : vals)
                sb.append(String.format("%f ", j));
            sb.append("}");

            sb2.append(String.format("%s \t %s \t %f",
                i.toString(), sb.toString(), Math.abs(f.calculate(vals))));
            sb2.append("\n");
            
        }
        Run.log(sb2.toString());
    }

    public static double avgFitness(List<Chromosome> population,
            Decoder d, FunctionInterface f)
    {
        double acc = 0;

        for (Chromosome chromosome : population)
            acc += f.calculate(d.decode(chromosome));

        return acc / population.size();
    }

    public static double bestFitness(List<Chromosome> population,
            Decoder d, FunctionInterface f)
    {
        /*
         * Don't initialize this to zero as it will cause incorrect
         * results if the fitnesses of all chromosomes are negative.
         */
        Double best = null;

        for (Chromosome chromosome : population)
        {
            if (best == null)
                best = f.calculate(d.decode(chromosome));
            else
            {
                double current = f.calculate(d.decode(chromosome));
                best = current > best ? current : best;
            }
        }

        return best;
    }

    
    public static double worstFitness(List<Chromosome> population,
            Decoder d, FunctionInterface f)
    {
        /*
         * Don't initialize this to zero as it will cause incorrect
         * results if the fitnesses of all genes are negative.
         */
        Double worst = null;

        for (Chromosome chromosome : population)
        {
            if (worst == null)
                worst = f.calculate(d.decode(chromosome));
            else
            {
                double current = f.calculate(d.decode(chromosome));
                worst = current < worst ? current : worst;
            }
        }

        return worst;
    }
    
}
