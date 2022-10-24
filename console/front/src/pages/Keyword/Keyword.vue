
<template>
  <v-container fluid>
    <div class="dashboard-page">
      <v-row no-gutters class="d-flex justify-space-between mt-10 mb-6">
        <h1 class="page-title">Keywords</h1>
      </v-row>
      <v-row>
        <v-col cols="6" offset="3">
          <v-text-field
              v-model="keyword"
          >
            <v-btn slot="append" icon
                   @click="append()">
              <v-icon style="font-size: 28px">mdi-bookmark-plus-outline</v-icon>
            </v-btn>
            <v-btn slot="append" icon
                   @click="save()">
              <v-icon style="font-size: 28px">mdi-content-save-check</v-icon>
            </v-btn>


          </v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="8" offset="2">
          <v-btn v-for="keyword in keywordList" style="margin: 5px" v-bind:key="keyword"> <!-- success case -->
            {{ keyword }}
          </v-btn>

        </v-col>
      </v-row>
    </div>

  </v-container>
</template>
<script>
import axios from "axios";
// import store from "@/store/index";
import VueCookies from "vue-cookies";

export default {

  components: {
  },
  data () {
    return {
      keyword: null,
      // keywordList: [],
      keywordList: new Array(),
      // keywordRule: [
        // v => !!v || 'E-mail is required',
        // v => /.+@.+/.test(v) || 'E-mail must be valid',
      // ],

    }
  },
  created() {
    // console.log('VueCookies.get(\'userId\'): ', VueCookies.get('userId'));

    axios.get(`/api/v1/user/${VueCookies.get('userId')}/keywords`, {})
        .then(res => console.log("res: ", res));

  },

  methods: {
    append(){

      console.log("append()");
      let keyword = this.keyword;
      let keywordList = this.keywordList;

      if (keyword.length < 1 || keywordList.includes(keyword))
        return;



      console.log('넣기전 키워드값 : ', keyword);
      console.log('넣기전 키워드값 : ', keyword.toString());
     this.keywordList.push(keyword);
     this.keyword = '';


      // this.keywordList.push(this.keyword.toString());
    },
    save() {
      console.log("save");
    }

  }
}
</script>
<!--<style src="./Index.scss" lang="scss"/>-->
