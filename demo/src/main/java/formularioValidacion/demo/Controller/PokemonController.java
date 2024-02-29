package formularioValidacion.demo.Controller;

import formularioValidacion.demo.Model.Pokemon;
import formularioValidacion.demo.Service.PokemonService;
import formularioValidacion.demo.Utils.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pokemons")
@CrossOrigin(origins = "http://localhost:5173")
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody(required = true) Pokemon pokemon, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.add(error.getField() + ": " + error.getDefaultMessage());
            }
            Response<Object> apiError = new Response<>(errors, true, 400, "Validation failed");
            return new ResponseEntity(apiError, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(
                this.pokemonService.savePokemon(pokemon),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public List<Pokemon> getAllPokemons() {
        return pokemonService.getAllPokemons();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {
        boolean deleted = pokemonService.deletePokemon(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Pokemon> updatePokemon(@PathVariable Long id, @RequestBody Pokemon pokemon) {
        Pokemon updatedPokemon = pokemonService.updatePokemon(id, pokemon);
        if (updatedPokemon != null) {
            return ResponseEntity.ok(updatedPokemon);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
