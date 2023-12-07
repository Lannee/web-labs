<template>
  <Header />
</template>

<script>
import Header from '../components/Header.vue'

export default {
  name: "IndexComponent",
  components: {
    Header
  }
}
</script>


<style>
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