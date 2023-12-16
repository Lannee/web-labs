<template>
    <div class="areas">
        <canvas id="areas_canvas" height="300" width="300"></canvas>
    </div>
</template>

<script>
export default {
    name: "CanvasComponent"
}
</script>

<style>
#areas_canvas {
    background-image: url("../static/images/areas.png");
}

.areas{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
}
</style>