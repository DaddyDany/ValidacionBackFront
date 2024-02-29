import Vue from "vue";
import App from "./App.vue";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import "@asika32764/vue-animate/dist/vue-animate.css";
import VueSweetalert2 from "vue-sweetalert2";
import "sweetalert2/dist/sweetalert2.min.css";
import "./assets/main.css";

//IMPORTACIONES DE VEE-VALIDATE
import { ValidationProvider, ValidationObserver, extend } from "vee-validate";

// REGISTRAR COMO COMPONENTES GLOBALES LAS IMPORTACIONES DE VEE-VALIDATE
Vue.component("ValidationProvider", ValidationProvider);
Vue.component("ValidationObserver", ValidationObserver);

//Make sweetalert available throughout your project
Vue.use(VueSweetalert2);
// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

new Vue({
  render: (h) => h(App),
}).$mount("#app");
