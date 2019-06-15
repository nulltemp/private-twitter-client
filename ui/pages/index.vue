<template>
  <v-layout column justify-center align-center>
    <v-flex xs12 sm8 md6>
      <v-card v-for="item in items" :key="item.id">
        <v-card-text>
          {{ item.text }}
        </v-card-text>
      </v-card>
    </v-flex>
  </v-layout>
</template>

<script>
export default {
  data() {
    return {
      items: []
    }
  },
  async asyncData({ app }) {
    const response = await app.$axios.get('http://localhost:8080/api/timeline')
    return {
      items: response.data
    }
  }
}
</script>
