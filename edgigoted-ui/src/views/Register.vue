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
              <input type="text" class="form-control" v-model="username">
            </div>
            <div class="form-group">
              <label>Email Address</label>
              <input type="email" class="form-control" v-model="email">
            </div>
            <div class="form-group">
              <label>Password</label>
              <input type="password" class="form-control" v-model="password">
            </div>
            <div class="form-group">
              <label>Mobile Number</label>
              <input type="text" class="form-control" v-model="mobileNumber">
            </div>
            <div class="form-group checkbox">
              <label>
                <input type="checkbox" v-model="termsAccepted"> I have read and agree the Terms & Conditions
              </label>
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
export default {
  data() {
    return {
      username: '',
      email: '',
      password: '',
      mobileNumber: '',
      termsAccepted: false
    };
  },
  methods: {
    submitForm() {
      // Handle form submission 
      console.log('Form submitted');
    }
  }
}
</script>

<style scoped >
  @import '/assets/css/style.css';
</style>
