<template>
  <div class="sidebar" id="sidebar">
    <div class="sidebar-inner slimscroll">
      <div id="sidebar-menu" class="sidebar-menu">
        <ul>
          <li class="menu-title">Main</li>
          <li :class="{ active: $route.path === '/dashboard' }">
            <router-link to="/dashboard"><i class="fa fa-dashboard"></i> <span>Dashboard</span></router-link>
          </li>
          <li :class="{ active: $route.path === '/doctors' }" v-if=" role === 'admin'">
            <router-link to="/doctors"><i class="fa fa-user-md"></i> <span>Doctors</span></router-link>
          </li>
          <li :class="{ active: $route.path === '/patients' }" v-if="role === 'nurse' || role === 'admin'">
            <router-link to="/patients"><i class="fa fa-wheelchair"></i> <span>Patients</span></router-link>
          </li>
          <li :class="{ active: $route.path === '/appointments' }" v-if="role === 'doctor' || role === 'nurse' || role === 'admin'">
  <router-link to="/appointments"><i class="fa fa-calendar"></i> <span>Appointments</span></router-link>
</li>


          <li :class="{ active: $route.path === '/settings' }">
            <router-link to="/settings"><i class="fa fa-cog"></i> <span>Settings</span></router-link>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      role: '', // Initialize role
    };
  },
  created() {
    this.role = localStorage.getItem('userRole');
  },
};
</script>

<style scoped>
@import '/assets/css/style.css';

.sidebar-menu li.active {
  background-color: #f8f9fa;
}
</style>
