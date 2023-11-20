
package DiseaseState;
import population.Specimen;

public interface IState {
    void infect(Specimen neighboor);
}
