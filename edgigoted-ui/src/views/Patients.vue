<template>
    <div class="main-wrapper">
      <!-- Include Navbar Component -->
      <Navbar />
      <!-- Include Sidebar Component -->
      <Sidebar />
      <!-- Patients Content -->
      <div class="page-wrapper">
        <div class="content">
          <div class="row">
            <!-- Patients Table -->
            <div class="col-md-12">
              <div class="table-responsive">
                <table class="table table-border table-striped custom-table datatable mb-0">
                  <!-- Table Headers -->
                  <thead>
                    <tr>
                      <th>Name</th>
                      <th>Age</th>
                      <th>Address</th>
                      <th>Phone</th>
                      <th>Email</th>
                      <th class="text-right">Action</th>
                    </tr>
                  </thead>
                  <!-- Table Body -->
                  <tbody>
                    <tr v-for="patient in patients" :key="patient.id">
                      <td>{{ patient.name }}</td>
                      <td>{{ patient.age }}</td>
                      <td>{{ patient.address }}</td>
                      <td>{{ patient.phone }}</td>
                      <td>{{ patient.email }}</td>
                      <td class="text-right">
                        <!-- Add actions here -->
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
            <!-- End of Patients Table -->
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import Navbar from '../components/Navbar.vue';
  import Sidebar from '../components/Sidebar.vue';
  import axios from 'axios'; 
  
  export default {
    components: {
      Navbar,
      Sidebar
    },
    data() {
      return {
        patients: [] // Array to store fetched patients
      };
    },
    mounted() {
      // Fetch patients data when component is mounted
      this.fetchPatients();
    },
    methods: {
      fetchPatients() {
        // Make GET request to fetch patients data
        axios.get('http://localhost:8888/api/patients')
  .then(response => {
    this.patients = response.data; // Update patients array with fetched data
  })
  .catch(error => {
    console.error('Error fetching patients:', error);
  });

      }
    }
  }
  </script>
  
  <style scoped>
  /* Add component-specific styles here */
  </style>
  