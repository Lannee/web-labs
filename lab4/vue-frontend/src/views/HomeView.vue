<template>
    <Header />
    <MainForm />
    <ResultTable />
    <div class="button convex" id="router_link"><router-link to="/">Go to Index</router-link></div>
</template>

<script>
import ResultTable from '@/components/ResultTable.vue'
import Header from '@/components/Header.vue'
import MainForm from '@/components/Form.vue'

export default {
  name: "HomeComponent",
  components: {
    Header,
    MainForm,
    ResultTable,
  }
}
</script>