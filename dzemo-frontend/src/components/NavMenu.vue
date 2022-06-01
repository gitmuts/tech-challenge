<template>
<div>
  <v-app-bar color="#FFFFFF" dark elevation="1" app>
  <v-app-bar-nav-icon @click="drawer = true" color="black"></v-app-bar-nav-icon>
  <v-toolbar-title>
  </v-toolbar-title>
  <v-spacer></v-spacer>
  <v-menu
    left
    bottom
  >
    <template v-slot:activator="{ on, attrs }">
      <v-btn
        icon
        v-bind="attrs"
        v-on="on"
      >
        <v-icon color="accent">mdi-dots-vertical</v-icon>
      </v-btn>
    </template>

    <v-list>
      <v-list-item @click="logoutDialog = true"
      >
        <v-list-item-title>Log out</v-list-item-title>
      </v-list-item>
    </v-list>
  </v-menu>
 </v-app-bar>
  <v-navigation-drawer
    v-model="drawer"
    app
    :permanent="$vuetify.breakpoint.mdAndUp"
    dense
  >
    <v-list class="mt-0">
      <v-img style="background-color:#071b2c;" class="justify-center ma-1" contain max-width="180" max-height="80" src="@/assets/logo.png"></v-img>
      <v-list-item to="/admin">
        <v-list-item-icon>
          <v-icon>mdi-home</v-icon>
        </v-list-item-icon>
        <v-list-item-content>
          <v-list-item-title>Home</v-list-item-title>
        </v-list-item-content>
      </v-list-item>
    </v-list>
  </v-navigation-drawer>
  <v-dialog v-model="logoutDialog" persistent max-width="600">
  <v-card>
    <v-card-title class="headline">Logout</v-card-title>
    <v-card-text>
      <h4 class="mt-2"> Are you sure you want to logout? </h4> </v-card-text>
    <v-card-actions>
      <v-btn color="primary" text @click="logoutDialog = false">Cancel</v-btn>
      <v-spacer></v-spacer>
      <v-btn color="secondary" @click="logout">Logout</v-btn>
    </v-card-actions>
  </v-card>
</v-dialog>
</div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: 'NavMenu',
  data: function () {
    return {
      drawer: null,
      item: 0,
      logoutDialog: false
    }
  },
  methods: {
    ...mapActions({ logout: 'session/logout' }),
    logoutUser () {
      this.logout()
    }
  }
}
</script>
<style>
</style>
