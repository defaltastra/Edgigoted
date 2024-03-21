// src/router.js
import { createRouter, createWebHistory } from 'vue-router'
import Login from './views/Login.vue'
import Dashboard from './views/Dashboard.vue'
import Register from './views/Register.vue'
import Patients from './views/Patients.vue'
const routes = [
  { path: '/', name: 'Login', component: Login },
  { path: '/patients', name: 'Patients', component: Patients },

  { path: '/dashboard', name: 'Dashboard', component: Dashboard },
  { path: '/register', name: 'Register', component: Register },


]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
