<template>
  <div class="main-wrapper">
    <!-- Include Navbar Component -->
    <Navbar />
    <!-- Include Sidebar Component -->
    <Sidebar />
    <!-- Doctors Content -->
    <div class="page-wrapper">
      <div class="content">
        <div class="row">
          <!-- Add Doctor Button -->
          <button class="btn btn-success" @click="showAddDoctorModal">
            Add Doctor
          </button>
          <!-- Doctors Table -->
          <div class="col-md">
            <div class="table-responsive">
              <table class="table table-border table-striped custom-table datatable mb-0">
                <!-- Table Headers -->
                <thead>
                  <tr>
                    <th>Name</th>
                    <th>Specialization</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th class="text-right">Action</th>
                  </tr>
                </thead>
                <!-- Table Body -->
                <tbody>
                  <tr v-for="doctor in doctors" :key="doctor.id">
                    <td>{{ doctor.name }}</td>
                    <td>{{ doctor.specialization }}</td>
                    <td>{{ doctor.email }}</td>
                    <td>{{ doctor.phone }}</td>
                    <td class="text-right">
                      <!-- Button to edit doctor details -->
                      <button class="btn btn-primary" @click="editDoctor(doctor)">Edit</button>
                      <!-- Button to delete doctor -->
                      <button class="btn btn-danger" @click="deleteDoctor(doctor.id)">Delete</button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <!-- End of Doctors Table -->
        </div>
      </div>
    </div>
    <!-- Edit Doctor Form Modal -->
    <div class="modal fade" id="editDoctorModal" tabindex="-1" role="dialog" aria-labelledby="editDoctorModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="editDoctorModalLabel">Edit Doctor</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body" v-if="selectedDoctor">
            <!-- Edit Doctor Form -->
            <form @submit.prevent="updateDoctor">
              <div class="form-group">
                <label>Name</label>
                <input type="text" class="form-control" v-model="selectedDoctor.name">
              </div>
<div class="form-group">
    <label for="specialization">Specialization</label>
    <select class="form-control" v-model="selectedDoctor.specialization">
        <option value="">Select Specialization</option>
        <option value="Cardiology">Cardiology</option>
        <option value="Dermatology">Dermatology</option>
        <option value="Endocrinology">Endocrinology</option>
        <option value="Gastroenterology">Gastroenterology</option>
    </select>
</div>
<div class="form-group">
                <label>Email</label>
                <input type="email" class="form-control" v-model="selectedDoctor.email">
              </div>
              <div class="form-group">
                <label>Phone</label>
                <input type="text" class="form-control" v-model="selectedDoctor.phone">
              </div>
              <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control" v-model="selectedDoctor.password">
              </div>
              <div class="text-right">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary">Update Doctor</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>

    <!-- Add Doctor Form Modal -->
    <div class="modal fade" id="addDoctorModal" tabindex="-1" role="dialog" aria-labelledby="addDoctorModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="addDoctorModalLabel">Add Doctor</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <!-- Add Doctor Form -->
            <form @submit.prevent="addDoctor">
              <div class="form-group">
                <label>Name</label>
                <input type="text" class="form-control" v-model="newDoctor.name">
              </div>
<div class="form-group">
    <label for="specialization">Specialization</label>
    <select class="form-control" v-model="newDoctor.specialization">
        <option value="">Select Specialization</option>
        <option value="Cardiology">Cardiology</option>
        <option value="Dermatology">Dermatology</option>
        <option value="Endocrinology">Endocrinology</option>
        <option value="Gastroenterology">Gastroenterology</option>
    </select>
</div>

              <div class="form-group">
                <label>Email</label>
                <input type="email" class="form-control" v-model="newDoctor.email">
              </div>
              <div class="form-group">
                <label>Phone</label>
                <input type="text" class="form-control" v-model="newDoctor.phone">
              </div>
              <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control" v-model="newDoctor.password">
              </div>
              <div class="text-right">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-primary">Add Doctor</button>
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
      doctors: [],
      selectedDoctor: null,
      newDoctor: {
        name: '',
        specialization: '',
        email: '',
        phone: '',
        password: '' 
      }
    };
  },
  mounted() {
    // Fetch doctors data 
    this.fetchDoctors();
  },
  methods: {
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
    editDoctor(doctor) {
      // Set the selected doctor
      this.selectedDoctor = { ...doctor };
      // Show the edit modal form
      $('#editDoctorModal').modal('show');
    },
    updateDoctor() {
      // Make PUT request to update doctor
      axios.put(`http://localhost:8888/api/doctors/${this.selectedDoctor.id}`, this.selectedDoctor)
        .then(response => {
          // Update the doctor in the doctors array
          const index = this.doctors.findIndex(doctor => doctor.id === this.selectedDoctor.id);
          if (index !== -1) {
            this.doctors[index] = response.data;
          }
          // Hide the modal
          $('#editDoctorModal').modal('hide');
        })
        .catch(error => {
          console.error('Error updating doctor:', error);
        });
    },
    deleteDoctor(doctorId) {
      // Make DELETE request to delete doctor
      axios.delete(`http://localhost:8888/api/doctors/${doctorId}`)
        .then(() => {
          // Remove the deleted doctor from the doctors array
          this.doctors = this.doctors.filter(doctor => doctor.id !== doctorId);
        })
        .catch(error => {
          console.error('Error deleting doctor:', error);
        });
    },
    showAddDoctorModal() {
      // Reset the newDoctor object
      this.newDoctor = {
        name: '',
        specialization: '',
        email: '',
        phone: '',
        password: '' 
      };
      // Show the add doctor modal form
      $('#addDoctorModal').modal('show');
    },
    addDoctor() {
      // Make POST request to add new doctor
      axios.post('http://localhost:8888/api/doctors', this.newDoctor)
        .then(response => {
          // Add the new doctor to the doctors array
          this.doctors.push(response.data);
          // Clear the newDoctor object
          this.newDoctor = {
            name: '',
            specialization: '',
            email: '',
            phone: '',
            password: '' 
          };
          // Hide the modal
          $('#addDoctorModal').modal('hide');
        })
        .catch(error => {
          console.error('Error adding doctor:', error);
        });
    }
  }
};
</script>

<style scoped>
@import '/assets/css/style.css';
</style>
