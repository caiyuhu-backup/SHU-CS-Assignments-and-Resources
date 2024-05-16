<template>
  <div v-html="content"></div>
</template>

<script>
import {marked} from 'marked'
import '@/assets/markdown/vue.css'
export default {
  name: "index",
  props: ['text'],
  computed: {
    content() {
      return marked(this.text)
    }
  }
}
</script>
