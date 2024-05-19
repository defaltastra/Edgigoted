<template>
  <Navbar></Navbar>

  <div class="main-wrapper">
    <Sidebar></Sidebar>
    <div class="page-wrapper">
      <div class="content">
        <div class="row">
          <!-- Chart for doctors count -->
          <div class="col-md-6 col-sm-6 col-lg-6 col-xl-4">
            <canvas id="doctorsChart"></canvas>
          </div>

          <!-- Chart for patients count -->
          <div class="col-md-6 col-sm-6 col-lg-6 col-xl-4">
            <canvas id="patientsChart"></canvas>
          </div>

          <!-- Chart for appointments count -->
          <div class="col-md-6 col-sm-6 col-lg-6 col-xl-4">
            <canvas id="appointmentsChart"></canvas>
          </div>
        </div>
      </div>
      <div class="sidebar-overlay" data-reff=""></div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import Navbar from '../components/Navbar.vue';
import Sidebar from '../components/Sidebar.vue';

export default {
  components: {
    Navbar,
    Sidebar
  },
  data() {
    return {
      username: '',
      doctorsCount: 0,
      patientsCount: 0,
      appointmentsCount: 0
    };
  },
  mounted() {
    this.fetchData();
    this.username = localStorage.getItem('username');
  },
  methods: {
    async fetchData() {
      try {
        // Fetch doctors count
        const doctorsResponse = await axios.get('http://localhost:8888/api/doctors');
        this.doctorsCount = doctorsResponse.data.length;

        // Fetch patients count
        const patientsResponse = await axios.get('http://localhost:8888/api/patients');
        this.patientsCount = patientsResponse.data.length;

        // Fetch appointments count
        const appointmentsResponse = await axios.get('http://localhost:8888/api/appointments');
        this.appointmentsCount = appointmentsResponse.data.length;

        // Draw charts
        this.drawDoctorsChart();
        this.drawPatientsChart();
        this.drawAppointmentsChart();

      } catch (error) {
        console.error('Error fetching data:', error);
      }
    },
    drawDoctorsChart() {
      const ctx = document.getElementById('doctorsChart').getContext('2d');
      new Chart(ctx, {
        type: 'doughnut',
        data: {
          labels: ['Doctors', 'Empty Slots'],
          datasets: [{
            label: 'Doctors Count',
            data: [this.doctorsCount, 10 - this.doctorsCount], // Assuming there are 10 slots in total
            backgroundColor: ['#36A2EB', '#FF6384'],
            borderWidth: 1
          }]
        },
        options: {
          responsive: true
        }
      });
    },
    drawPatientsChart() {
      const ctx = document.getElementById('patientsChart').getContext('2d');
      new Chart(ctx, {
        type: 'doughnut',
        data: {
          labels: ['Patients', 'Empty Slots'],
          datasets: [{
            label: 'Patients Count',
            data: [this.patientsCount, 10 - this.patientsCount],
            backgroundColor: ['#FFCE56', '#FF6384'],
            borderWidth: 1
          }]
        },
        options: {
          responsive: true
        }
      });
    },
    drawAppointmentsChart() {
      const ctx = document.getElementById('appointmentsChart').getContext('2d');
      new Chart(ctx, {
        type: 'doughnut',
        data: {
          labels: ['Appointments', 'Empty Slots'],
          datasets: [{
            label: 'Appointments Count',
            data: [this.appointmentsCount, 10 - this.appointmentsCount], // Assuming there are 10 slots in total
            backgroundColor: ['#4CAF50', '#FF6384'],
            borderWidth: 1
          }]
        },
        options: {
          responsive: true
        }
      });
    }
  }
};
</script>

<style scoped>
/* Add custom styles for charts here */
</style>
