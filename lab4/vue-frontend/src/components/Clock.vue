<template>
  <main id="main">
        <div class="clock">
            <div class="hour"> <!-- Обертка для стрелок -->
                <div id="hr" :style="{ transform: 'rotateZ('+ hr_deg +'deg)'}"></div> <!-- Сама стрелка -->
            </div>
            <div class="min">
                <div id="mn" :style="{ transform: 'rotateZ('+ mn_deg +'deg)'}"></div>
            </div>
            <div class="sec">
                <div id="sc" :style="{ transform: 'rotateZ('+ sc_deg +'deg)'}"></div>
            </div>
        </div>
        <!-- <div class="timer"></div> -->
    </main>
</template>

<script>
const DEG = 6;

export default {
    name: "ClockComponent",
    data() {
        return {
            hr_deg: 0,
            mn_deg: 0,
            sc_deg: 0
        }
    },
    methods: {
        updateClock() {
            let day = new Date();
            this.sc_deg = day.getSeconds() * DEG;
            this.mn_deg = day.getMinutes() * DEG;
            this.hr_deg = day.getHours() * 30 + this.mn_deg / 12;
        }
    },
    mounted() {
        this.updateClock();
        setInterval(this.updateClock, 1000);
    }
}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Montserrat&display=swap');

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

.clock {
    /* position: absolute;
    top: 5%;
    right: 3%; */
    margin: auto;
    margin-bottom: 20px;

    width: 20vw;
    height: 20vw;
    display: flex;
    justify-content: center;
    align-items: center;
    background: url("https://antonaniskovich.ru/filearticle/chasy-na-javascript/file/clock.png"),
    url("https://se.ifmo.ru/o/helios-theme/images/cs_logo.png") no-repeat center,
    radial-gradient(circle, rgb(37, 73, 90) 0%, rgb(25, 48, 60) 11%, #1d1d1d 38%);
    background-size: cover, 25% 25%, cover;
    box-shadow: 0 0 10px 5px rgba(221, 221, 221, 1);
    border-radius: 50%;
    transition: 0.5s;
}

.clock:before{
    content: '';
    position: absolute;
    width: 1vw;
    height: 1vw;
    background-color: #ffffff;
    border-radius: 50%;
    z-index: 50;
}

.clock .hour,
.clock .min,
.clock .sec{
    position: absolute;
}

.clock .hour, #hr{
    width: 11vw;
    height: 11vw;
}
.clock .min, #mn{
    width: 13vw;
    height: 13vw;
}
.clock .sec, #sc{
    width: 16vw;
    height: 16vw;
}

#hr, #mn, #sc{
    display: flex;
    justify-content: center;
    position: absolute;
    border-radius: 50%;
}

#hr:before{
    content: '';
    position: absolute;
    width: 0.5vw;
    height: 6vw;
    background-color: #ffc600;
    z-index: 10;
    border-radius: 6px 6px 0 0;
}
#mn:before{
    content: '';
    position: absolute;
    width: 0.3vw;
    height: 8vw;
    background-color: #fff;
    z-index: 11;
    border-radius: 6px 6px 0 0;
}
#sc:before{
    content: '';
    position: absolute;
    width: 0.2vw;
    height: 10vw;
    background-color: #fff;
    z-index: 12;
    border-radius: 6px 6px 0 0;
}
</style>