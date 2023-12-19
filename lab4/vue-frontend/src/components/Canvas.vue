<template>
    <div class="areas">
        <canvas id="areas_canvas" height="300" width="300" @click="hadleClick"></canvas>
    </div>
    <div v-show="showTip" id="tip">{{ tipMessage }}</div>
</template>

<script>
import ShotService from '@/service/ShotService'
import userAuthService from '@/service/UserAuthService'

import * as formValid from '@/validators/formValidator'

import router from '@/router'


const HIT_COLOR = "#22bb33"
const MISS_COLOR = "#d7191c"
const INVALID_RADIUS_COLOR = "#000000"

const POINT_RADIUS  = 4;

let canvas;
let ctx;

export default {
    name: "CanvasComponent",
    props: {
        radius: Number,
    },
    data() {
        return {
            results: [],

            tipMessage: "",
            showTip: false
        }
    },
    mounted() {
        canvas = document.getElementById("areas_canvas");
        ctx = canvas.getContext("2d");

        this.updateResults()

        ShotService.addListener(this)
    },
    methods: {
        updateResults() {
            ShotService.getShots().then(res => {
                if(!res) {
                    router.push('/')
                } else {
                    this.results = res.data
                    this.clearCanvas()
                    this.drawAllPoints()
                }
            })
        },
        addResult(result) {
            this.results.push(result)
            this.clearCanvas()
            this.drawAllPoints()
        },
        clearResults() {
            this.results = []
            this.clearCanvas()
        },
        drawAllPoints(val) {
            if(!val) {
                this.results.forEach(e => {
                    this.drawPointXYCoords(e.x, e.y, e.r, e.hit)
                })
            } else {
                val.forEach(e => {
                    this.drawPointXYCoords(e.x, e.y, e.r, e.hit)
                })
            }
        },
        validateX(x) {
            if(formValid.isXNotvalid(x)) {

                this.doShowTip("Invalid value for X")
                return false
            }
            this.hideTip()
            return true;
        },
        validateY(y) {
            if(formValid.isYNotvalid(y)) {
                this.doShowTip("Invalid value for Y")
                return false
            }
            this.hideTip()
            return true
        },
        validateR(r) {
            if(formValid.isRNotvalid(r)) {

                this.doShowTip("Invalid value for R")
                return false
            }
            this.hideTip()
            return true
        },
        validateShot(x, y, r) {
            return this.validateX(x) && this.validateY(y) && this.validateR(r)
        },
        doShowTip(message) {
            this.showTip = true
            this.tipMessage = message
        },
        hideTip() {
            this.showTip = false
        },
        hadleClick(event) {
            this.hideTip()
            const rect = canvas.getBoundingClientRect();
            const mouseX = event.clientX - rect.left;
            const mouseY = event.clientY - rect.top;

            const r = this.radius
            const x = parseFloat(this.convertXCoordinate(mouseX, r)).toFixed(4);
            const y = parseFloat(this.convertYCoordinate(mouseY, r)).toFixed(4);
            
            if(this.validateShot(x, y, r)) {
                ShotService.shot(x, y, r).then(e => {
                    if(e.data != undefined) {
                        this.results.push(e)
                        this.clearCanvas()
                        this.drawAllPoints()
                    } else if(e == "Invalid token") {
                        router.push('/')
                    } else {
                        this.doShowTip(e)
                    }
                })
            }
        },
        drawPointXYCoords(xValue, yValue, r, hit) {
            // console.log("draw at x: " + xValue + " y: " + yValue + " r: " + r + " hit: " + hit)
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
    },
    watch: {
        radius: function(){
            this.clearCanvas()
            this.drawAllPoints()
        }
    }
}
</script>

<style>
@media screen and (max-width: 1050px) {
    #areas_canvas {
        background-image: url("../static/images/areas.png");

        margin-bottom: 20px;
        
    }
}


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