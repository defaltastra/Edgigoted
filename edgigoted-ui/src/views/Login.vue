<template>
  <div class="main-wrapper account-wrapper">
    <div class="account-page">
      <div class="account-center">
        <div class="account-box">
          <form @submit.prevent="submitForm" class="form-signin">
            <div v-if="errorMessage" class="alert alert-danger text-center">{{ errorMessage }}</div>

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
            <!-- Display error message if sign-in fails -->
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
      usernameOrEmail: '',
      password: '',
      errorMessage: ''
    };
  },
  methods: {
    async submitForm() {
      try {
        // Make a POST request to the doctor sign-in endpoint
        const doctorResponse = await axios.post('http://localhost:8888/api/doctors/signin', {
          email: this.usernameOrEmail,
          password: this.password
        });

        // If doctor sign-in successful, redirect or handle as needed
        console.log('Doctor signed in:', doctorResponse.data);

        // Save token in local storage
        localStorage.setItem('token', doctorResponse.data.token);

        // Clear form fields after submission
        this.usernameOrEmail = '';
        this.password = '';
        this.$router.push('/dashboard');

      } catch (error) {
        console.error('Doctor sign-in failed:', error.response.data);
        // Set error message
        this.errorMessage = error.response.data;
      }
    }
  }
}
</script>

<style scoped>
  @import '/assets/css/style.css';
</style>
