<template>
    <div class="sidebar" id="sidebar">
      <div class="sidebar-inner slimscroll">
        <div id="sidebar-menu" class="sidebar-menu">
          <ul>
            <li class="menu-title">Main</li>
            <li class="active">
              <router-link to="/dashboard"><i class="fa fa-dashboard"></i> <span>Dashboard</span></router-link>
            </li>
            <li>
              <router-link to="/doctors"><i class="fa fa-user-md"></i> <span>Doctors</span></router-link>
            </li>
            <li>
              <router-link to="/patients"><i class="fa fa-wheelchair"></i> <span>Patients</span></router-link>
            </li>
            <li>
              <router-link to="/departments"><i class="fa fa-hospital-o"></i> <span>Departments</span></router-link>
            </li>
            <li>
              <router-link to="/settings"><i class="fa fa-cog"></i> <span>Settings</span></router-link>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    // Add your Vue component logic here
  }
  </script>
  
  <style scoped>
  @import '/assets/css/style.css';

  </style>
  