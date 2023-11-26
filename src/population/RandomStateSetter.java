package population;

import DiseaseState.ImmuneState;
import DiseaseState.InfectedSymptompsState;
import DiseaseState.InfectedNoSymptompsState;
import DiseaseState.State;
import DiseaseState.VulnerableState;

public class RandomStateSetter {
    public static State notInfectedRandom(boolean immunity){
        State state;
        if(immunity){
            state =  Math.random()<0.5 ? new ImmuneState() : new VulnerableState();
        }else{
            state = new VulnerableState();
        }
        return state;
    }


    public static State infectedRandom(){
        int duration = (int) (Math.random() * 11) + 20 * 25;
        return  Math.random()<0.5 ? new InfectedNoSymptompsState(duration) : new InfectedSymptompsState(duration);
    }
}
