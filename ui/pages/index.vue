<template>
  <v-layout column justify-center align-center>
    <time-line :items="items" title="timeline" />
    <template v-for="(list, index) in lists">
      <time-line :key="index" :items="list.items" :title="list.title" />
    </template>
  </v-layout>
</template>

<script>
import TimeLine from '@/components/TimeLine'

export default {
  components: {
    TimeLine
  },
  data() {
    return {
      items: [],
      lists: []
    }
  },
  async asyncData({ app }) {
    const response = await app.$axios.get('http://localhost:8080/api/timeline')
    const listRes = await app.$axios.get('http://localhost:8080/api/userlist')
    const lists = []
    for (const data of listRes.data) {
      const res = await app.$axios.get(
        'http://localhost:8080/api/userlist/statuses',
        {
          params: { slug: data.slug }
        }
      )
      lists.push({ title: data.name, items: res.data })
    }
    return {
      items: response.data,
      lists: lists
    }
  }
}
</script>
