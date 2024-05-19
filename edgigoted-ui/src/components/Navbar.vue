<template>
  <div class="header">
    <div class="header-left">
      <a href="index" class="logo">
        <img src="/assets/img/logo.png" width="35" height="35" alt=""> <span>Edgigoted</span>
      </a>
    </div>
    <a id="mobile_btn" class="mobile_btn float-left" href="#sidebar"><i class="fa fa-bars"></i></a>
    <ul class="nav user-menu float-right">
      <li class="nav-item dropdown has-arrow">
        <a href="#" class="dropdown-toggle nav-link user-link" data-toggle="dropdown">
          <span class="user-img">
            <img class="rounded-circle" src="/assets/img/user.jpg" width="24" alt="Admin">
            <span class="status online"></span>
          </span>
          <span>{{ connectedUserName }}</span>
        </a>
        <div class="dropdown-menu">
          <a class="dropdown-item" href="profile">My Profile</a>
          <a class="dropdown-item" href="edit-profile">Edit Profile</a>
          <a class="dropdown-item" href="settings">Settings</a>
          <a class="dropdown-item" href="#" @click="logout">Logout</a>
        </div>
      </li>
    </ul>
    <div class="dropdown mobile-user-menu float-right">
      <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
      <div class="dropdown-menu dropdown-menu-right">
        <a class="dropdown-item" href="profile">My Profile</a>
        <a class="dropdown-item" href="edit-profile">Edit Profile</a>
        <a class="dropdown-item" href="settings">Settings</a>
        <a class="dropdown-item" href="#" @click="logout">Logout</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      connectedUserName: "" 
    };
  },
  created() {
    // Retrieve the username from local storage when the component is created
    const user = JSON.parse(localStorage.getItem('user'));
    if (user) {
      this.connectedUserName = user.name;
    }
  },
  methods: {
    logout() {

      window.location.href = '/'; 
    }
  }
};
</script>


<style scoped>
@import '/assets/css/style.css';
</style>
