package formularioValidacion.demo.Service;

import formularioValidacion.demo.Model.Pokemon;
import formularioValidacion.demo.Repository.PokemonRepository;
import formularioValidacion.demo.Utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {
    @Autowired
    private PokemonRepository pokemonRepository;
    public List<Pokemon> getAllPokemons(){
        return pokemonRepository.findAll();
    }

    public Response savePokemon(Pokemon pokemon){
        return new Response(
                this.pokemonRepository.saveAndFlush(pokemon),
                false,
                200,
                "Pokemon guardado exitosamente"
        );
    }

    public boolean deletePokemon(Long id) {
        if (pokemonRepository.existsById(id)) {
            pokemonRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
    public Pokemon updatePokemon(Long id, Pokemon pokemon) {
        Optional<Pokemon> existingPokemonOptional = pokemonRepository.findById(id);
        if (existingPokemonOptional.isPresent()) {
            Pokemon existingPokemon = existingPokemonOptional.get();
            existingPokemon.setName(pokemon.getName());
            existingPokemon.setDescription(pokemon.getDescription());
            existingPokemon.setType(pokemon.getType());
            existingPokemon.setSize(pokemon.getSize());
            existingPokemon.setWeight(pokemon.getWeight());
            return pokemonRepository.save(existingPokemon);
        } else {
            return null;
        }
    }
}
