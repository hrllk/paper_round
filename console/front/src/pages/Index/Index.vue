
<template>
  <v-container fluid>
    <div class="dashboard-page">
      <v-row no-gutters class="d-flex justify-space-between mt-10 mb-6">
        <h1 class="page-title">Latest News</h1>
      </v-row>
      <v-row>
        <v-col lg="12" sm="12" md="12" cols="12">
          <agile>
              <div v-for="(item, idx) in items" v-bind:key="idx" class="slide">
              <!--              hi {{ i }}-->
              <!--              제목 : {{item.title}}-->
              <h2>{{ item.title }}</h2>

              subtitle <br><br>
              <br>
              내용 : {{ item.content }}
              <br>
              <br>
              원문 : ???
            </div>

          </agile>
        </v-col>
      </v-row>
    </div>
  </v-container>
</template>
<script>
import { VueAgile } from 'vue-agile'
export default {
  components: {
    agile: VueAgile
  },
  data () {
    return {
      items: [
        {
          title: "title1",
          content: "content1"
        },
        {
          title: "title2",
          content: "content2"
        },
      ]
    }
  }
}
</script>

<style src="./Index.scss" lang="scss"/>
