<template>
    <v-app class="pa-6">
<!--      <v-app>-->
        <Header />
        <Sidebar />
        <v-main class="content">
            <router-view />
<!--            <Footer />-->
        </v-main>
    </v-app>
</template>

<script>
    import Header from '@/components/Header/Header';
    import Sidebar from '@/components/Sidebar/Sidebar';
    import './DashboardLayout.scss';

    export default {
      name: 'DashboardLayout',
      components: {Header, Sidebar},

    };
</script>
