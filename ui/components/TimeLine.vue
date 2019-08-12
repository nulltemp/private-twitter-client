<template>
  <v-flex xs12>
    <div>{{ title }}</div>
    <v-card v-for="item in items" :key="item.id">
      <v-card-text>
        {{ item.text }}
      </v-card-text>
    </v-card>
  </v-flex>
</template>

<script>
export default {
  props: {
    title: {
      type: String,
      default: () => {
        return ''
      }
    },
    items: {
      type: Array,
      default: () => {
        return []
      }
    }
  }
}
</script>
