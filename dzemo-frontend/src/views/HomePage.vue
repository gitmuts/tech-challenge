<template>
  <v-container>
    <v-row>
    <v-col cols="12">
    <v-row>
      <v-col cols="12" md="5">
        <h1> Hello {{ user.name }} </h1>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="5">
        <h3> Loan Balance {{ loan.amount }} </h3>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="5">
        <p> Quick Actions </p>
        <v-btn to="/all-transactions"> All transactions </v-btn>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="5">
        <h2> Recent Transactions </h2>
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
                {{item.transactionMode}}:  {{item.id}}  {{item.createdAt | formatdate }}
              </v-list-item-subtitle>
              </div>
              <v-divider></v-divider>
            <!-- <v-row v-if="item.transactionType == 'CREDIT'">
              <v-row>
                <v-col cols="12" md="5">From:  {{item.customerName}} </v-col>
                <v-spacer></v-spacer>
                <v-col cols="12" md="5">  +{{item.amount}}  KES</v-col>
              </v-row>
              <v-row>
                <v-col cols="12" md="5">{{item.transactionMode}}:  {{item.id}} </v-col>
                <v-spacer></v-spacer>
                <v-col cols="12" md="5">  {{item.createdAt | formatdate }}  KES</v-col>
              </v-row>
            </v-row> -->
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
  name: 'HomePage',
  data: () => ({
    transactions: []
  }),
  computed: mapState({
    user: (state) => state.user.user,
    loan: (state) => state.user.loan
  }),
  methods: {
    ...mapActions({ getRecentTransactions: 'transaction/getRecentTransactions' }),
    triggerGetTransactions () {
      this.loading = true
      this.transactions = []
      this.getRecentTransactions().then(response => {
        this.transactions = response.data.data
      }).catch(error => {
        this.$store.dispatch('alert/displayAlert', { snackbar: true, color: 'error', message: error.response.data.message }, { root: true })
        this.loading = false
      })
    }
  },
  created () {
    this.$store.dispatch('user/getUser')
    this.$store.dispatch('user/getLoan')
    this.triggerGetTransactions()
  }
}
</script>
