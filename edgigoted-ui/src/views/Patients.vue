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
                      <!-- Button to edit patients details -->
                      <button class="btn btn-primary" @click="editPatient(patient.id)">Edit</button>
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
    <!-- Edit patients Form Modal -->
    <div class="modal" v-if="showEditForm">
      <div class="modal-content">
        <span class="close" @click="showEditForm = false">&times;</span>
        <!-- Only display the EditPatientForm component -->
        <edit-patients-form :patient="selectedPatient" @update-patient="updatePatient"></edit-patients-form>
      </div>
    </div>
  </div>
</template>

<script>
import Navbar from '../components/Navbar.vue';
import Sidebar from '../components/Sidebar.vue';
import EditPatientForm from '../components/EditPatientForm.vue';
import axios from 'axios';

export default {
  components: {
    Navbar,
    Sidebar,
    EditPatientForm
  },
  data() {
    return {
      patients: [],
      showEditForm: false,
      selectedPatient: null
    };
  },
  mounted() {
    // Fetch patients data 
    this.fetchPatients();
  },
  methods: {
    fetchPatients() {
      // Make GET request to fetch patients data
      axios.get('http://localhost:8888/api/patients')
        .then(response => {
          this.patients = response.data; 
        })
        .catch(error => {
          console.error('Error fetching patients:', error);
        });
    },
    editPatient(patientId) {
      // Find the selected patient from the patients array
      this.selectedPatient = this.patients.find(patient => patient.id === patientId);
      // Show the edit form modal
      console.log("Selected Patient:", this.selectedPatient);
      this.showEditForm = true;
      console.log("Modal Displayed:", this.showEditForm);
    },
    updatePatient(updatedPatient) {
      // Check if the updatedPatient has the id property
      if (!updatedPatient.id) {
        console.error('Patient id is undefined');
        return;
      }

      // Make PUT request to update patient
      axios.put(`http://localhost:8888/api/patients/${updatedPatient.id}`, updatedPatient)
        .then(response => {
          console.log('Patient updated successfully:', response.data);
          // Close the edit form modal
          this.showEditForm = false;
          // Refresh patients data after update
          this.fetchPatients();
        })
        .catch(error => {
          console.error('Error updating patient:', error);
        });
    }
  }
};
</script>
