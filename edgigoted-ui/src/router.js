import { createRouter, createWebHistory } from 'vue-router'
import Login from './views/Login.vue'
import Dashboard from './views/Dashboard.vue'
import Register from './views/Register.vue'
import Patients from './views/Patients.vue'

const routes = [
  { path: '/', name: 'Login', component: Login },
  { path: '/patients', name: 'Patients', component: Patients, meta: { requiresAuth: true } }, // Add meta field for route guard
  { path: '/dashboard', name: 'Dashboard', component: Dashboard, meta: { requiresAuth: true } }, // Add meta field for route guard
  { path: '/register', name: 'Register', component: Register },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  // Check if the route requires authentication
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // Check if the user is authenticated
    const isAuthenticated = localStorage.getItem('token') !== null; // Check if token exists in localStorage

    if (!isAuthenticated) {
      // Redirect to login page if not authenticated
      next({ name: 'Login' })
    } else {
      // Proceed to the route if authenticated
      next()
    }
  } else {
    // Proceed to the route if no authentication is required
    next()
  }
})

export default router
