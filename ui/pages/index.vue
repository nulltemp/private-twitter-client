<template>
  <div>
    <input v-model="email" placeholder="email" />
    <input v-model="password" type="password" placeholder="password" />
    <button @click="authenticate">Login</button>
  </div>
</template>

<script>
export default {
  auth: false,
  data() {
    return {
      email: '',
      password: ''
    }
  },
  methods: {
    async authenticate() {
      try {
        await this.$auth.loginWith('local', {
          params: {
            email: this.email,
            password: this.password
          }
        })
        this.$router.push('/home')
      } catch (e) {
        console.error(e)
      }
    }
  }
}
</script>
