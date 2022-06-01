<template>
  <v-container>
    <v-row>
    <v-col cols="12">
    <v-row>
      <v-col cols="12" md="5">
        <h1> Payment History </h1>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="5">
        <v-menu
          v-model="startDateMenu" :close-on-content-click="false"
          :nudge-right="40"
          transition="scale-transition"
          offset-y
          min-width="auto"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="startDate"
              label="Picker without buttons"
              prepend-icon="mdi-calendar"
              readonly
              v-bind="attrs"
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker
            v-model="startDate"
            @input="startDateMenu = false"
          ></v-date-picker>
        </v-menu>
      </v-col>
      <v-col cols="12" md="5">
        <v-menu
          v-model="endDateMenu" :close-on-content-click="false"
          :nudge-right="40"
          transition="scale-transition"
          offset-y
          min-width="auto"
        >
          <template v-slot:activator="{ on, attrs }">
            <v-text-field
              v-model="endDate"
              label="Picker without buttons"
              prepend-icon="mdi-calendar"
              readonly
              v-bind="attrs"
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker
            v-model="endDate"
            @input="endDateMenu = false"
          ></v-date-picker>
        </v-menu>
      </v-col>
      <v-col>
          <v-btn @click="triggerGetTransactions" color="primary">Load </v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-list three-line>
        <template v-for="item in transactions">
          <v-list-item :key="item.id">
            <v-list-item-avatar>
            </v-list-item-avatar>
            <v-list-item-content>
              <div v-if="item.transactionType == 'CREDIT'">
               <v-list-item-subtitle class="mb-2">
                From:  {{item.customerName}}  <span class="ml-8"> +{{item.amount}}  KES </span>
              </v-list-item-subtitle>
              <v-list-item-subtitle>
                {{item.transactionMode}}:  {{item.id}} <span class="ml-8">  {{item.createdAt | formatdate }} </span>
              </v-list-item-subtitle>
              </div>
              <div v-else>
                <v-list-item-subtitle class="mb-2">
                To:  {{item.customerName}}  -{{item.amount}}  KES
              </v-list-item-subtitle>
              <v-list-item-subtitle>
                {{item.transactionMode}}:  {{item.receiverAccountNo}}  {{item.createdAt | formatdate }}
              </v-list-item-subtitle>
              </div>
              <v-divider></v-divider>
            </v-list-item-content>
          </v-list-item>
        </template>
      </v-list>
    </v-row>
    </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapState, mapActions } from 'vuex'
export default {
  name: 'AllTransaction',
  data: () => ({
    transactions: [],
    endDate: '',
    endDateMenu: false,
    startDate: '',
    startDateMenu: false,
    payload: { startDate: '', endDate: '' }
  }),
  computed: mapState({
  }),
  methods: {
    ...mapActions({ getTransactions: 'transaction/getTransactions' }),
    triggerGetTransactions () {
      this.loading = true
      this.transactions = []
      this.payload.startDate = this.startDate
      this.payload.endDate = this.endDate
      this.getTransactions(this.payload).then(response => {
        this.transactions = response.data
      }).catch(error => {
        this.$store.dispatch('alert/displayAlert', { snackbar: true, color: 'error', message: error.response.data.message }, { root: true })
        this.loading = false
      })
    }
  },
  created () {
    this.triggerGetTransactions()
  }
}
</script>
