package formularioValidacion.demo.Repository;

import formularioValidacion.demo.Model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}
