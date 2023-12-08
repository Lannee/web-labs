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

<style src="../../public/css/clock.css">

</style>