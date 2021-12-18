import java.util.*;

public class Ukulele implements Instrument
{
    ArrayList<LinkedList<Double>> strings;

    Ukulele() 
    {
        strings = new ArrayList<>();
        for (int i = 0; i < 37; i++) 
        {
            double freq = 440.0 * Math.pow(2.0, (i - 24) / 12.0);
            int size = (int)(44100 / freq);
            LinkedList<Double> ll = new LinkedList<>();

            for (int j = 0; j < size; j++) 
            {
                ll.add(0.0);
            }
            strings.add(ll);
        }
    }


    @Override
    public void pluck(char key) 
    {
        double freq = 440.0 * Math.pow(2.0, (key % 37 - 24) / 12.0);
        int size = (int)(44100.0 / freq);
        for (int f = 0; f < size; f++) 
        {
            double random = Math.random() - 0.5;
            double rand = random;
            strings.get(key % 37).set(f, rand);
        }
    }

    @Override
    public void tick() 
    {
        for (int gs = 0; gs < 37; gs++) 
        {
            LinkedList<Double> buffer = strings.get(gs);
            double magic = 0.994 * (buffer.get(0) + buffer.get(1)) / 2.0;
            buffer.add(magic);
            buffer.remove();
        }
    }

    @Override
    public double superposition() 
    {
        double sum = 0;
        // For loop goes through list named to get the values
        for (int i = 0; i < strings.size(); i++)
        {
            Double value = strings.get(i).get(0);
            sum += value;
        }
        return sum;
    }
}