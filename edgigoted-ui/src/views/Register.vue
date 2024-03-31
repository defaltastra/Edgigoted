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
              <label>Username</label>
              <input type="text" class="form-control" v-model="name">
            </div>
            <div class="form-group">
              <label>Email Address</label>
              <input type="email" class="form-control" v-model="email">
            </div>
            <div class="form-group">
              <label>Password</label>
              <input type="password" class="form-control" v-model="password">
            </div>
            <div class="form-group text-center">
              <button class="btn btn-primary account-btn" type="submit">Signup</button>
            </div>
            <div class="text-center login-link">
              Already have an account? <router-link to="/">Login</router-link>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      name: '',
      email: '',
      password: ''
    };
  },
  methods: {
    async submitForm() {
      try {
        // Prepare user data
        const userData = {
          name: this.name,
          email: this.email,
          password: this.password
        };

        // Make API call to sign up the user
        const response = await axios.post('http://localhost:8888/api/doctors/signup', userData);

        // Handle success response
        console.log('User signed up successfully:', response.data);
      } catch (error) {
        // Handle error
        console.error('Error signing up user:', error);
      }
    }
  }
}
</script>

<style scoped>
@import '/assets/css/style.css';
</style>
