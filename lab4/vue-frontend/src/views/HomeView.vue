<template>
    <Header />
    <MainForm />
    <ResultTable />
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