<template>
  <v-layout column justify-center align-center>
    <v-flex xs12 sm8 md6>
      <v-card v-for="item in items" :key="item.id">
        <v-card-text>
          {{ item.text }}
        </v-card-text>
      </v-card>
      <v-card v-for="(list, index) in lists" :key="index">
        <v-card-text>
          {{ list }}
        </v-card-text>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
export default {
  data() {
    return {
      items: [],
      lists: []
    }
  },
  async asyncData({ app }) {
    const response = await app.$axios.get('http://localhost:8080/api/timeline')
    const listRes = await app.$axios.get('http://localhost:8080/api/userlist')
    return {
      items: response.data,
      lists: listRes.data.map(data => data.name)
    }
  }
}
</script>
