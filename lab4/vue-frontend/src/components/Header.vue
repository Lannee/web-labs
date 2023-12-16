<template>
    <div class="convex" id="header">
      <div id="headerModalButton" class="convex" @click="this.showModal = !this.showModal">info</div>
      <div>{{ name }}</div>
      <div>{{ group }}</div>
      <div>Variant: {{ variant }}</div>
    </div>
      <modal-window v-if="showModal" @close="showModal = false"></modal-window>
</template>

<script>
import ModalWindow from './ModalWindow.vue'

export default {
    name: "HeaderComponent",
    data() {
        return {
            name: "Bulko Egor",
            group: "P3206",
            variant: "1905",

            showModal: false
        }
    },
    components: {
        ModalWindow
    }
}
</script>

<style>
#header {
    color: var(--shade-base);
    display: flex;
    justify-content: space-between;
    width: 45%;
}

#header * {
    padding: 10px;
    margin: 0 10px; 
}

#header #headerModalButton {
    border-radius: 12px;
    cursor: pointer;
}
</style>