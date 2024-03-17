<template>
  <div class="main-wrapper account-wrapper">
    <div class="account-page">
      <div class="account-center">
        <div class="account-box">
          <form @submit.prevent="submitForm" class="form-signin">
            <div class="account-logo">
              <a href="index-2.html"><img src="/assets/img/logo-dark.png" alt=""></a>

            </div>
            <div class="form-group">
              <label>Username or Email</label>
              <input type="text" autofocus class="form-control" v-model="usernameOrEmail">
            </div>
            <div class="form-group">
              <label>Password</label>
              <input type="password" class="form-control" v-model="password">
            </div>
            <div class="form-group text-right">
              <router-link to="/forgot-password">Forgot your password?</router-link>
            </div>
            <div class="form-group text-center">
              <button type="submit" class="btn btn-primary account-btn">Login</button>
            </div>
            <div class="text-center register-link">
              Don’t have an account? <router-link to="/register">Register Now</router-link>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      usernameOrEmail: '',
      password: ''
    };
  },
  methods: {
    submitForm() {
      // Handle form submission here
      console.log('Form submitted');
    }
  }
}
</script>

<style scoped>
  @import '/assets/css/style.css';

</style>
