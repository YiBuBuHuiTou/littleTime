<template>
<div id="home_page">
    <header-bar />
    <footer-bar />
</div>
</template>

<script>
import headerBar from '@/components/headerBar'
import footerBar from '@/components/footerBar'

export default {
  name: 'homePage',
  components: {
    headerBar,
    footerBar
  },
  data () {
    return {}
  },
  created () {

  },
  computed: {

  },
  methods: {

  }

}
</script>
<style scoped>
  #home_page {
   width: 100%;
  overflow: none;
  }
</style>
