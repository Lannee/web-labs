<template>
    <div class="areas">
        <canvas id="areas_canvas" height="300" width="300" @click="hadleClick"></canvas>
    </div>
</template>

<script>
import ShotService from '@/service/ShotService'

const HIT_COLOR = "#22bb33"
const MISS_COLOR = "#d7191c"
const INVALID_RADIUS_COLOR = "#000000"

const POINT_RADIUS  = 4;

let canvas;
let ctx;

export default {
    name: "CanvasComponent",
    props: {
        radius: Number
    },
    mounted() {
        canvas = document.getElementById("areas_canvas");
        ctx = canvas.getContext("2d");
    },
    methods: {
        hadleClick(event) {
            const rect = canvas.getBoundingClientRect();
            const mouseX = event.clientX - rect.left;
            const mouseY = event.clientY - rect.top;

            const r = this.radius
            const x = parseFloat(this.convertXCoordinate(mouseX, r)).toFixed(4);
            const y = parseFloat(this.convertYCoordinate(mouseY, r)).toFixed(4);
            this.drawPointXYCoords(x, y, r, true)

            ShotService.shot(x, y, r)
        },
        drawPointXYCoords(xValue, yValue, r, hit) {
            this.drawPointCornerCoords(canvas.width / 2 + canvas.width / 3 * (xValue / this.radius),
            canvas.height / 2 - canvas.height / 2.95 * (yValue / this.radius), r,
            hit);
        },
        drawPointCornerCoords(xValue, yValue, r, hit) {
            ctx.save();
            ctx.beginPath();
            ctx.fillStyle = r == this.radius ? (hit ? HIT_COLOR : MISS_COLOR) : INVALID_RADIUS_COLOR;
            ctx.arc(xValue, yValue, POINT_RADIUS, 0, 2 * Math.PI);
            ctx.fill();
            ctx.restore();
        },
        clearCanvas() {
            ctx.clearRect(0, 0, canvas.width, canvas.width)
        },
        convertXCoordinate(xOnCorner, radius) {
            return (xOnCorner - canvas.width / 2) / (canvas.width / 3) * radius
        },
        convertYCoordinate(yOnCorner, radius) {
            return -(yOnCorner - canvas.height / 2) / (canvas.height / 3) * radius
        }
    }
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