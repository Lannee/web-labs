<template>
  <Header />
  <Clock />
  <div class="button convex" id="router_link"><router-link to="/home">Go to Home</router-link></div>
</template>

<script>
import Header from '../components/Header.vue'
import Clock from '../components/Clock.vue'

export default {
  name: "IndexComponent",
  components: {
    Header, Clock
  }
}
</script>


<style>
#router_link {
  width: 30%;
  margin: auto;

  padding: 12px;
  border-radius: 12px;

  text-align: center;
}

.convex {
    background-color: var(--base);

    box-shadow: -5px -5px 9px rgba(255, 255, 255, 0.45), 
                5px 5px 9px rgba(94, 104, 121, 0.3);
}

.concave {
    background-color: var(--base);

    box-shadow: inset -5px -5px 9px rgba(255, 255, 255, 0.45), 
                inset 5px 5px 9px rgba(94, 104, 121, 0.3);
}
</style>