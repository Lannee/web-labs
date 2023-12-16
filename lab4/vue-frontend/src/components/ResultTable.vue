<template>
  <div class="convex" id="results">
    <table id="result_table" class="results" @click="add_tipical">
        <thead>
            <th style="width:10%">X</th>
            <th style="width:10%">Y</th>
            <th style="width:10%">R</th>
            <th style="width:10%">HitFact</th>
            <th style="width:33%">Executed at</th>
            <th style="width:27%">Execution time</th>
        </thead> 
        <tbody>     
            <tr v-for="result in results" :key="result.id">
                <td>{{ result.x }}</td>
                <td>{{ result.y }}</td>
                <td>{{ result.r }}</td>
                <td>{{ result.hit }}</td>
                <td>{{ result.curr_time }}</td>
                <td>{{ result.exec_time }}</td>
            </tr>  
        </tbody>   
    </table>
    <div v-if="results.length > 0">
        <button id="clear" class="convex" @click="clear_table">Clear</button>
    </div>
  </div>  
</template>

<script>
export default {
    data() {
        return {
            tipical: {id: 0, x: 1, y: 2, r: 3, hit: true, curr_time: 12344151, exec_time: 134115132535},
            results: [
                {id: 0, x: 1, y: 2, r: 3, hit: true, curr_time: 12344151, exec_time: 134115132535},
            ]
        }
    },
    methods: {
        clear_table() {
            this.results = []
        },
        add_tipical() {
            this.results.push(this.tipical)
        },
    }
}
</script>

<style>
td {
    text-align: center;
}
</style>