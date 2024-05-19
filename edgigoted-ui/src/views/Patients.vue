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
          <div class="col-md">
            <!-- Add Patient Button -->
            <div class="text-right mb-3">
              <button class="btn btn-success" @click="showAddPatientModal">
                Add Patient
              </button>
            </div>
            <div class="table-responsive">
              <table
                class="table table-border table-striped custom-table datatable mb-0"
              >
                <!-- Table Headers -->
                <thead>
                  <tr>
                    <th>Name</th>
                    <th>Age</th>
                    <th>Address</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Assigned Doctor</th>
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
                    <td>{{ patient.assignedDoctor }}</td>
                    <td class="text-right">
                      <!-- Button to edit patient details -->
                      <button @click="editPatient(patient)" class="btn btn-primary">Edit</button>

                      <!-- Button to delete patient -->
                      <button
                        class="btn btn-danger"
                        @click="deletePatient(patient.id)"
                      >
                        Delete
                      </button>
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
    <!-- Add Patient Form Modal -->
    <div
      class="modal fade"
      id="addPatientModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="addPatientModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="addPatientModalLabel">Add Patient</h5>
            <button
              type="button"
              class="close"
              data-dismiss="modal"
              aria-label="Close"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <!-- Add Patient Form -->
            <form @submit.prevent="addPatient">
              <div class="form-group">
                <label>Name</label>
                <input
                  type="text"
                  class="form-control"
                  v-model="newPatient.name"
                />
              </div>
              <div class="form-group">
                <label>Age</label>
                <input
                  type="number"
                  class="form-control"
                  v-model="newPatient.age"
                />
              </div>
              <div class="form-group">
                <label>Address</label>
                <input
                  type="text"
                  class="form-control"
                  v-model="newPatient.address"
                />
              </div>
              <div class="form-group">
                <label>Phone</label>
                <input
                  type="text"
                  class="form-control"
                  v-model="newPatient.phone"
                />
              </div>
              <div class="form-group">
                <label>Email</label>
                <input
                  type="email"
                  class="form-control"
                  v-model="newPatient.email"
                />
              </div>
              <div class="form-group">
                <label>Assigned Doctor</label>
                <select
                  class="form-control"
                  v-model="newPatient.assignedDoctor"
                >
                  <option
                    v-for="doctor in doctors"
                    :key="doctor.id"
                    :value="doctor.name"
                  >
                    {{ doctor.name }}
                  </option>
                </select>
              </div>
              <div class="text-right">
                <button
                  type="button"
                  class="btn btn-secondary"
                  data-dismiss="modal"
                >
                  Close
                </button>
                <button type="submit" class="btn btn-primary">
                  Add Patient
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    <!-- Edit patients Form Modal -->
    <div class="modal fade" id="editPatientModal" tabindex="-1" role="dialog" aria-labelledby="editPatientModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="editPatientModalLabel">Edit Patient</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" v-if="selectedPatient">
        <!-- Edit Patient Form -->
        <form @submit.prevent="updatePatient">
          <div class="form-group">
            <label>Name</label>
            <input type="text" class="form-control" v-model="selectedPatient.name">
          </div>
          <div class="form-group">
            <label>Age</label>
            <input type="number" class="form-control" v-model="selectedPatient.age">
          </div>
          <div class="form-group">
            <label>Address</label>
            <input type="text" class="form-control" v-model="selectedPatient.address">
          </div>
          <div class="form-group">
            <label>Phone</label>
            <input type="text" class="form-control" v-model="selectedPatient.phone">
          </div>
          <div class="form-group">
            <label>Email</label>
            <input type="email" class="form-control" v-model="selectedPatient.email">
          </div>
          <div class="form-group">
            <label>Assigned Doctor</label>
            <select class="form-control" v-model="selectedPatient.assignedDoctor">
              <option v-for="doctor in doctors" :key="doctor.id" :value="doctor.name">{{ doctor.name }}</option>
            </select>
          </div>
          <div class="text-right">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="submit" class="btn btn-primary">Update Patient</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

  </div>
  
</template>

<script>
import Sidebar from '../components/Sidebar.vue';
import axios from 'axios';
import Navbar from '../components/Navbar.vue';

export default {
  components: {
    Sidebar,
    Navbar
  },
  data() {
    return {
      patients: [],
      selectedPatient: null,
      doctors: [], // Added doctors array
      userRole: '', // Added user role
      newPatient: {
        name: '',
        age: '',
        address: '',
        phone: '',
        email: '',
        assignedDoctor: ''
      }
    };
  },
  mounted() {
    // Fetch user role
    this.userRole = localStorage.getItem('userRole');

    // Fetch patients data based on user role
    if (this.userRole === 'admin') {
      this.fetchPatientsForAdmin();
    } else if (this.userRole === 'nurse') {
      this.fetchPatientsForNurse();
    } else if (this.userRole === 'doctor') {
      this.fetchPatientsForDoctor();
    }

    // Fetch doctors data
    this.fetchDoctors();
  },
  methods: {
    fetchPatientsForAdmin() {
      // Make GET request to fetch patients data for admin
      axios.get('http://localhost:8888/api/patients')
        .then(response => {
          // Update the patients data with the response data
          this.patients = response.data;
        })
        .catch(error => {
          console.error('Error fetching patients:', error);
        });
    },
    fetchPatientsForNurse() {
      // Make GET request to fetch patients data for nurse
      axios.get('http://localhost:8888/api/nurse/patients')
        .then(response => {
          // Update the patients data with the response data
          this.patients = response.data;
        })
        .catch(error => {
          console.error('Error fetching patients:', error);
        });
    },
    fetchPatientsForDoctor() {
      // Make GET request to fetch patients data for doctor
      axios.get('http://localhost:8888/api/doctor/patients')
        .then(response => {
          // Update the patients data with the response data
          this.patients = response.data;
        })
        .catch(error => {
          console.error('Error fetching patients:', error);
        });
    },
    fetchDoctors() {
      // Make GET request to fetch doctors data
      axios.get('http://localhost:8888/api/doctors')
        .then(response => {
          // Update the doctors data with the response data
          this.doctors = response.data;
        })
        .catch(error => {
          console.error('Error fetching doctors:', error);
        });
    },
    editPatient(patient) {
  if (patient) {
    // Set the selected patient
    this.selectedPatient = { ...patient };
    // Show the edit modal form
    $('#editPatientModal').modal('show');
  } else {
    console.error('Selected patient is null.');
  }
},
updatePatient() {
  // Make PUT request to update patient
  axios.put(`http://localhost:8888/api/patients/${this.selectedPatient.id}`, this.selectedPatient)
    .then(response => {
      // Find the index of the updated patient in the patients array
      const index = this.patients.findIndex(patient => patient.id === response.data.id);
      // If the patient is found, replace the old patient data with the updated data
      if (index !== -1) {
        this.patients[index] = response.data;
      }
      // Hide the modal after successful update
      $('#editPatientModal').modal('hide');
    })
    .catch(error => {
      console.error('Error updating patient:', error);
    });
},

    deletePatient(patientId) {
      // Make DELETE request to delete patient
      axios.delete(`http://localhost:8888/api/patients/${patientId}`)
        .then(() => {
          // Remove the deleted patient from the patients array
          this.patients = this.patients.filter(patient => patient.id !== patientId);
        })
        .catch(error => {
          console.error('Error deleting patient:', error);
        });
    },
    showAddPatientModal() {
      // Reset new patient data
      this.newPatient = {
        name: '',
        age: '',
        address: '',
        phone: '',
        email: '',
        assignedDoctor: ''
      };
      // Show the modal
      $('#addPatientModal').modal('show');
    },
    addPatient() {
      // Make POST request to add patient
      axios.post('http://localhost:8888/api/patients', this.newPatient)
        .then(response => {
          // Add the new patient to the patients array
          this.patients.push(response.data);
          // Hide the modal
          $('#addPatientModal').modal('hide');
        })
        .catch(error => {
          console.error('Error adding patient:', error);
        });
    }
  }
};
</script>
