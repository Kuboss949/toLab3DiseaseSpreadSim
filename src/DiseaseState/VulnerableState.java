package DiseaseState;

import population.DistCalc;
import population.RandomStateSetter;
import population.Specimen;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class VulnerableState extends State {
    Map<Specimen, Integer> timesMap = new HashMap<>();
    public VulnerableState(){
        this.color = Color.orange;
    }
    @Override
    public void infect(Specimen s1, Specimen s2) {
        if(DistCalc.calculateDistance(s1, s2) <= 2){
            if(s2.getState() instanceof InfectedSymptompsState || s2.getState() instanceof InfectedNoSymptompsState){
                    timesMap.compute(s2, (key, value) -> (value == null) ? 1 : value + 1);
                if(timesMap.get(s2) >= 3 * 25){
                    if(s2.getState() instanceof InfectedSymptompsState)
                        s1.setState(RandomStateSetter.infectedRandom());
                    else if(Math.random()>0.5){
                        s1.setState(RandomStateSetter.infectedRandom());
                    }
                }
            }
            Iterator<Map.Entry<Specimen, Integer>> iterator = timesMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Specimen, Integer> entry = iterator.next();
                Specimen key = entry.getKey();

                if(DistCalc.calculateDistance(s1, key)>2)
                    timesMap.put(key, 0);
            }
        }
    }
}
