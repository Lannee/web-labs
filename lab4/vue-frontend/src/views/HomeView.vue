<template>
    <Header />
    <ResultTable />
</template>

<script>
import ResultTable from '@/components/ResultTable.vue'
import Header from '../components/Header.vue'

export default {
  name: "HomeComponent",
  components: {
    Header,
    ResultTable
  }
}
</script>