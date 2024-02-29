<template>
  <div class="container">
    <div style="display: flex; padding-top: 50px; padding-bottom: 20px">
      <h1 style="margin-bottom: 0">Pokemón</h1>
      <FormModal @registroExitoso="actualizarPokemons"></FormModal>
    </div>
    <div class="text-center" id="loaderSpinner" v-if="loading">
      <b-spinner class="spinner" type="grow"></b-spinner>
      <b-spinner class="spinner" type="grow"></b-spinner>
      <b-spinner class="spinner" type="grow"></b-spinner>
      <b-spinner class="spinner" type="grow"></b-spinner>
      <b-spinner class="spinner" type="grow"></b-spinner>
    </div>
    <div v-if="pokemons">
      <TransitionGroup name="zoomDown" tag="div" class="row">
        <div
          class="col-4 pokemonCards"
          v-for="pokemon in pokemons"
          :key="pokemon.pokemonId"
        >
          <div>
            <b-card
              :title="pokemon.name"
              :img-src="pokemon.imageBase64"
              img-alt="Image"
              img-top
              tag="article"
              style="max-width: 20rem"
              class="mb-2"
            >
              <b-card-text class="description">
                {{ pokemon.description }}
              </b-card-text>

              <div>
                <p class="CustomBadge">{{ pokemon.type.name }}</p>
                <p><b>Tamaño: </b>{{ pokemon.size }}</p>
                <p><b>Peso: </b>{{ pokemon.weight }}</p>
              </div>
              <div style="width: 100%; display: flex">
                <div
                  style="
                    display: flex;
                    width: 100%;
                    justify-content: space-around;
                  "
                >
                  <b-button
                    variant="danger"
                    style="width: 45%; padding: 8px"
                    @click="deletePokemon(pokemon.pokemonId)"
                    >Eliminar</b-button
                  >
                  <!-- <EditModal
                      style="width: 45%"
                      :key="'modalEdicion_' + pelicula.id"
                      :pelicula="pelicula"
                      @actualizacionExitosa="actualizarPeliculas"
                    ></EditModal> -->
                </div>
              </div>
            </b-card>
          </div>
        </div>
      </TransitionGroup>
    </div>
    <div v-else>
      <h3 class="text-center">Sin resultados</h3>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import FormModal from "./FormModal.vue";
export default {
  name: "PokemonCards",
  components: {
    FormModal,
  },
  data() {
    return {
      pokemons: [],
      loading: null,
      loaded: false,
    };
  },
  methods: {
    actualizarPokemons() {
      axios
        .get("http://localhost:8080/api/pokemons")
        .then((response) => {
          this.pokemons = response.data;
          this.loading = false;
        })
        .catch((error) => {
          console.error(
            "Error en la peticion para obtener los pokemon: ",
            error
          );
        });
    },
    deletePokemon(id) {
      this.$swal({
        title: "¿Estas seguro?",
        text: "No podras revertir este cambio",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        cancelButtonText: "cancelar",
        confirmButtonText: "Si, eliminar",
      }).then((result) => {
        if (result.isConfirmed) {
          axios
            .delete(`http://localhost:8080/api/pokemons/${id}`)
            .then((response) => {
              this.$swal({
                title: "Eliminado",
                text: "El pokemon ha sido eliminada con exito",
                icon: "success",
              });
              this.actualizarPokemons();
            })
            .catch((error) => {
              console.error(error);
            });
        }
      });
    },
  },
  mounted() {
    this.loading = true;
    axios
      .get("http://localhost:8080/api/pokemons")
      .then((response) => {
        this.pokemons = response.data;
      })
      .catch((error) => {
        console.error("Error en la peticion para obtener los pokemon: ", error);
      })
      .finally(() => {
        this.loading = false;
      });
  },
};
</script>

<style>
#loaderSpinner {
  height: 75%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.spinner {
  margin: 7px;
}
.pokemonCards {
  display: flex;
  justify-content: center;
}
.CustomBadge {
  background-color: black;
  border-radius: 50px;
  color: white;
  text-align: center;
  width: 50%;
}
</style>
