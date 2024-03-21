<template>
  <div class="main-wrapper">
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
            <span>Admin</span>
          </a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="profile">My Profile</a>
            <a class="dropdown-item" href="edit-profile">Edit Profile</a>
            <a class="dropdown-item" href="settings">Settings</a>
            <a class="dropdown-item" href="login">Logout</a>
          </div>
        </li>
      </ul>
      <div class="dropdown mobile-user-menu float-right">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
        <div class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="profile">My Profile</a>
          <a class="dropdown-item" href="edit-profile">Edit Profile</a>
          <a class="dropdown-item" href="settings">Settings</a>
          <a class="dropdown-item" href="login">Logout</a>
        </div>
      </div>
    </div>
    <div class="sidebar" id="sidebar">
      <div class="sidebar-inner slimscroll">
        <div id="sidebar-menu" class="sidebar-menu">
          <ul>
            <li class="menu-title">Main</li>
            <li class="active">
              <a href="index"><i class="fa fa-dashboard"></i> <span>Dashboard</span></a>
            </li>
            <li>
              <a href="doctors"><i class="fa fa-user-md"></i> <span>Doctors</span></a>
            </li>
            <li>
              <a href="patients"><i class="fa fa-wheelchair"></i> <span>Patients</span></a>
            </li>
            <li>
              <a href="departments"><i class="fa fa-hospital-o"></i> <span>Departments</span></a>
            </li>
            <li>
              <a href="settings"><i class="fa fa-cog"></i> <span>Settings</span></a>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <div class="page-wrapper">
    <div class="content">
      <div class="row">
        <div class="col-md-6 col-sm-6 col-lg-6 col-xl-3">
          <div class="dash-widget">
            <span class="dash-widget-bg1"><i class="fa fa-stethoscope" aria-hidden="true"></i></span>
            <div class="dash-widget-info text-right">
              <h3>{{ doctorsCount }}</h3>
              <span class="widget-title1">Doctors <i class="fa fa-check" aria-hidden="true"></i></span>
            </div>
          </div>
        </div>
        <div class="col-md-6 col-sm-6 col-lg-6 col-xl-3">
          <div class="dash-widget">
            <span class="dash-widget-bg2"><i class="fa fa-user-o"></i></span>
            <div class="dash-widget-info text-right">
              <h3>{{ patientsCount }}</h3>
              <span class="widget-title2">Patients <i class="fa fa-check" aria-hidden="true"></i></span>
            </div>
          </div>
        </div>
        <!-- Add more widgets here as needed -->
      </div>
    </div>
  </div>
  </div>
  <div class="sidebar-overlay" data-reff=""></div>

</template>


<script>
import axios from 'axios';

export default {
  data() {
    return {
      doctorsCount: 0,
      patientsCount: 0
    };
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      try {
        // Fetch doctors count
        const doctorsResponse = await axios.get('http://localhost:8080/api/doctors');
        this.doctorsCount = doctorsResponse.data.count; 

        // Fetch patients count
        const patientsResponse = await axios.get('http://localhost:8080/api/patients');
        this.patientsCount = patientsResponse.data.count; 
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    }
  }
};
</script>


<style scoped>
  @import '/assets/css/style.css';

</style>
