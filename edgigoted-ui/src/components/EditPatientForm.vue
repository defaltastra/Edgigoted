<template>
  <div>
    <h2>Edit Patient</h2>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label>Name</label>
        <input type="text" v-model="editedPatient.name">
      </div>
      <div class="form-group">
        <label>Age</label>
        <input type="text" v-model="editedPatient.age">
      </div>
      <div class="form-group">
        <label>Address</label>
        <input type="text" v-model="editedPatient.address">
      </div>
      <div class="form-group">
        <label>Phone</label>
        <input type="text" v-model="editedPatient.phone">
      </div>
      <div class="form-group">
        <label>Email</label>
        <input type="email" v-model="editedPatient.email">
      </div>
      <button type="submit">Update</button>
    </form>
  </div>
</template>

<script>
export default {
  props: {
    patient: Object
  },
  data() {
    return {
      editedPatient: { ...this.patient }
    };
  },
  methods: {
    submitForm() {
      console.log('Submitting form with editedPatient:', JSON.stringify(this.editedPatient));
      // Emit updated patient details
      this.$emit('update-patient', this.editedPatient);
    }
  },
  created() {
    console.log('EditPatientForm created with patient:', JSON.stringify(this.patient));
  },
  watch: {
    patient(newVal) {
      console.log('Patient prop changed:', JSON.stringify(newVal));
      this.editedPatient = { ...newVal };
    }
  }
};
</script>
