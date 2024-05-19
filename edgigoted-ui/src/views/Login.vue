<template>
  <div class="main-wrapper account-wrapper">
    <div class="account-page">
      <div class="account-center">
        <div class="account-box">
          <form @submit.prevent="submitForm" class="form-signin">
            <div v-if="errorMessage" class="alert alert-danger text-center">{{ errorMessage }}</div>

            <div class="account-logo">
              <img src="/assets/img/logo-dark.png" alt="">
            </div>
            <div class="form-group">
              <label>Email</label>
              <input type="text" autofocus class="form-control" v-model="email">
            </div>
            <div class="form-group">
              <label>Password</label>
              <input type="password" class="form-control" v-model="password">
            </div>
            <div class="form-group">
              <label>Select Role:</label><br>
              <input type="radio" id="doctor" value="doctor" v-model="role">
              <label for="doctor">Doctor</label><br>
              <input type="radio" id="nurse" value="nurse" v-model="role">
              <label for="nurse">Nurse</label><br>
              <input type="radio" id="admin" value="admin" v-model="role">
                <label for="admin">Admin</label><br>

            </div>

            <div class="form-group text-center">
              <button type="submit" class="btn btn-primary account-btn">Login</button>
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
      email: '',
      password: '',
      role: '', 
      errorMessage: ''
    };
  },
  methods: {
    async submitForm() {
      try {
        if (!this.role) {
          throw new Error('Please select a role.');
        }

        let response;
        if (this.role === 'doctor') {
          response = await axios.post('http://localhost:8888/api/doctors/signin', {
            email: this.email,
            password: this.password,
            role: this.role 
          });
        } else if (this.role === 'nurse') {
          response = await axios.post('http://localhost:8888/api/nurses/signin', {
            email: this.email,
            password: this.password,
            role: this.role 
          });
        } else if (this.role === 'admin') {
  response = await axios.post('http://localhost:8888/api/admin/signin', {
    email: this.email,
    password: this.password,
    role: this.role 
  });
}
 else {
          throw new Error('Invalid role');
        }

        // Extract the username from the response data
        const username = response.data.name;

        // Call the setConnectedUserName method to update the connectedUserName property
        this.setConnectedUserName(username);

        // Save user information and role in local storage
        const user = response.data;
        localStorage.setItem('user', JSON.stringify(user));
        localStorage.setItem('userRole', this.role);

        // Clear form fields after submission
        this.email = '';
        this.password = '';

        // Redirect to appropriate dashboard based on role
        this.$router.push('/dashboard');

      } catch (error) {
        console.error('Sign-in failed:', error.response ? error.response.data : error.message);
        this.errorMessage = error.response ? error.response.data : error.message;
      }
    },
    setConnectedUserName(username) {
      this.connectedUserName = username;
    }
  }
}
</script>

<style scoped>
</style>
