<template>
    <div class="convex" id="header">
      <div>{{ name }}</div>
      <div>{{ group }}</div>
      <div>Variant: {{ variant }}</div>
    </div>
</template>

<script>

export default {
    name: "HeaderComponent",
    data() {
        return {
            name: "Bulko Egor",
            group: "P3206",
            variant: "1905"
        }
    }
}
</script>

<style scoped>
/* @media screen and (min-width: 1179) {
    #header {
        color: var(--shade-base);
        display: flex;
        justify-content: space-around;
        width: 45%;
        color: red;
    }
}

@media screen and (max-width: 1179) {
    #header {
        color: var(--shade-base);
        display: flex;
        justify-content: space-around;
        width: 45%;
        color: red;
    }
} */
</style>