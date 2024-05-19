<template>
  <div>
    <Sidebar></Sidebar>
    <Navbar></Navbar>
    <div class="appointments container mt-4">
      <h1 class="mb-4">Appointments</h1>
      <div v-if="loading" class="text-center">Loading...</div>
      <div v-else>
        <button class="btn btn-primary mb-3" @click="showAddAppointmentModal">Add Appointment</button>
        <div v-if="appointments.length === 0">No appointments available.</div>
        <div v-else>
          <table class="table table-bordered">
            <thead>
              <tr>
                <th>Patient</th>
                <th>Doctor</th>
                <th>Date</th>
                <th>Reason</th>
                <th>Status</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="appointment in appointments" :key="appointment.id">
  <td>{{ getPatientName(appointment.patientId) }}</td>
  <td>{{ getDoctorName(appointment.doctorId) }}</td>
  <td>{{ formatDate(appointment.appointmentDate) }}</td>
  <td>{{ appointment.reason }}</td>
  <td>{{ appointment.status }}</td>
  <td>
                  <button class="btn btn-warning btn-sm mr-2" @click="editAppointment(appointment)">Edit</button>
                  <button class="btn btn-danger btn-sm" @click="deleteAppointment(appointment.id)">Delete</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>

    <!-- Add Appointment Modal -->
    <!-- Add Appointment Modal -->
    <div class="modal" v-if="showAddModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Add Appointment</h5>
            <button type="button" class="close" @click="showAddModal = false">&times;</button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="addAppointment">
              <div class="form-group">
                <label for="patient">Patient:</label>
                <select id="patient" class="form-control" v-model="newAppointment.patientId" required>
                  <option v-for="patient in patients" :key="patient.id" :value="patient.id">{{ patient.name }}</option>
                </select>
              </div>
              <div class="form-group">
                <label for="doctor">Doctor:</label>
                <select id="doctor" class="form-control" v-model="newAppointment.doctorId" required>
                  <option v-for="doctor in doctors" :key="doctor.id" :value="doctor.id">{{ doctor.name }}</option>
                </select>
              </div>
              <div class="form-group">
                <label for="appointmentDate">Date:</label>
                <input type="datetime-local" id="appointmentDate" class="form-control" v-model="newAppointment.appointmentDate" required>
              </div>
              <div class="form-group">
                <label for="reason">Reason:</label>
                <input type="text" id="reason" class="form-control" v-model="newAppointment.reason" required>
              </div>
              <div class="form-group">
  <label for="status">Status:</label>
  <select id="status" class="form-control" v-model="newAppointment.status" required>
    <option value="">Select Status</option>
    <option value="Pending">Pending</option>
    <option value="Confirmed">Confirmed</option>
    <option value="Cancelled">Cancelled</option>
    <!-- Add more options as needed -->
  </select>
</div>

              <button type="submit" class="btn btn-primary">Add</button>
              <button type="button" class="btn btn-secondary" @click="showAddModal = false">Cancel</button>
            </form>
          </div>
        </div>
      </div>
    </div>

    <!-- Edit Appointment Modal -->
    <div class="modal" v-if="showEditModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Edit Appointment</h5>
            <button type="button" class="close" @click="showEditModal = false">&times;</button>
          </div>
          <div class="modal-body">
            <form @submit.prevent="updateAppointment">
              <div class="form-group">
                <label for="editPatient">Patient:</label>
                <select id="editPatient" class="form-control" v-model="selectedAppointment.patientId" required>
  <option v-for="patient in patients" :key="patient.id" :value="patient.id">{{ patient.name }}</option>
</select>

              </div>
              <div class="form-group">
                <label for="editDoctor">Doctor:</label>
                <select id="editDoctor" class="form-control" v-model="selectedAppointment.doctor.id" required>
                  <option v-for="doctor in doctors" :key="doctor.id" :value="doctor.id">{{ doctor.name }}</option>
                </select>
              </div>
              <div class="form-group">
                <label for="editAppointmentDate">Date:</label>
                <input type="datetime-local" id="editAppointmentDate" class="form-control" v-model="selectedAppointment.appointmentDate" required>
              </div>
              <div class="form-group">
                <label for="editReason">Reason:</label>
                <input type="text" id="editReason" class="form-control" v-model="selectedAppointment.reason" required>
              </div>
              <div class="form-group">
  <label for="editStatus">Status:</label>
  <select id="editStatus" class="form-control" v-model="selectedAppointment.status" required>
    <option value="">Select Status</option>
    <option value="Pending">Pending</option>
    <option value="Confirmed">Confirmed</option>
    <option value="Cancelled">Cancelled</option>
    <!-- Add more options as needed -->
  </select>
</div>

              <button type="submit" class="btn btn-primary">Update</button>
              <button type="button" class="btn btn-secondary" @click="showEditModal = false">Cancel</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import Sidebar from '../components/Sidebar.vue';
import Navbar from '../components/Navbar.vue';

export default {
  components: { Sidebar, Navbar },
  data() {
    return {
      appointments: [],
      doctors: [],
      patients: [],
      loading: true,
      showAddModal: false,
      showEditModal: false,
      newAppointment: {
        patientId: '',
        doctorId: '',
        appointmentDate: '',
        reason: '',
        status: ''
      },
      selectedAppointment: null
    };
  },
  mounted() {
    this.fetchAppointments();
    this.fetchDoctors();
    this.fetchPatients();
  },
  methods: {
    async fetchAppointments() {
    try {
      const response = await axios.get('http://localhost:8888/api/appointments');
      this.appointments = response.data;
      // Fetch doctor data for each appointment
      for (const appointment of this.appointments) {
        const doctorResponse = await axios.get(`http://localhost:8888/api/doctors/${appointment.doctorId}`);
        // Set the doctor object in the appointment
        appointment.doctor = doctorResponse.data;
      }
      this.loading = false;
    } catch (error) {
      console.error('Error fetching appointments:', error);
      this.loading = false;
    }
  },

    fetchDoctors() {
      axios.get('http://localhost:8888/api/doctors')
        .then(response => {
          this.doctors = response.data;
        })
        .catch(error => {
          console.error('Error fetching doctors:', error);
        });
    },
    fetchPatients() {
      axios.get('http://localhost:8888/api/patients')
        .then(response => {
          this.patients = response.data;
        })
        .catch(error => {
          console.error('Error fetching patients:', error);
        });
    },
    addAppointment() {
      axios.post('http://localhost:8888/api/appointments', this.newAppointment)
        .then(response => {
          this.appointments.push(response.data);
          this.showAddModal = false;
          this.resetNewAppointment();
        })
        .catch(error => {
          console.error('Error adding appointment:', error);
        });
    },
    editAppointment(appointment) {
  this.selectedAppointment = { ...appointment };
  // Fetch doctor data for the selected appointment
  axios.get(`http://localhost:8888/api/doctors/${this.selectedAppointment.doctorId}`)
    .then(response => {
      this.selectedAppointment.doctor = response.data;
      this.showEditModal = true;
    })
    .catch(error => {
      console.error('Error fetching doctor data:', error);
    });
},


    updateAppointment() {
      axios.put(`http://localhost:8888/api/appointments/${this.selectedAppointment.id}`, this.selectedAppointment)
        .then(response => {
          const index = this.appointments.findIndex(appointment => appointment.id === this.selectedAppointment.id);
          if (index !== -1) {
            this.appointments.splice(index, 1, response.data);
          }
          this.showEditModal = false;
        })
        .catch(error => {
          console.error('Error updating appointment:', error);
        });
    },
    deleteAppointment(appointmentId) {
      axios.delete(`http://localhost:8888/api/appointments/${appointmentId}`)
        .then(() => {
          this.appointments = this.appointments.filter(appointment => appointment.id !== appointmentId);
        })
        .catch(error => {
          console.error('Error deleting appointment:', error);
        });
    },
    getPatientName(patientId) {
      const patient = this.patients.find(patient => patient.id === patientId);
      return patient ? patient.name : 'Unknown';
    },    
    getDoctorName(doctorId) {
      const doctor = this.doctors.find(doctor => doctor.id === doctorId);
      return doctor ? doctor.name : 'Unknown';
    },
     formatDate(dateArray) {
  // Construct a Date object using the provided array
  const date = new Date(dateArray[0], dateArray[1] - 1, dateArray[2], dateArray[3], dateArray[4]);

  // Extract date components
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are zero-based
  const day = String(date.getDate()).padStart(2, '0');
  const hours = String(date.getHours()).padStart(2, '0');
  const minutes = String(date.getMinutes()).padStart(2, '0');

  // Return formatted date string
  return `${year}-${month}-${day} ${hours}:${minutes}`;
},
    showAddAppointmentModal() {
      this.resetNewAppointment();
      this.showAddModal = true;
    },
    resetNewAppointment() {
      this.newAppointment = {
        patientId: '',
        doctorId: '',
        appointmentDate: '',
        reason: '',
        status: ''
      };
    }
  }
};
</script>

<style scoped>
@import '/assets/css/style.css';

.modal {
  display: block; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

.modal-dialog {
  margin: 15% auto; /* 15% from the top and centered */
}

.modal-content {
  background-color: #fefefe;
  padding: 20px;
  border: 1px solid #888;
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
</style>
