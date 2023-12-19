<template>
  <div class="convex" id="results">
    <table id="result_table" class="results">
        <thead>
            <th style="width:10%">X</th>
            <th style="width:10%">Y</th>
            <th style="width:10%">R</th>
            <th style="width:10%">HitFact</th>
            <th style="width:33%" class="execColomn">Executed at</th>
            <th style="width:27%" class="timeColomn">Execution time</th>
        </thead> 
        <tbody>     
            <tr v-for="result in results" :key="result.id">
                <td>{{ result.x }}</td>
                <td>{{ result.y }}</td>
                <td>{{ result.r }}</td>
                <td>{{ result.hit }}</td>
                <td class="execColomn">{{ result.currTime }}</td>
                <td class="timeColomn">{{ result.execTime }}</td>
            </tr>  
        </tbody>   
    </table>
    <div v-if="results.length > 0">
        <button id="clear" class="convex" @click="clear_table">Clear</button>
    </div>
  </div>  
    <div v-show="showTip" id="tip">{{ tipMessage }}</div>

</template>

<script>
import ShotService from '@/service/ShotService'

import router from '@/router'

export default {
    data() {
        return {
            results: [],

            showTip: false,
            tipMessage: ''
        }
    },
    mounted() {
        this.updateResults()

        ShotService.addListener(this)
    },
    methods: {
        clear_table() {
            this.hideTip()
            const message = ShotService.clear()
            // if(message) {
            //     if(message.data != undefined) {
            //         this.doShowTip(message.data)
            //     } else if(message != "Clear successful") {
            //         this.doShowTip(message)
            //     }
            // }
        },
        updateResults() {
            ShotService.getShots().then(res => {
                if(!res) {
                    router.push('/')
                } else {
                    this.results = res.data
                }
                // if(res.data != undefined) {
                //     this.results = res.data
                // } else {
                //     this.doShowTip(res)
                // }
            })
        },
        clearResults() {
            this.results = []
        },
        addResult(result) {
            this.results.push(result)
        },

        doShowTip(message) {
            this.showTip = true
            this.tipMessage = message
        },
        hideTip() {
            this.showTip = false
        },
    }
}
</script>

<style>
td {
    text-align: center;
}
</style>